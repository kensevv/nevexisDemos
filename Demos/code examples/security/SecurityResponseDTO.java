package com.frantishex.lwr.security;

import java.util.Arrays;

public class SecurityResponseDTO {
	
	private String message;

	public SecurityResponseDTO(String message) {
		super();
		this.message = message;
	} 
	
	public SecurityResponseDTO(Throwable e) {
		super();
		this.message = Arrays.toString(e.getStackTrace());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 
	
	
}
