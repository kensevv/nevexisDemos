package com.frantishex.lwr.config;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class MapperConfig {
	
	@Autowired 
	ObjectMapper objectMapper; 
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Bean
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();

		org.modelmapper.config.Configuration configuration = modelMapper.getConfiguration();
        configuration.setSkipNullEnabled(true);
        configuration.setMatchingStrategy(MatchingStrategies.STRICT);
		       
		return modelMapper;
	}
	
	@PostConstruct
	public void configureJackson() {
		objectMapper.registerModule(new JavaTimeModule());
	}
	
	
	
}
