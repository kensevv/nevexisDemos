package com.nevexis.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
public class Currency {

	@Id
	private String currencyName;

	
	public Currency() {
		super();
	}

	public Currency(String currencyName) {
		super();
		this.currencyName = currencyName;
	}
	
	public Currency(Currency oth) {
		setCurrencyName(oth.getCurrencyName());
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}


}
