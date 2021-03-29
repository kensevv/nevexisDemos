package com.kensev.entitites;

public class Vehicle {
	protected String licPlate;
	protected String model;
	protected String insurance;
	protected boolean isAvailable;
	protected int milleage;
	protected double price;
	public Vehicle(String licPlate)
	{
		this.licPlate = licPlate;
	}
	public Vehicle(String licPlate, String model, String insurance, boolean isAvailable,
			int milleage, double price)
	{
		this.licPlate = licPlate;
		this.model = model;
		this.insurance = insurance;
		this.isAvailable = isAvailable;
		this.milleage = milleage;
		this.price = price;
	}

	public String getLicPlate() {
		return licPlate;
	}

	public void setLicPlate(String licPlate) {
		this.licPlate = licPlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getMilleage() {
		return milleage;
	}

	public void setMilleage(int milleage) {
		this.milleage = milleage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
