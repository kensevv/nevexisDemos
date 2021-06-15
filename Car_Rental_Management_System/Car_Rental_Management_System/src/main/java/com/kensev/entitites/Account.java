package com.kensev.entitites;

public class Account {

	private String username;
	private String email;
	private String password;
	private String employeeId;
	private Roles role;
	
	public Account() {}
	
	public Account(String username, String email, String password, String employeeId, Roles role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.employeeId = employeeId;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
}
