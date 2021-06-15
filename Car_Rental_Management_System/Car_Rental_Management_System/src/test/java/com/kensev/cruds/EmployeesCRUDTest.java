package com.kensev.cruds;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.kensev.entitites.Account;
import com.kensev.entitites.Employees;

public class EmployeesCRUDTest{
	Employees dummyEmployees;
	Employees output_employees;
	EmployeesCRUD dummyEmployeesCRUD;
	Account dummyAccount;
	Date today;
	
	@Before
	public void setup() {
		today = new Date(System.currentTimeMillis());
		dummyEmployees = new Employees("1234567890","pepe","popo","email@gmail.com","0888384427",today,"987654321","Sofia","0046054065");
		dummyEmployeesCRUD = new EmployeesCRUD();
		
		try {
			dummyEmployeesCRUD.addEmployee(dummyEmployees);
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
		
		try {
			output_employees = dummyEmployeesCRUD.getEmployeeById(dummyEmployees.getID());
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
		
	}
	@Test
	public void addEmployeeTest() {		
		assertEquals("ID matches",dummyEmployees.getID(),output_employees.getID());
		assertEquals("First Name matches",dummyEmployees.getFirst_name(),output_employees.getFirst_name());
		assertEquals("Last Name matches",dummyEmployees.getLast_name(),output_employees.getLast_name());
		assertEquals("Email matches",dummyEmployees.getEmail(),output_employees.getEmail());
		assertEquals("Phone matches",dummyEmployees.getPhone(),output_employees.getPhone());
		assertEquals("Date matches",dummyEmployees.getBirthday().toString(),output_employees.getBirthday().toString());
		assertEquals("Work number matches",dummyEmployees.getWork_number(),output_employees.getWork_number());
		assertEquals("Manager ID matches",dummyEmployees.getManager_id(),output_employees.getManager_id());
		assertEquals("Branch ID matches",dummyEmployees.getBranch_name(),output_employees.getBranch_name());
		
		try {
			dummyEmployeesCRUD.removeEmployee(dummyEmployees.getID());
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
	}
	
	@Test
	public void removeVehicleTest() {
		try {
			dummyEmployeesCRUD.removeEmployee(dummyEmployees.getID());
			output_employees = dummyEmployeesCRUD.getEmployeeById(dummyEmployees.getID());
		} 
		catch (SQLException e) {}
		catch (InterruptedException f) {}
		
		assertEquals("Removed vehicle",null,output_employees);
	}
	
	@Test
	public void updateVehicleTest() {
		dummyEmployees.setFirst_name("peepee");
		dummyEmployees.setLast_name("poopoo");
		dummyEmployees.setEmail("email1@gmail.com");
		dummyEmployees.setPhone("0888702245");
		dummyEmployees.setWork_number("123456780");
		dummyEmployees.setBranch_name("Plovdiv");
		
		try {
			dummyEmployeesCRUD.updateEmployee(dummyEmployees);
			output_employees = dummyEmployeesCRUD.getEmployeeById("1234567890");
		} 
		catch (SQLException e) {}
		catch (InterruptedException f) {}
		
		assertEquals("ID matches",dummyEmployees.getID(),output_employees.getID());
		assertEquals("First Name matches","peepee",output_employees.getFirst_name());
		assertEquals("Last Name matches","poopoo",output_employees.getLast_name());
		assertEquals("Email matches","email1@gmail.com",output_employees.getEmail());
		assertEquals("Phone matches","0888702245",output_employees.getPhone());
		assertEquals("Licence matches","123456780",output_employees.getWork_number());
		assertEquals("Branch matches","Plovdiv",output_employees.getBranch_name());
		
		try {
			dummyEmployeesCRUD.removeEmployee(dummyEmployees.getID());
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
	}
}