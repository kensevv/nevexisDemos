package com.nevexis.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevexis.mapping.StockModel;
import com.nevexis.model.Crypto;
import com.nevexis.model.CryptoPrimaryKey;
import com.nevexis.model.Currency;

@Service
public class DBMapperService {

	@Autowired
	private CryptoRepo repo;

	public synchronized void insertCryptoOHLC(StockModel stock, String currencyName) {
		List<List<String>> data = stock.result.data;
		Crypto crypto;

		for (List<String> itemsList : data) {
			crypto = new Crypto();
			Timestamp date = new Timestamp(Long.parseLong(itemsList.get(0)) * 1000);

			crypto.setId(new CryptoPrimaryKey(date, currencyName, "Kraken"));

			crypto.setOpen(new BigDecimal(itemsList.get(1)));
			crypto.setHigh(new BigDecimal(itemsList.get(2)));
			crypto.setLow(new BigDecimal(itemsList.get(3)));
			crypto.setClose(new BigDecimal(itemsList.get(4)));
			crypto.setAverage(new BigDecimal(itemsList.get(5)));
			crypto.setVolume(new BigDecimal(itemsList.get(6)));
			crypto.setTrades(Integer.parseInt(itemsList.get(7)));
			crypto.setCurrency(new Currency(currencyName));

			repo.saveAndFlush(crypto);
		}
	}

}
