package com.kensev.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordService {
	synchronized public String encrypt(String password) throws NoSuchAlgorithmException {
		return Base64.getEncoder()
				.encodeToString(MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8)));
	}

	public boolean comparePasswords(String inputPassword, String encodedPassword) throws NoSuchAlgorithmException {
		return encrypt(inputPassword).equals(encodedPassword);
	}
}
