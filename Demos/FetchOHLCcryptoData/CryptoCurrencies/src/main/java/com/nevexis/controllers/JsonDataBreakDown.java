package com.nevexis.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nevexis.models.CryptoData;

public class JsonDataBreakDown {
	
	public static void loadCryptoEntities(String sUrl) throws JsonParseException, JsonMappingException, IOException {
		List<CryptoData> allData = new ArrayList<CryptoData>(1000);

		ObjectMapper objectMapper = new ObjectMapper();
		URL jsonUrl = new URL(sUrl);
		Map<String, Object> rawData = objectMapper.readValue(jsonUrl, Map.class);

		Map<String, Object> arrays = (Map<String, Object>) rawData.get("result");

		String typeCrypto = "";
		for (String key : arrays.keySet()) {
			typeCrypto = key;
			break;
		}
		ArrayList<ArrayList<String>> currencies = (ArrayList<ArrayList<String>>) arrays.get(typeCrypto);

		for (ArrayList<String> arr : currencies) {
			CryptoData currentData = new CryptoData();

			currentData.setType(typeCrypto);
			currentData.setTimestamp(new Timestamp(Long.parseLong(String.valueOf(arr.get(0))) * 1000));
			currentData.setOpen(Double.parseDouble(arr.get(1)));
			currentData.setHigh(Double.parseDouble(arr.get(2)));
			currentData.setLow(Double.parseDouble(arr.get(3)));
			currentData.setClose(Double.parseDouble(arr.get(4)));
			currentData.setVwap(Double.parseDouble(arr.get(5)));
			currentData.setVolume(Double.parseDouble(arr.get(6)));
			currentData.setCount(Integer.parseInt(String.valueOf(arr.get(7))));

		}
		
		
	}

}