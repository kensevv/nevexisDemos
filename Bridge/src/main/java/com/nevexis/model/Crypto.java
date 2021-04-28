package com.nevexis.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "crypto")
public class Crypto {

	@EmbeddedId
	CryptoPrimaryKey id;

	@MapsId("currencyName")
	@ManyToOne
	@JoinColumn(name = "crypto_currencyId")
	private Currency currency;

	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private BigDecimal average;
	private BigDecimal volume;
	private Integer trades;

	public Timestamp getTimestamp() {
		return id.getTimestamp();
	}

	public void setTimestamp(Timestamp timestamp) {
		id.setTimestamp(timestamp);
	}

	public String getCurrencyName() {
		return id.getCurrencyName();
	}

	public void setCurrencyName(String currencyName) {
		id.setCurrencyName(currencyName);
	}

	public String getMarket() {
		return id.getMarket();
	}

	public void setMarket(String market) {
		id.setMarket(market);
	}

	public CryptoPrimaryKey getId() {
		return id;
	}

	public void setId(CryptoPrimaryKey id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getAverage() {
		return average;
	}

	public void setAverage(BigDecimal average) {
		this.average = average;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public Integer getTrades() {
		return trades;
	}

	public void setTrades(Integer trades) {
		this.trades = trades;
	}

}
