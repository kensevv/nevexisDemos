package com.kensev.entitites;

import java.sql.Date;

public class Deals {
	private Date start_date;
	private String client_id;
	private String employee_id;
	private String vehicle_licPlate;
	private String branch_name;
	private Date end_date;
	private Double payment;

	public Deals(Date startDate, String clientId, String emlpoyeeId, String vehicleLicPlate, String branchName,
			Date endDate, Double payment) {
		this.start_date = startDate;
		this.client_id = clientId;
		this.employee_id = emlpoyeeId;
		this.vehicle_licPlate = vehicleLicPlate;
		this.branch_name = branchName;
		this.end_date = endDate;
		this.payment = payment;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String emlpoyee_id) {
		this.employee_id = emlpoyee_id;
	}

	public String getVehicle_licPlate() {
		return vehicle_licPlate;
	}

	public void setVehicle_licPlate(String vehicle_licPlate) {
		this.vehicle_licPlate = vehicle_licPlate;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	@Override
	public String toString() {
		return "Deals [startDate=" + start_date + ", clientId=" + client_id + ", emlpoyeeId=" + employee_id
				+ ", vehicleLicPlate=" + vehicle_licPlate + ", branchName=" + branch_name + ", endDate=" + end_date
				+ ", payment=" + payment + "]";
	}
}
