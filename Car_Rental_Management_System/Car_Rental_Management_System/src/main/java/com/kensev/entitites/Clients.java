package com.kensev.entitites;

import java.sql.Date;

public class Clients {
	private String ID;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	private Date birthday;
	private String driver_lic;
	public Clients(String iD, String first_name, String last_name, String email, String phone, Date birthday,
			String driver_lic) {
		super();
		this.ID = iD;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.driver_lic = driver_lic;
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
	public String getDriver_lic() {
		return driver_lic;
	}
	public void setDriver_lic(String driver_lic) {
		this.driver_lic = driver_lic;
	}
}
