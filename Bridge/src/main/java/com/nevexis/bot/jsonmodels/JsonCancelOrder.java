package com.nevexis.bot.jsonmodels;

import java.util.List;

public class JsonCancelOrder implements JsonMessage{
	private String event;
	private String token;
	private List<String> txid;
	
	public JsonCancelOrder(String event, String token, List<String> txid) {
		super();
		this.event = event;
		this.token = token;
		this.txid = txid;
	}

	public String getEvent() {
		return event;
	}

	public String getToken() {
		return token;
	}

	public List<String> getTxid() {
		return txid;
	}
}
