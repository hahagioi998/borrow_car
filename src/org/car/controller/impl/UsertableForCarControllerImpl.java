package org.car.controller.impl;

import java.util.List;

import org.car.controller.UsertableForCarController;
import org.car.dao.UsertableForCarDao;
import org.car.dao.impl.UsertableForCarDeoImpl;
import org.car.entity.BorrowTable;
import org.car.entity.CarTable;
import org.car.entity.UsertableForCar;

public class UsertableForCarControllerImpl implements UsertableForCarController {

    @Override
    public boolean insertUsertableForCar(UsertableForCar user) {
        // TODO Auto-generated method stub
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        int count=ud.insertUsertableForCar(user);
        if(count>0) return true;
        return false;
    }

    @Override
    public boolean selectUsertableForCarByRegister(UsertableForCar user) {
        // TODO Auto-generated method stub
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        if(ud.selectUsertableForCarByRegister(user)!=null) return true;
        return false;
    }

    @Override
    public boolean selectUsertableForCarByLogin(UsertableForCar user) {
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        if(ud.selectUsertableForCarByLogin(user)!=null) return true;
        return false;
    }

    @Override
    public boolean deleteCarTableByUser(String carnumber) {
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        if(ud.deleteCarTableByUser(carnumber)>0) return true;
        else return false;
    }

    @Override
    public boolean insertBorrowTableByBorrowCar(CarTable car, String username, int day) {
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        if(ud.insertBorrowTableByBorrowCar(car, username, day)>0) return true;
        else return false;
    }

    @Override
    public CarTable selectBorrowTableByBorrowCarForReturn(String carnumber) {
        // TODO Auto-generated method stub
        return new UsertableForCarDeoImpl().selectBorrowTableByBorrowCarForReturn(carnumber);
    }

    @Override
    public boolean insertCartableByBorrowCar(CarTable car) {
        // TODO Auto-generated method stub
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        if(ud.insertCartableByBorrowCar(car)>0) return true;
        else return false;
    }

    @Override
    public boolean deleteBorrowCarByBorrowCar(String carnumber) {
        // TODO Auto-generated method stub
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        if(ud.deleteBorrowCarByBorrowCar(carnumber)>0) return true;
        else return false;
    }

    @Override
    public List<CarTable> selectCarTableByUserForall() {
        // TODO Auto-generated method stub
        return new UsertableForCarDeoImpl().selectCarTableByUserForall();
    }

    @Override
    public List<CarTable> selectCarTableByUser1(CarTable car) {
        // TODO Auto-generated method stub
        return new UsertableForCarDeoImpl().selectCarTableByUser1(car);
    }

    @Override
    public List<BorrowTable> selectBorrowTableByBorrowCar(CarTable car) {
        // TODO Auto-generated method stub
        return new UsertableForCarDeoImpl().selectBorrowTableByBorrowCar(car);
    }

    @Override
    public boolean selectUsertableForCarByUser(String username,String email) {
        // TODO Auto-generated method stub
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        if(ud.selectUsertableForCarByUser(username)!=null) {
            if((ud.selectUsertableForCarByUser(username)).equals(email)) return true;
            else return false;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateUsertableForCarByAlterPwd(String username, String password) {
        // TODO Auto-generated method stub
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        if(ud.updateUsertableForCarByAlterPwd(username, password)>0) return true;
        else return false;
    }

    @Override
    public String selectUsertableForCarByLoginByEmail(String email) {
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        String username =ud.selectUsertableForCarByLoginByEmail(email);
        return username;
    }

    @Override
    public boolean selectUsertableForCarByRegister(String email) {
        UsertableForCarDao ud=new UsertableForCarDeoImpl();
        UsertableForCar username=ud.selectUsertableForCarByRegister(email);
        if(username!=null) return true;
        else return false;
    }



}
