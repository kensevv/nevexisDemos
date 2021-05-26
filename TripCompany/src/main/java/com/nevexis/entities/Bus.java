package com.nevexis.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Bus extends BaseEntity{
	private String regNumber;
	private Integer numberSeats;
	
	@ManyToOne
	private Trip trip;
	
	@ManyToMany
	private Set<Driver> drivers;
}
