package com.kensev.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kensev.accounts.Account;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = new Account();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean logged;
		try {
			logged = acc.login(email, password);
		} catch (SQLException e) {
			throw new Error("Login error ......");
		}
		
		if(logged == true) {
			request.getSession().setAttribute("account", acc);
			response.sendRedirect("home.jsp");
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}
}