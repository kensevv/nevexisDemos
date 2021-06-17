package com.nevexis;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nevexis.controllers.JsonDataBreakDown;

@SpringBootApplication
public class SpringCarRentalApplication {

	public static void main(String[] args) throws IOException, URISyntaxException {
		SpringApplication.run(SpringCarRentalApplication.class, args);
		JsonDataBreakDown.loadCryptoEntities("https://api.kraken.com/0/public/OHLC?pair=BTCUSD&since=0&interval=1");
		
	}
}
