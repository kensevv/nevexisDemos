package com.nevexis.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Transition.getAllTransitions",query = "Select t FROM Transition t")
public class Transition extends BaseEntity {
	@ManyToOne
	private WeekDays weekday;
	private String hourTime;
	
	private Integer orderColumn;
	
	@ManyToOne
	private City city;
	
	@ManyToOne
	private Trip trip;

	public WeekDays getWeekday() {
		return weekday;
	}

	public String getHourTime() {
		return hourTime;
	}

	public Integer getOrderColumn() {
		return orderColumn;
	}

	public City getCity() {
		return city;
	}

	public Trip getTrip() {
		return trip;
	}
}
