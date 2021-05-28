package com.kensev.cruds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kensev.connection.ConnectionPool;
import com.kensev.entitites.Branches;

public class BranchesCRUD {

	public List<Branches> getAllBranches() throws SQLException, InterruptedException {
		List<Branches> allBranches = new ArrayList<>(50);
		try (Connection connection = ConnectionPool.getConnection();
				Statement prepStatement = connection.createStatement();
				ResultSet resultSet = prepStatement.executeQuery("SELECT * FROM FN71947.BRANCHES");) {
			while (resultSet.next()) {
				allBranches.add(new Branches(resultSet.getString("NAME"), resultSet.getString("ADDRESS"),
						resultSet.getString("MANAGER_ID")));
			}
		}
		return allBranches;
	}

	public void addBranch(Branches newBranch) throws SQLException, InterruptedException {
		try (Connection connection = ConnectionPool.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {
			try (ResultSet rs = statement.executeQuery("SELECT * FROM FN71947.BRANCHES WHERE 1<>1");) {
				rs.moveToInsertRow();
				rs.updateString("NAME", newBranch.getName());
				rs.updateString("ADDRESS", newBranch.getAddress());
				rs.updateString("MANAGER_ID", newBranch.getManagerId());
				rs.insertRow();
			}
		}
	}

	public void removeBranch(String branchName) throws SQLException, InterruptedException {
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection.prepareStatement(
						"SELECT * FROM FN71947.BRANCHES WHERE NAME = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			prepStatement.setString(1, branchName);
			try (ResultSet rs = prepStatement.executeQuery()) {
				while (rs.next()) {
					rs.deleteRow();
				}
			}
		}
	}

	public void updateBranch(Branches branch) throws SQLException, InterruptedException {

		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM FN71947.BRANCHES WHERE NAME = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {
			statement.setString(1, branch.getName());
			try (ResultSet rs = statement.executeQuery();) {
				if (rs.next()) {
					rs.updateString("NAME", branch.getName());
					rs.updateString("ADDRESS", branch.getAddress());
					rs.updateString("MANAGER_ID", branch.getManagerId());
					rs.updateRow();
				}
			}
		}
	}

	public Branches getBranchById(String branchName) throws SQLException, InterruptedException {
		Branches foundBranch = null;
		try (Connection connection = ConnectionPool.getConnection();
				PreparedStatement prepStatement = connection
						.prepareStatement("SELECT * FROM FN71947.BRANCHES WHERE NAME = ?")) {
			prepStatement.setString(1, branchName);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					foundBranch = new Branches(resultSet.getString("NAME"), resultSet.getString("ADRESS"),
							resultSet.getString("MANAGER_ID"));
				}
			}
		}
		return foundBranch;
	}
}
