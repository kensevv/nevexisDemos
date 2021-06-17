package com.nevexis.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CryptoPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "crypto_timestamp")
	private Timestamp timestamp;
	
	@Column(name = "crypto_currencyId",length = 20)
	private String currencyName;
	
	@Column(name = "crypto_marketID",length = 64)
	private String market;
	
	public CryptoPrimaryKey() {
	}
	
	public CryptoPrimaryKey(CryptoPrimaryKey oth) {
		setTimestamp(oth.getTimestamp());
		setCurrencyName(oth.getCurrencyName());
		setMarket(oth.getMarket());
		
	}
	
	public CryptoPrimaryKey(Timestamp timestamp, String currencyID, String market) {
		this.timestamp = timestamp;
		this.currencyName = currencyID;
		this.market = market;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}


	
}
