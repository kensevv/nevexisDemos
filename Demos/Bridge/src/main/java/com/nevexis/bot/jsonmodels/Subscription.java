package com.nevexis.bot.jsonmodels;

public class Subscription {
	private String name;
	private String token;
		
	public Subscription(String name, String token) {
		this.name = name;
		this.token = token;
	}
	
	public String getName() {
		return name;
	}
	public String getToken() {
		return token;
	}
}
