package com.kensev.cruds;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.kensev.entitites.Clients;

public class ClientsCRUDTest{
	Clients dummyClients;
	Clients output_clients;
	ClientsCRUD dummyClientsCRUD;
	Date today;
	
	@Before
	public void setup() {
		today = new Date(System.currentTimeMillis());
		dummyClients = new Clients("1234567890","pepe","popo","email@gmail.com","0888384427",today,"987654321");
		dummyClientsCRUD = new ClientsCRUD();
		
		try {
			dummyClientsCRUD.addClient(dummyClients);
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
		
		try {
			output_clients = dummyClientsCRUD.getClientById(dummyClients.getID());
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
		
	}
	@Test
	public void addClientsTest() {		
		assertEquals("ID matches",dummyClients.getID(),output_clients.getID());
		assertEquals("First Name matches",dummyClients.getFirst_name(),output_clients.getFirst_name());
		assertEquals("Last Name matches",dummyClients.getLast_name(),output_clients.getLast_name());
		assertEquals("Email matches",dummyClients.getEmail(),output_clients.getEmail());
		assertEquals("Phone matches",dummyClients.getPhone(),output_clients.getPhone());
		assertEquals("Date matches",dummyClients.getBirthday().toString(),output_clients.getBirthday().toString());
		assertEquals("Licence matches",dummyClients.getDriver_lic(),output_clients.getDriver_lic());
		
		try {
			dummyClientsCRUD.removeClient(dummyClients.getID());
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
	}
	
	@Test
	public void removeClientsTest() {
		try {
			dummyClientsCRUD.removeClient(dummyClients.getID());
			output_clients = dummyClientsCRUD.getClientById(dummyClients.getID());
		} 
		catch (SQLException e) {}
		catch (InterruptedException f) {}
		
		assertEquals("Removed vehicle",null,output_clients);
	}
	
	@Test
	public void updateClients() {
		dummyClients.setFirst_name("peepee");
		dummyClients.setLast_name("poopoo");
		dummyClients.setEmail("email1@gmail.com");
		dummyClients.setPhone("0888702245");
		dummyClients.setDriver_lic("123456780");
		
		try {
			dummyClientsCRUD.updateClient(dummyClients);
			output_clients = dummyClientsCRUD.getClientById("1234567890");
		} 
		catch (SQLException e) {}
		catch (InterruptedException f) {}
		
		assertEquals("ID matches",dummyClients.getID(),output_clients.getID());
		assertEquals("First Name matches","peepee",output_clients.getFirst_name());
		assertEquals("Last Name matches","poopoo",output_clients.getLast_name());
		assertEquals("Email matches","email1@gmail.com",output_clients.getEmail());
		assertEquals("Phone matches","0888702245",output_clients.getPhone());
		assertEquals("Licence matches","123456780",output_clients.getDriver_lic());
		
		try {
			dummyClientsCRUD.removeClient(dummyClients.getID());
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
	}
}