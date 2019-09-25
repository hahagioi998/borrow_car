package org.car.entity;


public class BorrowTable {
    private CarTable car;
    private String username;
    private int rentDay;
    private String borrowTime;
    private String returnTime;
    public BorrowTable() {
    	
    }
    public BorrowTable(CarTable car,String username,int rentDay,String borrowTime,String returnTime) {
    	this.car=car;
    	this.username=username;
    	this.rentDay=rentDay;
    	this.borrowTime=borrowTime;
    	this.returnTime=returnTime;
    }
	public CarTable getCar() {
		return car;
	}
	public void setCar(CarTable car) {
		this.car = car;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRentDay() {
		return rentDay;
	}
	public void setRentDay(int rentDay) {
		this.rentDay = rentDay;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
    
}
