package com.nevexis.bot.jsonmodels;

public class JsonAddOrder implements JsonMessage {
	private String event;
	private String ordertype;
	private String pair;
	private String price;
	private String token;
	private String type;
	private String volume;
	
	public JsonAddOrder(String event, String ordertype, String pair, String price, String token, String type,
			String volume) {
		super();
		this.event = event;
		this.ordertype = ordertype;
		this.pair = pair;
		this.price = price;
		this.token = token;
		this.type = type;
		this.volume = volume;
	}

	public String getEvent() {
		return event;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public String getPair() {
		return pair;
	}

	public String getPrice() {
		return price;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public String getVolume() {
		return volume;
	}
}
