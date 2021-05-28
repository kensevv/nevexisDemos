package com.kensev.entitites;

import java.sql.Date;

public class Employees {
	private String Id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date birthday;
	private String workNumber;
	private String branchName;
	private String managerId;

	public Employees(String id, String firstName, String lastName, String email, String phone, Date birthday,
			String workNumber, String branchName, String managerId) {
		this.Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.workNumber = workNumber;
		this.branchName = branchName;
		this.managerId = managerId;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Employees [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", birthday=" + birthday + ", workNumber=" + workNumber + ", branchName="
				+ branchName + ", managerId=" + managerId + "]";
	}
	
}
