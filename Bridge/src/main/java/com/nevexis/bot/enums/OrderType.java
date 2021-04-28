package com.nevexis.bot.enums;

public enum OrderType {
	MARKET("Market"),
	LIMIT("Limit"),
	SETTLE_POSITION("Settle Position"),
	STOP_LOSS("Stop Loss"),
	TAKE_PROFIT("Take Profit"),
	STOP_LOSS_LMT("Stop Loss Lmt"),
	TAKE_PROFIT_LMT("Take Profit Lmt");
	
	private String value;
	  
    public String getOrderType()
    {
        return this.value;
    }
  
    private OrderType(String value)
    {
        this.value = value;
    }
}
