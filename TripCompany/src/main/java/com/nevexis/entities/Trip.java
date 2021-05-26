package com.nevexis.entities;

import javax.persistence.Entity;

@Entity
public class Trip extends BaseEntity {
	private String startPoint;
	private String endPoint;
	
	public String getStartPoint() {
		return startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	
	
}
