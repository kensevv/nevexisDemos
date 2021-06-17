package com.nevexis;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nevexis.bot.client.Client;
import com.nevexis.bot.client.KrakenAuthorization;
import com.nevexis.bot.client.PrivateFeedMethods;

@Configuration
@EnableScheduling
@EnableWebMvc
public class AppConfig {

	private final String PRIVATE_STREAM_URL = "wss://ws-auth.kraken.com/";

	private KrakenAuthorization krakenAuth;
	private Client client;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public void startClient() throws InterruptedException, DeploymentException, IOException, URISyntaxException {
		krakenAuth = new KrakenAuthorization();

		String token = krakenAuth.getToken();

		client = new Client();

		client.connect(PRIVATE_STREAM_URL);

		client.sendMessage(PrivateFeedMethods.subscribe(token, "openOrders"));

		client.disconnect();
	}

}
