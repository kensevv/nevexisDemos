package com.nevexis.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import com.google.gson.Gson;

@Entity
@Table(name = "crypto_data")
public class CryptoData {
	
	
	public CryptoData(String type, Timestamp timestamp, Double open, Double high, Double low, Double close, Double vwap,
			Double volume, Integer count) {
		super();
		this.type = type;
		this.timestamp = timestamp;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.vwap = vwap;
		this.volume = volume;
		this.count = count;
	}
	public CryptoData() {
		super();
	}
	
	@Id
	private String type;
	@Id
	private Timestamp timestamp;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double vwap;
	private Double volume;
	private Integer count;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	public Double getVwap() {
		return vwap;
	}
	public void setVwap(Double vwap) {
		this.vwap = vwap;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}