package com.nevexis.bot.enums;

public enum PurchaseType {
	BUY("buy"),
	SELL("sell");
	
	private String value;
	  
    public String getOrderType()
    {
        return this.value;
    }
  
    private PurchaseType(String value)
    {
        this.value = value;
    }
}
