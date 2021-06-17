package com.frantishex.lp.tsh.interceptor.context;

import com.frantishex.lp.bean.Sale;

public class Context
{

	private Sale sale;

	private Sale oldSale = null;

	/**
	 * Initialize Context with Sale.
	 * 
	 * @param sale
	 */
	public Context(Sale sale) {
		super();
		this.sale = sale;
	}

	/**
	 * Initialize Context with Sale and old Sale before merge.
	 * 
	 * @param sale
	 * @param oldSale
	 */
	public Context(Sale sale, Sale oldSale) {
		super();
		this.sale = sale;
		this.oldSale = oldSale;
	}

	/**
	 * @return the sale
	 */
	public Sale getSale()
	{
		return sale;
	}

	/**
	 * @param sale
	 *            the sale to set
	 */
	public void setSale(Sale sale)
	{
		this.sale = sale;
	}

	public Sale getOldSale()
	{
		return oldSale;
	}
}