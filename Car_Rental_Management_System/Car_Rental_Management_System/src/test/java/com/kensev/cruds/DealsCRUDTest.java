package com.kensev.cruds;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.kensev.entitites.Account;
import com.kensev.entitites.Deals;

public class DealsCRUDTest{
	Deals dummyDeals;
	Deals output_deals;
	DealsCRUD dummyDealsCRUD;
	Account dummyAccount;
	Date today;
	
	@Before
	public void setup() {
		Date today = new Date(System.currentTimeMillis());
		dummyDeals = new Deals(today,"0046054065","0266845768","BD 8888 OP","Sofia",today,100.0);
		dummyDealsCRUD = new DealsCRUD();
		
		try {
			dummyDealsCRUD.addDeal(dummyDeals);
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
		
		try {
			output_deals = dummyDealsCRUD.getDealById(today, "0046054065", "0266845768", "BD 8888 OP", "Sofia");
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
		
	}
	@Test
	public void addDealTest() {		
		assertEquals("Start Date Matches",dummyDeals.getStart_date().toString(),output_deals.getStart_date().toString());
		assertEquals("End Date matches",dummyDeals.getEnd_date().toString(),output_deals.getEnd_date().toString());
		assertEquals("Employee ID Matches",dummyDeals.getEmployee_id(),output_deals.getEmployee_id());
		assertEquals("Client ID matches",dummyDeals.getClient_id(),output_deals.getClient_id());
		assertEquals("Licence plate matches",dummyDeals.getVehicle_licPlate(),output_deals.getVehicle_licPlate());
		assertEquals("Branch name matches",dummyDeals.getBranch_name(),output_deals.getBranch_name());
		assertEquals("Payment matches",dummyDeals.getPayment(),output_deals.getPayment(),0.1);
		
		try {
			dummyDealsCRUD.removeDeal(today, "0046054065", "0266845768", "BD 8888 OP", "Sofia");
		} 
		catch (SQLException e) {} 
		catch (InterruptedException f) {}
	}
	
	@Test
	public void removeDealTest() {
		try {
			dummyDealsCRUD.removeDeal(today, "0046054065", "0266845768", "BD 8888 OP", "Sofia");
			output_deals = dummyDealsCRUD.getDealById(today, "0046054065", "0266845768", "BD 8888 OP", "Sofia");
		} 
		catch (SQLException e) {}
		catch (InterruptedException f) {}
		
		assertEquals("Removed vehicle",null,output_deals);
	}
}