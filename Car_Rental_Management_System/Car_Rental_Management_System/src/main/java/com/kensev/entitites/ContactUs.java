package com.kensev.entitites;

public class ContactUs {
	private Integer ID;
	private String name;
	private String email;
	private String message;
	
	public ContactUs() {}

	public ContactUs(String name, String email, String message) {
		this.name = name;
		this.email = email;
		this.message = message;
	}

	public ContactUs(Integer ID, String name, String email, String message) {
		this.ID = ID;
		this.name = name;
		this.email = email;
		this.message = message;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
