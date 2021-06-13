package com.kensev.cruds;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.kensev.entitites.Account;
import com.kensev.entitites.Vehicle;

public class VehicleCRUDTest{
	Vehicle dummyVehicle;
	Vehicle output_vehicle;
	VehicleCRUD dummyVehicleCRUD;
	Account dummyAccount;
	
	@Before
	public void setup() {
		dummyVehicle = new Vehicle("123456789","model1","insurance1",true,1000,100,"Sofia");
		dummyVehicleCRUD = new VehicleCRUD();
		output_vehicle = new Vehicle();
		dummyAccount=new Account();
		
		
		try {
			dummyAccount.login("kensev2000@gmail.com", "test");
		} catch (SQLException e) {}
		
		
		try {
			dummyVehicleCRUD.addVehicle(dummyVehicle);
		} catch (SQLException e) {}
		
		
		try {
			output_vehicle = dummyVehicleCRUD.getVehicle("123456789");
		} catch (SQLException e) {}
	}
	
	@Test
	public void addVehicleTest() {		
		assertEquals("Licence plate matches",dummyVehicle.getLicense_plate(),output_vehicle.getLicense_plate());
		assertEquals("Model matches",dummyVehicle.getModel(),output_vehicle.getModel());
		assertEquals("Insurance matches",dummyVehicle.getInsurance(),output_vehicle.getInsurance());
		assertEquals("Is available matches",dummyVehicle.getIs_available(),output_vehicle.getIs_available());
		assertEquals("Price matches",dummyVehicle.getPrice(),output_vehicle.getPrice(),0.1);
		assertEquals("Mileage matches",dummyVehicle.getMileage(),output_vehicle.getMileage());
		assertEquals("Branch matches",dummyVehicle.getBranch_name(),output_vehicle.getBranch_name());
		
		try {
			dummyVehicleCRUD.removeVehicle(dummyVehicle.getLicense_plate());
		} catch (SQLException e) {}
	}
	
	@Test
	public void removeVehicleTest() {
		try {
			dummyVehicleCRUD.removeVehicle(dummyVehicle.getLicense_plate());
			output_vehicle = dummyVehicleCRUD.getVehicle("123456789");
		} catch (SQLException e) {}
		assertEquals("Removed vehicle",null,output_vehicle);
	}
	
	@Test
	public void updateVehicleTest() {
		dummyVehicle.setInsurance("insurance2");
		dummyVehicle.setModel("model2");
		dummyVehicle.setIs_available(false);
		dummyVehicle.setMileage(1001);
		dummyVehicle.setPrice(101);
		dummyVehicle.setBranch_name("Plovdiv");
		
		try {
			dummyVehicleCRUD.updateVehicle(dummyVehicle);
			output_vehicle = dummyVehicleCRUD.getVehicle("123456789");
		} catch (SQLException e) {}
		
		assertEquals("Licence plate matches",dummyVehicle.getLicense_plate(),output_vehicle.getLicense_plate());
		assertEquals("Model update matches","model2",output_vehicle.getModel());
		assertEquals("Insurance update matches","insurance2",output_vehicle.getInsurance());
		assertEquals("Is available update matches",false,output_vehicle.getIs_available());
		assertEquals("Price update matches",101,output_vehicle.getPrice(),0.1);
		assertEquals("Mileage update matches",1001,output_vehicle.getMileage());
		assertEquals("Branch update matches","Plovdiv",output_vehicle.getBranch_name());
		
		try {
			dummyVehicleCRUD.removeVehicle(dummyVehicle.getLicense_plate());
		} catch (SQLException e) {}
	}
}