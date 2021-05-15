package com.reporting.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Persons.getPersonsCount", query = "SELECT COUNT(p) FROM Persons p")
@NamedQuery(name = "Persons.getPersons", query = "SELECT p FROM Persons p")
public class Persons {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String names;
	
	public Persons() {};
	
	public Persons(Integer id, String names) {
		this.id = id;
		this.names = names;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
}