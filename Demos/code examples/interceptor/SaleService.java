package com.frantishex.lp.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.frantishex.lp.bean.Client;
import com.frantishex.lp.bean.PointTransaction;
import com.frantishex.lp.bean.PointTransactionTypeEnum;
import com.frantishex.lp.bean.Sale;
import com.frantishex.lp.bean.SaleBonus;
import com.frantishex.lp.bean.SaleTypeEnum;
import com.frantishex.lp.criteria.FrantishexCriteria;
import com.frantishex.lp.criteria.FrantishexCriteriaBuilder;
import com.frantishex.lp.criteria.FrantishexCriteriaValidationException;
import com.frantishex.lp.interceptor.InterceptorChainImpl;
import com.frantishex.lp.interceptor.ProcessingException;
import com.frantishex.lp.tsh.interceptor.context.Context;
import com.frantishex.lp.utils.MapperUtils;

@Repository("saleService")
@Transactional
public class SaleService extends BaseService<Sale>
{

	private static final SimpleDateFormat FRX_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	@Autowired
	private ClientService clientService;

	@Autowired
	@Qualifier("bonusChain")
	private InterceptorChainImpl<Context> bonusChain;

	@Autowired
	@Qualifier("bonusChainOnlyPoints")
	private InterceptorChainImpl<Context> bonusChainOnlyPoints;

	@Autowired
	@Qualifier("bonusChainNoPoints")
	private InterceptorChainImpl<Context> bonusChainNoPoints;

	@Autowired
	private MapperUtils mapperUtils;

	public Sale makeSale(Sale sale) throws ProcessingException, FrantishexCriteriaValidationException
	{
		/**
		 * we can detach instance but this means unit tests or integration tests error
		 * is made
		 */
		if (getEntityManager().contains(sale)) {
			throw new IllegalArgumentException("do not pass entity managed instance. We always expect a detached one!");
		}

		/**
		 * The Reservation only unique key is description
		 */
		if (null != sale.getDescription() && !sale.getDescription().isEmpty()
				&& (null == sale.getId() || 0 == sale.getId()) && sale.getSaleType() == SaleTypeEnum.RESERVATION
				&& findByKey("description", sale.getDescription()).size() > 0) {
			throw new IllegalArgumentException("Sale duplicated");
		}

		// determine the old client
		Sale oldSale = sale.getId() != null ? findById(sale.getId()) : null;
		Sale detachedOldSale = sale.getId() != null ? mapperUtils.map(findById(sale.getId()), Sale.class) : null;
		Client oldClien = oldSale != null ? oldSale.getClient() : null;

		sale = saveOrUpdate(sale);

		List<Client> findByKey = clientService.findByKey("cardNumber", sale.getCardNumber());
		if (findByKey.size() > 0) {
			Client client = findByKey.get(0);
			sale.setClient(client);
		}

		Context context = new Context(sale, detachedOldSale);
		if (sale.getSaleType() == SaleTypeEnum.RESERVATION) {
			if (sale.getConfirmed()) {
				bonusChain.invoke(context);
			} else {
				bonusChainNoPoints.invoke(context);
			}
		} else {
			bonusChainOnlyPoints.invoke(context);
		}
		Client client = sale.getClient();
		recalculateClientPoints(client);
		if (oldClien == null || (oldClien != null && client == null) || (oldClien.getId() != client.getId())) {
			recalculateClientPoints(oldClien);
		}

		return sale;

	}

	public void estimateSale(Sale sale) throws ProcessingException, FrantishexCriteriaValidationException
	{
		/**
		 * we can detach instance but this means unit tests or integration tests error
		 * is made
		 */
		if (getEntityManager().contains(sale)) {
			throw new IllegalArgumentException("do not pass entity managed instance. We always expect a detached one!");
		}

		List<Client> findByKey = clientService.findByKey("cardNumber", sale.getCardNumber());
		if (findByKey.size() > 0) {
			Client client = findByKey.get(0);
			sale.setClient(client);
		}

		Context context = new Context(sale);
		if (sale.getSaleType() == SaleTypeEnum.RESERVATION) {
			bonusChain.invoke(context);
		} else {
			bonusChainOnlyPoints.invoke(context);
		}

	}

