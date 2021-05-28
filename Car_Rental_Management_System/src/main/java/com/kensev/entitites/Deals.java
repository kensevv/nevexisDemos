package com.kensev.entitites;

import java.sql.Date;
import java.time.LocalDate;

public class Deals {
	@Override
	public String toString() {
		return "Deals [startDate=" + startDate + ", clientId=" + clientId + ", emlpoyeeId=" + emlpoyeeId
				+ ", vehicleLicPlate=" + vehicleLicPlate + ", branchName=" + branchName + ", endDate=" + endDate
				+ ", payment=" + payment + "]";
	}

	private Date startDate;
	private String clientId;
	private String emlpoyeeId;
	private String vehicleLicPlate;
	private String branchName;
	private Date endDate;
	private Double payment;

	public Deals(Date startDate, String clientId, String emlpoyeeId, String vehicleLicPlate, String branchName,
			Date endDate, Double payment) {
		this.startDate = startDate;
		this.clientId = clientId;
		this.emlpoyeeId = emlpoyeeId;
		this.vehicleLicPlate = vehicleLicPlate;
		this.branchName = branchName;
		this.endDate = endDate;
		this.payment = payment;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getEmlpoyeeId() {
		return emlpoyeeId;
	}

	public void setEmlpoyeeId(String emlpoyeeId) {
		this.emlpoyeeId = emlpoyeeId;
	}

	public String getVehicleLicPlate() {
		return vehicleLicPlate;
	}

	public void setVehicleLicPlate(String vehicleLicPlate) {
		this.vehicleLicPlate = vehicleLicPlate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

}
