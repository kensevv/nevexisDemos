package com.nevexis.bot.jsonmodels;

public class JsonSubscription implements JsonMessage{
	private String event;
	private Subscription subscription;
	
	public JsonSubscription(String event, Subscription subscription) {
		this.event = event;
		this.subscription = subscription;
	}
	
	public String getEvent() {
		return event;
	}
	public Subscription getSubscription() {
		return subscription;
	}
}
