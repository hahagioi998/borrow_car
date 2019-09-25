package org.car.controller;

import java.util.List;

import org.car.entity.BorrowTable;
import org.car.entity.CarTable;
import org.car.entity.UsertableForCar;

public interface UsertableForCarController {
    /**
     * 向usertableforcar表里插入元素
     * @param user
     * @return
     */
    public boolean insertUsertableForCar(UsertableForCar user);
    /**
     * 在注册页面查询usertableforcar表
     * @param user
     * @return
     */
    public boolean selectUsertableForCarByRegister(UsertableForCar user);
    /**
     * 在登录页面查询usertableforcar表
     * @param user
     * @return
     */
    public boolean selectUsertableForCarByLogin(UsertableForCar user);
    /**
     * 用车牌号删除cartable表的数据
     * @param carnumber
     * @return
     */
    public boolean deleteCarTableByUser(String carnumber);
    /**
     * 向borrowtable表里插入数据
     * @return
     */
    public boolean insertBorrowTableByBorrowCar(CarTable car, String username, int day);
    /**
     * 用用户名向borrowtable表里查询数据用于还车
     * @param carnumber
     * @return
     */
    public CarTable selectBorrowTableByBorrowCarForReturn(String carnumber) ;
    /**
     * 向cartable表中插入数据
     * @param carnumber
     * @return
     */
    public boolean insertCartableByBorrowCar(CarTable car);
    /**
     * 用用户名删除borrowtable的数据
     * @param carnumber
     * @return
     */
    public boolean deleteBorrowCarByBorrowCar(String carnumber);
    /**
     * 在租车后查询整个cartable表
     * @return
     */
    public List<CarTable> selectCarTableByUserForall();
    /**
     * 在用户页面查询cartable表
     * @param car
     * @return
     */
    public List<CarTable> selectCarTableByUser1(CarTable car);
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
    public boolean selectUsertableForCarByUser(String username, String message);
    /**
     *
     * 在AlterPwd页面修改密码
     * @param username
     * @return
     */
    public boolean updateUsertableForCarByAlterPwd(String username, String password);
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
    public boolean selectUsertableForCarByRegister(String email);
}
