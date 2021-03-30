package com.nevexis.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;

	@SuppressWarnings("unused")
	private String manufacturer;
	@SuppressWarnings("unused")
	private String model;
	@SuppressWarnings("unused")
	private Integer mileage;
}
