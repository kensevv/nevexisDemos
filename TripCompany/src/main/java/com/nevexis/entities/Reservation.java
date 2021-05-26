package com.nevexis.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Reservation extends BaseEntity {
	private Integer seatNumber;
	private BigDecimal price;
	@ManyToOne
	private City fromCity;
	@ManyToOne
	private City toCity;
	private LocalDate leavingDate;
	private LocalDate arrivingDate;
	@ManyToOne
	private Trip trip;
	
	public Reservation() {}
	
	public Reservation(Trip trip, City fromCity, City toCity, LocalDate leavingDate,
			LocalDate arrivingDate, Integer seatNumber, BigDecimal price) {
		this.seatNumber = seatNumber;
		this.price = price;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.leavingDate = leavingDate;
		this.arrivingDate = arrivingDate;
		this.trip = trip;
	}
	public Integer getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public City getFromCity() {
		return fromCity;
	}
	public void setFromCity(City fromCity) {
		this.fromCity = fromCity;
	}
	public City getToCity() {
		return toCity;
	}
	public void setToCity(City toCity) {
		this.toCity = toCity;
	}
	public LocalDate getLeavingDate() {
		return leavingDate;
	}
	public void setLeavingDate(LocalDate leavingDate) {
		this.leavingDate = leavingDate;
	}
	public LocalDate getArrivingDate() {
		return arrivingDate;
	}
	public void setArrivingDate(LocalDate arrivingDate) {
		this.arrivingDate = arrivingDate;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
}
