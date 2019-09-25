package org.car.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.car.entity.BorrowTable;
import org.car.entity.CarTable;
import org.car.entity.UsertableForCar;

public interface UsertableForCarDao {
    /**
     * 向usertableforcar表里插入元素
     * @param user
     * @return
     */
    public int insertUsertableForCar(UsertableForCar user);
    /**
     * 在注册页面查询usertableforcar表
     * @param user
     * @return
     */
    public UsertableForCar selectUsertableForCarByRegister(UsertableForCar user);
    /**
     * 在登录页面查询usertableforcar表
     * @param user
     * @return
     */
    public UsertableForCar selectUsertableForCarByLogin(UsertableForCar user);
    /**
     * 在用户页面查询cartable表
     * @param car
     * @return
     */
    public List<CarTable> selectCarTableByUser1(CarTable car);
    /**
     * 在租车后查询整个cartable表
     * @return
     */
    public List<CarTable> selectCarTableByUserForall();
    /**
     *用车牌号查询车辆信息
     * @param carnumber
     * @return
     */
    public CarTable selectCarTableByBorrowCar(String carnumber);
    /**
     * 向borrowtable表里插入数据
     * @return
     */
    public int insertBorrowTableByBorrowCar(CarTable car, String username, int day);
    /**
     * 用用户名向borrowtable表里查询数据
     * @return
     */
    public List<BorrowTable> selectBorrowTableByBorrowCar(String username);
    /**
     * 用车牌号删除cartable表的数据
     * @param carnumber
     * @return
     */
    public int deleteCarTableByUser(String carnumber);
    /**
     * 用用户名向borrowtable表里查询数据用于还车
     * @param carnumber
     * @return
     */
    public CarTable selectBorrowTableByBorrowCarForReturn(String carnumber);
    /**
     * 向cartable表中插入数据
     * @param carnumber
     * @return
     */
    public int insertCartableByBorrowCar(CarTable car);
    /**
     * 用用户名删除borrowtable的数据
     * @param carnumber
     * @return
     */
    public int deleteBorrowCarByBorrowCar(String carnumber);
    /**
     * 在租车页面查询borrowable表
     * @param car
     * @return
     */
    public List<BorrowTable> selectBorrowTableByBorrowCar(CarTable car);
    /**
     * 用户号名查询邮箱
     * @param username
     * @return
     */
    public String selectUsertableForCarByUser(String username);
    /**
     *
     * 在AlterPwd页面修改密码
     * @param username
     * @return
     */
    public int updateUsertableForCarByAlterPwd(String username, String password);
    /**
     * 用邮箱查询用户名
     * @return
     */
    public String selectUsertableForCarByLoginByEmail(String email);
    /**
     * 查询是否由重复的邮箱
     * @param email
     * @return
     */
    public UsertableForCar selectUsertableForCarByRegister(String email);
}
