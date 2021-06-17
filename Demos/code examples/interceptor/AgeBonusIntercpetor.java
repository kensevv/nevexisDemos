package com.frantishex.lp.tsh.interceptor;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.frantishex.lp.bean.DiscountTypeEnum;
import com.frantishex.lp.bean.Sale;
import com.frantishex.lp.interceptor.ProcessingException;
import com.frantishex.lp.tsh.interceptor.context.Context;

/**
 * Give bonus according to age
 * 
 * @author ivan
 * 
 */
@Component
public class AgeBonusIntercpetor extends BaseInterceptor
{

	@Override
	public void process(Context context) throws ProcessingException
	{
		Sale sale = context.getSale();

		if (null == sale.getAge() || 0 == sale.getAge()) {
			sale.setAge(null);
			return;
		}

		BigDecimal discount = getDiscount(sale.getAge());
		if (0 == new BigDecimal("0").compareTo(discount)) {
			return;
		}

		createBonus(sale, sale.getTicketPrice(), discount, null, DiscountTypeEnum.AGE);
	}

	/**
	 * Get discount for clients age
	 * 
	 * @param age
	 *            Client Age
	 * @return Discount percentage
	 */
	private BigDecimal getDiscount(Integer age)
	{
		if (null == age || 0 == age) {
			return new BigDecimal("0");
		}
		if (age < 3) {
			return new BigDecimal("100");
		}
		if (age < 12) {
			return new BigDecimal("50");
		}
		if (age < 26) {
			return new BigDecimal("10");
		}
		if (age >= 60) {
			return new BigDecimal("10");
		}
		return new BigDecimal("0");
	}

}
