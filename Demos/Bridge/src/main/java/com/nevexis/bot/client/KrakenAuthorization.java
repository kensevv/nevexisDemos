package com.nevexis.bot.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.nevexis.bot.jsonmodels.JsonToken;

public class KrakenAuthorization {
	@Value("${krakenKey}")
	private String key;
	
	@Value("${krakenSecret}")
	private static String secret;
	
	@Value("${krakenDomain}")
	private static String domain;

	private String nonce, signature, data;
	
	private String path = "/0/private/GetWebSocketsToken";

	public String getToken() throws JsonMappingException, JsonProcessingException {
		nonce = String.valueOf(System.currentTimeMillis());
		data = "nonce=" + nonce;

		calculateSignature();

		return  post(domain + path, data);
	}

	public String post(String address, String output) throws JsonMappingException, JsonProcessingException {
		String answer = "";

		HttpsURLConnection conn = null;
		Gson gson = new Gson();

		try {
			URL url = new URL(address);

			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("API-Key", key);
			conn.setRequestProperty("API-Sign", signature);
			conn.setDoOutput(true);

			DataOutputStream os = new DataOutputStream(conn.getOutputStream());

			os.writeBytes(output);
			os.flush();
			os.close();
			BufferedReader br = null;

			if (conn.getResponseCode() >= 400)
				System.exit(1);
			

			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line;

			while ((line = br.readLine()) != null) {
				answer += line;
			}
		} catch (Exception x) {
			System.exit(1);
		} finally {
			conn.disconnect();
		}

		JsonToken jsonToken = gson.fromJson(answer, JsonToken.class);

		return jsonToken.getResult().getToken();
	}

	private void calculateSignature() {
		signature = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((nonce + data).getBytes());
			Mac mac = Mac.getInstance("HmacSHA512");
			mac.init(new SecretKeySpec(Base64.decodeBase64(secret.getBytes()), "HmacSHA512"));
			mac.update(path.getBytes());
			signature = new String(Base64.encodeBase64(mac.doFinal(md.digest())));
		} catch (Exception e) {
		}
		return;
	}
}