package com.kensev.entitites;

import java.sql.Date;
import java.time.LocalDate;

public class Clients {
	private String Id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date birthday;
	private String driverLic;

	public Clients(String id, String firstName, String lastName, String email, String phone, Date birthday,
			String driverLic) {
		this.Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.driverLic = driverLic;
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

	public String getDriverLic() {
		return driverLic;
	}

	public void setDriverLic(String driverLic) {
		this.driverLic = driverLic;
	}

	@Override
	public String toString() {
		return "Clients [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", birthday=" + birthday + ", driverLic=" + driverLic + "]";
	}

}