	public List<Sale> reportSaleByCardNumber(String cardNumber) throws FrantishexCriteriaValidationException
	{
		FrantishexCriteriaBuilder cb = FrantishexCriteriaBuilder.createCriteriaBuilder(Sale.class);
		ArrayList<FrantishexCriteria> criterions = new ArrayList<FrantishexCriteria>();

		criterions.add(cb.eq("client.cardNumber", cardNumber));

		criterions.add(cb.eq("confirmed", true));

		List<Sale> sales = search(cb.and(criterions.toArray(new FrantishexCriteria[0])));
		Collections.sort(sales, new Comparator<Sale>() {
			@Override
			public int compare(Sale o1, Sale o2)
			{
				return -o1.getDateCreated().compareTo(o2.getDateCreated());
			}
		});
		return sales;

	}

	private List<Sale> reportSaleByClientId(Long clientId) throws FrantishexCriteriaValidationException
	{

		FrantishexCriteriaBuilder cb = FrantishexCriteriaBuilder.createCriteriaBuilder(Sale.class);
		ArrayList<FrantishexCriteria> criterions = new ArrayList<FrantishexCriteria>();

		criterions.add(cb.eq("client.id", clientId));

		criterions.add(cb.eq("confirmed", true));

		List<Sale> sales = search(cb.and(criterions.toArray(new FrantishexCriteria[0])));
		Collections.sort(sales, new Comparator<Sale>() {
			@Override
			public int compare(Sale o1, Sale o2)
			{
				return -o1.getDateCreated().compareTo(o2.getDateCreated());
			}
		});
		return sales;
	}

	public List<Sale> reportSale(String sDateFrom, String sDateTo)
			throws ParseException, FrantishexCriteriaValidationException
	{
		FrantishexCriteriaBuilder cb = FrantishexCriteriaBuilder.createCriteriaBuilder(Sale.class);
		ArrayList<FrantishexCriteria> criterions = new ArrayList<FrantishexCriteria>();
		if (!StringUtils.isEmpty(sDateFrom)) {
			Date dateFrom = FRX_DATE_FORMAT.parse(sDateFrom);
			criterions.add(cb.ge("dateCreated", dateFrom));
		}
		if (!StringUtils.isEmpty(sDateTo)) {
			Date dateTo = FRX_DATE_FORMAT.parse(sDateTo);
			// this is incredibly stupid and ugly but it is needed because we
			// receive the dates as string for some ungodly reason
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateTo);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.add(Calendar.DATE, 1);
			criterions.add(cb.le("dateCreated", cal.getTime()));
		}

		criterions.add(cb.eq("confirmed", true));

		List<Sale> sales = search(cb.and(criterions.toArray(new FrantishexCriteria[0])));
		Collections.sort(sales, new Comparator<Sale>() {
			@Override
			public int compare(Sale o1, Sale o2)
			{
				return -o1.getDateCreated().compareTo(o2.getDateCreated());
			}
		});
		return sales;
	}

	/**
	 * Recalculate client points. Iterates all client sales and sets points
	 * property.
	 * 
	 * @param client
	 * @throws ProcessingException
	 * @throws FrantishexCriteriaValidationException
	 */
	private void recalculateClientPoints(Client client) throws ProcessingException, FrantishexCriteriaValidationException
	{

		if (null == client) {
			return;
		}

		client.setPoints(new BigDecimal("0"));
		List<Sale> sales = reportSaleByClientId(client.getId());
		for (Sale sale : sales) {
			for (SaleBonus sd : sale.getBonuses()) {
				PointTransaction pt = sd.getPointTransaction();
				if (null == pt) {
					continue;
				}
				if (pt.getType() == PointTransactionTypeEnum.EARNED) {
					client.setPoints(client.getPoints().add(pt.getPoints()));
				} else {
					client.setPoints(client.getPoints().subtract(pt.getPoints()));
				}
			}

		}

	}

	public void deleteSaleById(Long id) throws ProcessingException, FrantishexCriteriaValidationException
	{

		if (null == id || id <= 0) {
			throw new ProcessingException("Invalid sale id.");
		}

		Sale sale = findById(id);

		if (null == sale) {
			throw new ProcessingException("Sale by passed id does not exist.");
		}

		Client client = sale.getClient();
		delete(sale);
		recalculateClientPoints(client);

	}
}
