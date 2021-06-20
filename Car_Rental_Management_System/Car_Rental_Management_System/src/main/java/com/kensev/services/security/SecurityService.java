package com.kensev.services.security;

import javax.servlet.http.HttpServletRequest;

import com.kensev.entitites.Account;
import com.kensev.entitites.Roles;

public class SecurityService {
	public boolean userIsAdmin(HttpServletRequest request) {
		Account user = (Account) request.getSession().getAttribute("account");
		return user.getRole().equals(Roles.ADMIN);
	}
}
