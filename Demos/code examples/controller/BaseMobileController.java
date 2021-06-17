package com.frantishex.urbo.controller.mobile;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.frantishex.urbo.config.security.SecurityService;
import com.frantishex.urbo.utils.MapperUtils;

@Transactional
public abstract class BaseMobileController { // NO_UCD (use default)

	@Autowired
	protected MapperUtils mapperUtils;

	@Autowired
	protected SecurityService securityService;

	protected HttpHeaders headers;

	@PostConstruct
	private void init() {
		headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
	}

	protected ResponseEntity<String> getInternalServerError(Exception e) {
		return new ResponseEntity<String>("{\"error\":\"" + e.getMessage() + "\"}", headers,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected String getErrorJson(String errorMessage) {
		return "{\"error\" : \"" + errorMessage + "\"}";
	}

	protected String getSingleStringPropertyJson(String propertyName, String propertyValue) {
		return "{\"" + propertyName + "\" : \"" + propertyValue + "\"}";
	}

	protected String getSingleLiteralPropertyJson(String propertyName, String propertyValue) {
		return "{\"" + propertyName + "\" : " + propertyValue + "}";
	}
}
