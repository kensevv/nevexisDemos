package com.kensev.entitites;

import java.sql.Date;

public class Employees {
	private String ID;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	private Date birthday;
	private String work_number;
	private String branch_name;
	private String manager_id;

	public Employees(String id, String firstName, String lastName, String email, String phone, Date birthday,
			String workNumber, String branchName, String managerId) {
		this.ID = id;
		this.first_name = firstName;
		this.last_name = lastName;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.work_number = workNumber;
		this.branch_name = branchName;
		this.manager_id = managerId;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getWork_number() {
		return work_number;
	}

	public void setWork_number(String work_number) {
		this.work_number = work_number;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	@Override
	public String toString() {
		return "Employees [ID=" + ID + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", phone=" + phone + ", birthday=" + birthday + ", work_number=" + work_number + ", branch_name="
				+ branch_name + ", manager_id=" + manager_id + "]";
	}
}
