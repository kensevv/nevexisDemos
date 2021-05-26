package com.nevexis.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "City.getAllCities", query = "SELECT c FROM City c")
@NamedQuery(name = "City.getCityByName", query = "SELECT c FROM City c WHERE c.name = :cityName")
public class City extends BaseEntity{
	private String name;

	public City() {}
	public City(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
