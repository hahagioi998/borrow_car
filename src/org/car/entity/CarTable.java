package org.car.entity;

public class CarTable {
    private String carNumber;
    private String carBrand;
    private String carType;
    private String carColor;
    private String carAutoOrHand;
    private int carRent;
    public CarTable() {
    	
    }
    public CarTable(String carNumber,String carBrand,String carType,String carColor,String carAutoOrHand,  int carRent) {
    	this.carNumber=carNumber;
    	this.carBrand=carBrand;
    	this.carType=carType;
    	this.carColor=carColor;
    	this.carAutoOrHand=carAutoOrHand;
    	this.carRent=carRent;
    }
    public CarTable(String carNumber,String carBrand,String carType,String carAutoOrHand) {
    	this.carNumber=carNumber;
    	this.carBrand=carBrand;
    	this.carType=carType;
    	this.carAutoOrHand=carAutoOrHand;
    	
    }
	public String getCarAutoOrHand() {
		return carAutoOrHand;
	}
	public void setCarAutoOrHand(String carAutoOrHand) {
		this.carAutoOrHand = carAutoOrHand;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public int getCarRent() {
		return carRent;
	}
	public void setCarRent(int carRent) {
		this.carRent = carRent;
	}
}
