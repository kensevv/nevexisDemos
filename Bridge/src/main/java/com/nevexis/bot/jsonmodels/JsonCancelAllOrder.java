package com.nevexis.bot.jsonmodels;

public class JsonCancelAllOrder implements JsonMessage{
	private String event;
	private String token;
	
	public JsonCancelAllOrder(String event, String token) {
		super();
		this.event = event;
		this.token = token;
	}

	public String getEvent() {
		return event;
	}

	public String getToken() {
		return token;
	}
}
