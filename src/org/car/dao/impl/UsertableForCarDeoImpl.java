package org.car.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



import org.car.dao.UsertableForCarDao;
import org.car.entity.BorrowTable;
import org.car.entity.CarTable;
import org.car.entity.UsertableForCar;
import org.car.util.JDBC;

public class UsertableForCarDeoImpl implements UsertableForCarDao{
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    @Override
    public int insertUsertableForCar(UsertableForCar user) {
        String username=user.getUsername();
        String password=user.getPassword();
        String email=user.getEmail();
        int count=0;
        conn=JDBC.getConnection();
        try {
            ps=conn.prepareStatement("insert into usertableforcar values (?,?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, null);
        }
        return count;
    }

    @Override
    public UsertableForCar selectUsertableForCarByRegister(UsertableForCar user) {
        String username=user.getUsername();
        UsertableForCar user1=null;
        conn=JDBC.getConnection();
        try {
            ps=conn.prepareStatement("select * from usertableforcar where username=?");
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()) {
                user1=user;
                return user1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return user1;
    }

    @Override
    public UsertableForCar selectUsertableForCarByLogin(UsertableForCar user) {
        // TODO Auto-generated method stub
        String username=user.getUsername();
        String password=user.getPassword();
        String email=null;
        UsertableForCar user1=null;
        conn=JDBC.getConnection();
        try {
            ps=conn.prepareStatement("select email from UsertableForCar where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if(rs.next()) {
                email=rs.getString(1);
                user1= new UsertableForCar(username,password,email);
                return user1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return user1;
    }
    @Override
    public List<CarTable> selectCarTableByUser1(CarTable car) {
        // TODO Auto-generated method stub
        conn=JDBC.getConnection();
        CarTable car1=null;
        List<CarTable> list=new ArrayList<>();
        String carNumber=car.getCarNumber();
        String carType=car.getCarType();
        String carBrand=car.getCarBrand();
        String carAutoOrHand=car.getCarAutoOrHand();
        String sql="select * from cartable";
        if(!"".equals(carNumber)) {
            sql+=" where carnumber='"+carNumber+"'";

        }
        if(!"所有".equals(carType)) {
            if(sql.indexOf("where")>=0) {
                sql+=" and cartype like '%"+carType+"%'";
            }else {
                sql+=" where cartype like '%"+carType+"%'";

            }

        }
        if(!"所有".equals(carBrand)) {
            if(sql.indexOf("where")>=0) {
                sql+=" and carBrand like '%"+carBrand+"%'";
            }else {
                sql+=" where carBrand like '%"+carBrand+"%'";
            }

        }
        if(!"所有".equals(carAutoOrHand)) {
            if(sql.indexOf("where")>=0) {
                sql+=" and carAutoOrHand like '%"+carAutoOrHand+"%'";
            }else {
                sql+=" where carAutoOrHand like '%"+carAutoOrHand+"%'";
            }

        }
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()) {
                car1=new CarTable(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getInt(6));
                list.add(car1);
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return list;
    }


    @Override
    public CarTable selectCarTableByBorrowCar(String carnumber) {
        conn=JDBC.getConnection();
        CarTable ct=null;
        try {
            ps=conn.prepareStatement("select * from cartable where carnumber=?");
            ps.setString(1, carnumber);
            rs=	ps.executeQuery();
            if(rs.next()) {
                ct=new CarTable(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            }
            return ct;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return ct;
    }

    @Override
    public int insertBorrowTableByBorrowCar(CarTable car, String username,int day) {
        // TODO Auto-generated method stub
        conn=JDBC.getConnection();
        int count=0;
        try {
            ps=conn.prepareStatement("insert into borrowtable values (?,?,?,?,?,?,?,?,sysdate,(sysdate+"+day+"))");
            ps.setString(1, car.getCarNumber());
            ps.setString(2, username);
            ps.setString(3, car.getCarBrand());
            ps.setString(4, car.getCarType());
            ps.setString(5, car.getCarColor());
            ps.setString(6, car.getCarAutoOrHand());
            ps.setInt(7, car.getCarRent());
            ps.setInt(8, day);
            count=ps.executeUpdate();
            return count;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, null);
        }
        return count;
    }

    @Override
    public List<org.car.entity.BorrowTable> selectBorrowTableByBorrowCar(String username) {
        // TODO Auto-generated method stub
        List<org.car.entity.BorrowTable> list=new ArrayList<>();
        conn=JDBC.getConnection();
        try {
            ps=conn.prepareStatement("select * from borrowtable where username=?");
            ps.setString(1, username);
            rs=ps.executeQuery();
            while(rs.next()) {
                CarTable car=new CarTable(rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
                org.car.entity.BorrowTable br=new org.car.entity.BorrowTable(car, rs.getString(2), rs.getInt(8), rs.getString(9), rs.getString(10));
                list.add(br);
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return list;
    }

    @Override
    public int deleteCarTableByUser(String carnumber) {
        // TODO Auto-generated method stub
        conn=JDBC.getConnection();
        int count=0;
        try {
            ps=conn.prepareStatement("delete from cartable where carnumber=?");
            ps.setString(1, carnumber);
            count=ps.executeUpdate();
            return count;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, null);
        }
        return count;
    }

    @Override
    public CarTable selectBorrowTableByBorrowCarForReturn(String carnumber) {
        // TODO Auto-generated method stub
        conn=JDBC.getConnection();
        CarTable ct=null;
        try {
            ps=conn.prepareStatement("select * from borrowtable where carnumber=?");
            ps.setString(1, carnumber);
            rs=ps.executeQuery();
            if(rs.next()) {
                ct=new CarTable(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }
            return ct;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ct;
    }

    @Override
    public int insertCartableByBorrowCar(CarTable car) {
        conn=JDBC.getConnection();
        int count=0;

        try {
            ps=conn.prepareStatement("insert into cartable values (?,?,?,?,?,?)");
            ps.setString(1, car.getCarNumber());
            ps.setString(2, car.getCarBrand());
            ps.setString(3, car.getCarType());
            ps.setString(4, car.getCarColor());
            ps.setString(5, car.getCarAutoOrHand());
            ps.setInt(6, car.getCarRent());
            count=ps.executeUpdate();

            return count;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, null);
        }
        return count;

    }

    @Override
    public int deleteBorrowCarByBorrowCar(String carnumber) {
        // TODO Auto-generated method stub
        conn=JDBC.getConnection();
        int count=0;
        try {
            ps=conn.prepareStatement("delete from borrowtable where carnumber=?");
            ps.setString(1, carnumber);
            count=ps.executeUpdate();
            return count;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, null);
        }
        return count;
    }

    @Override
    public List<CarTable> selectCarTableByUserForall() {
        // TODO Auto-generated method stub
        List<CarTable> list=new ArrayList<>();
        conn=JDBC.getConnection();
        try {
            ps=conn.prepareStatement("select * from cartable");
            rs=ps.executeQuery();
            while(rs.next()) {
                CarTable car=new CarTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                list.add(car);
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<BorrowTable> selectBorrowTableByBorrowCar(CarTable car) {
        // TODO Auto-generated method stub
        conn=JDBC.getConnection();
        BorrowTable borrowcar=null;
        CarTable car1=null;
        List<BorrowTable> list=new ArrayList<>();
        String carNumber=car.getCarNumber();
        String carType=car.getCarType();
        String carBrand=car.getCarBrand();
        String carAutoOrHand=car.getCarAutoOrHand();
        String sql="select * from borrowtable";
        if(!"".equals(carNumber)) {
            sql+=" where carnumber='"+carNumber+"'";

        }
        if(!"所有".equals(carType)) {
            if(sql.indexOf("where")>=0) {
                sql+=" and cartype like '%"+carType+"%'";
            }else {
                sql+=" where cartype like '%"+carType+"%'";

            }

        }
        if(!"所有".equals(carBrand)) {
            if(sql.indexOf("where")>=0) {
                sql+=" and carBrand like '%"+carBrand+"%'";
            }else {
                sql+=" where carBrand like '%"+carBrand+"%'";
            }

        }
        if(!"所有".equals(carAutoOrHand)) {
            if(sql.indexOf("where")>=0) {
                sql+=" and carAutoOrHand like '%"+carAutoOrHand+"%'";
            }else {
                sql+=" where carAutoOrHand like '%"+carAutoOrHand+"%'";
            }

        }
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()) {
                car1=new CarTable(rs.getString(1), rs.getString(3), rs.getString(4),  rs.getString(5),  rs.getString(6), rs.getInt(7));
                borrowcar=new BorrowTable(car1,rs.getString(2),rs.getInt(8),rs.getString(9),rs.getString(10));
                list.add(borrowcar);
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return list;
    }

    @Override
    public String selectUsertableForCarByUser(String username) {
        // TODO Auto-generated method stub
        String email=null;
        conn=JDBC.getConnection();
        try {
            ps=conn.prepareStatement("select email from usertableforcar where username=?");
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()) {
                email=rs.getString(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return email;
    }

    @Override
    public int updateUsertableForCarByAlterPwd(String username,String password) {
        // TODO Auto-generated method stub
        conn=JDBC.getConnection();
        int count=0;
        try {
            ps=conn.prepareStatement("update usertableforcar set password=? where username=?");
            ps.setString(1, password);
            ps.setString(2, username);
            count=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, null);
        }
        return count;
    }

    @Override
    public String selectUsertableForCarByLoginByEmail(String email) {
        conn=JDBC.getConnection();
        String username=null;
        try {
            ps=conn.prepareStatement("select username from usertableforcar where email=?");
            ps.setString(1, email);
            rs=ps.executeQuery();
            if(rs.next()) {
                username=rs.getString(1);
            }
            return username;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return username;
    }

    @Override
    public UsertableForCar selectUsertableForCarByRegister(String email) {
        conn=JDBC.getConnection();
        UsertableForCar uc=null;
        try {
            ps=conn.prepareStatement("select * from usertableforcar where email=?");
            ps.setString(1, email);
            rs=ps.executeQuery();
            if(rs.next()) {
                uc=new UsertableForCar(rs.getString(1),rs.getString(2),rs.getString(3));
            }
            return uc;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBC.release(conn, ps, rs);
        }
        return uc;
    }








}
