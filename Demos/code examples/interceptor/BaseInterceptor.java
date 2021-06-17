package com.frantishex.lp.tsh.interceptor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.frantishex.lp.bean.DiscountTypeEnum;
import com.frantishex.lp.bean.PointTransaction;
import com.frantishex.lp.bean.PointTransactionTypeEnum;
import com.frantishex.lp.bean.Sale;
import com.frantishex.lp.bean.SaleBonus;
import com.frantishex.lp.interceptor.InterceptorImpl;
import com.frantishex.lp.tsh.interceptor.context.Context;

abstract public class BaseInterceptor extends InterceptorImpl<Context>
{

	/**
	 * Create new bonus item and calculates the discountedPrice amount.
	 * 
	 * @param sale
	 *            TODO
	 * @param price
	 *            - original price
	 * @param value
	 *            - discount value - usually percentage
	 * @param discountedPrice
	 *            TODO
	 * @param type
	 *            - discount type - EXTERNAL, CARD_PRICE, etc.
	 */
	protected void createBonus(Sale sale, BigDecimal price, BigDecimal value, BigDecimal discountedPrice,
			DiscountTypeEnum type)
	{
		SaleBonus bonus = new SaleBonus();

		bonus.setOriginalPrice(price);
		bonus.setValue(value);

		if (null == discountedPrice) {
			discountedPrice = new BigDecimal("100").subtract(value).multiply(price).divide(new BigDecimal("100"), 2,
					RoundingMode.HALF_UP);
		}
		bonus.setDiscountedPrice(discountedPrice);

		bonus.setDiscountType(type);
		bonus.setSale(sale);

		sale.getBonuses().add(bonus);

		if (type == DiscountTypeEnum.POINTS) {
			addSaleBonusTransaction(bonus, PointTransactionTypeEnum.SPENT);
		} else if (type == DiscountTypeEnum.BONIFICATE_WITH_POINTS) {
			addSaleBonusTransaction(bonus, PointTransactionTypeEnum.EARNED);
		}

	}

	private void addSaleBonusTransaction(SaleBonus saleBonus, PointTransactionTypeEnum transactionType)
	{
		PointTransaction pointsTransaction = new PointTransaction();
		pointsTransaction.setPoints(saleBonus.getValue());
		pointsTransaction.setType(transactionType);
		pointsTransaction.setSaleBonus(saleBonus);
		saleBonus.setPointTransaction(pointsTransaction);
	}
}