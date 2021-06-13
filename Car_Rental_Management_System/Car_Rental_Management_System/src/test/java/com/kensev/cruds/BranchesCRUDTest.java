package com.kensev.cruds;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.kensev.entitites.Account;
import com.kensev.entitites.Branches;

public class BranchesCRUDTest{
	Branches dummyBranch;
	Branches output_branch;
	BranchesCRUD dummyBranchCRUD;
	Account dummyAccount;
	
	@Before
	public void setup() {
		dummyBranch = new Branches("Mezdra","Garata","0046054065");
		dummyBranchCRUD = new BranchesCRUD();
		dummyAccount=new Account();
		
		
		try {
			dummyAccount.login("kensev2000@gmail.com", "test");
		} catch (SQLException e) {}
		
		
		try {
			dummyBranchCRUD.addBranch(dummyBranch);
		} catch (SQLException e) {}
		catch (InterruptedException f) {}
		
		
		try {
			output_branch = dummyBranchCRUD.getBranchById("Mezdra");
		} catch (SQLException e) {}
		catch (InterruptedException f) {}
	}
	
	@Test
	public void addBranchTest() {		
		assertEquals("Address matches",dummyBranch.getAddress(),output_branch.getAddress());
		assertEquals("Manager ID matches",dummyBranch.getManagerId(),output_branch.getManagerId());
		assertEquals("Name matches",dummyBranch.getName(),output_branch.getName());
		
		try {
			dummyBranchCRUD.removeBranch(dummyBranch.getName());
		} catch (SQLException e) {}
		catch (InterruptedException f) {}
	}
	
	@Test
	public void removeBranchTest() {
		try {
			dummyBranchCRUD.removeBranch(dummyBranch.getName());
			output_branch = dummyBranchCRUD.getBranchById("Mezdra");
		} catch (SQLException e) {}
		catch (InterruptedException f) {}
		
		assertEquals("Removed vehicle",null,output_branch);
	}
	
	@Test
	public void updateBranchTest() {
		dummyBranch.setAddress("Poshtata");
		dummyBranch.setManagerId("0268721968");
		
		try {
			dummyBranchCRUD.updateBranch(dummyBranch);
			output_branch = dummyBranchCRUD.getBranchById("Mezdra");
		} catch (SQLException e) {}
		catch (InterruptedException f) {}
		
		assertEquals("Address matches",dummyBranch.getAddress(),output_branch.getAddress());
		assertEquals("Manager ID Matches","0268721968",output_branch.getManagerId());
		
		
		try {
			dummyBranchCRUD.removeBranch(dummyBranch.getName());
		} catch (SQLException e) {}
		catch (InterruptedException f) {}
	}
}