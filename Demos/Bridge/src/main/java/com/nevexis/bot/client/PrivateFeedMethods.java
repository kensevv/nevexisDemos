package com.nevexis.bot.client;

import java.util.List;

import com.google.gson.Gson;
import com.nevexis.bot.enums.OrderType;
import com.nevexis.bot.enums.PurchaseType;
import com.nevexis.bot.jsonmodels.JsonAddOrder;
import com.nevexis.bot.jsonmodels.JsonCancelAllOrder;
import com.nevexis.bot.jsonmodels.JsonCancelOrder;
import com.nevexis.bot.jsonmodels.JsonMessage;
import com.nevexis.bot.jsonmodels.JsonSubscription;
import com.nevexis.bot.jsonmodels.Subscription;

public class PrivateFeedMethods {
	private static final String SUBSCRIBE_EVENT = "subscribe";
	private static final String ADD_ORDER_EVENT = "addOrder";
	private static final String CANCEL_ORDER_EVENT = "cancelOrder";
	private static final String CANCEL_ALL_ORDER_EVENT = "cancelAll";

	public static String subscribe(String token, String tradesType) {
		JsonMessage jsonSubscribe = new JsonSubscription(SUBSCRIBE_EVENT, new Subscription(tradesType, token));

		return new Gson().toJson(jsonSubscribe).toString();
	}

	public static String addOrder(String token, OrderType orderType, String pair, String price, PurchaseType type,
			String volume) {
		JsonMessage jsonAddOrder = new JsonAddOrder(ADD_ORDER_EVENT, orderType.getOrderType(), pair, price, token,
				type.getOrderType(), volume);

		return new Gson().toJson(jsonAddOrder).toString();
	}

//	public static String addOrderWithConditionalClose(String token) {
//		
//	}

	// cancelOrder() - may cancel one or multiple orders
	public static String cancelOrder(String token, List<String> ordersToCancel) {
		JsonMessage jsonCancelOrder = new JsonCancelOrder(CANCEL_ORDER_EVENT, token, ordersToCancel);

		return new Gson().toJson(jsonCancelOrder).toString();
	}

	public static String cancelAllOrder(String token, List<String> ordersToCancel) {
		JsonMessage jsonCancelAllOrder = new JsonCancelAllOrder(CANCEL_ALL_ORDER_EVENT, token);

		return new Gson().toJson(jsonCancelAllOrder).toString();
	}
}
