package com.kensev.entitites;

public class Vehicle {
	protected String license_plate;
	protected String model;
	protected String insurance;
	protected boolean is_available;
	protected int mileage;
	protected double price;
	protected String branch_name;
	
	public Vehicle() {}
	
	public Vehicle(String license_plate, String model, String insurance, boolean is_available, int mileage,
			double price, String branch_name) {
		super();
		this.license_plate = license_plate;
		this.model = model;
		this.insurance = insurance;
		this.is_available = is_available;
		this.mileage = mileage;
		this.price = price;
		this.branch_name = branch_name;
	}

	

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getLicense_plate() {
		return license_plate;
	}

	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
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

	public boolean getIs_available() {
		return is_available;
	}

	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
