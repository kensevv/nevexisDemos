package com.nevexis.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nevexis.mapping.StockModel;

@Service
public class StockRefresherService {
	
	@Value("${currency}")
	private String[] currency;
	
	@Value("${currencyURL}")
	private String[] currencyList;
	
	@Value("${URLkraken}")
	private String URL;
	

	@Autowired
	private DBMapperService dbMapper;
	
	@Scheduled(fixedDelay =10000)
	private synchronized void postConstruct() throws IOException {
		for (int i = 0; i < currencyList.length; i++) {
			getNewData(String.format(URL, currencyList[i]), currency[i]);
		}
	}

	private  void getNewData(String exchangeAndCurrencyURL, String currencyCode) throws IOException {
		URL url = new URL(exchangeAndCurrencyURL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.connect();

		// Getting the response code
		int responsecode = conn.getResponseCode();

		if (responsecode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		}

		StringBuilder inline = new StringBuilder();
		Scanner scanner = new Scanner(url.openStream());

		while (scanner.hasNext()) {
			inline.append(scanner.nextLine());
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		StockModel stock = gson.fromJson(inline.toString(), StockModel.class);

		dbMapper.insertCryptoOHLC(stock, currencyCode);
		
		scanner.close();
	}


}
