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
	}
}