package com.kensev.entitites;

public class Branches {
	private String name;
	private String address;
	private String managerId;

	public Branches(String name, String address, String managerId) {
		this.name = name;
		this.address = address;
		this.managerId = managerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Branches [name=" + name + ", address=" + address + ", managerId=" + managerId + "]";
	}

}
