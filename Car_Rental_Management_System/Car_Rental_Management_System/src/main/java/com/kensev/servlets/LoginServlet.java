package com.kensev.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kensev.entitites.Account;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final AccountService accountService = new AccountService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Account account = accountService.login(request.getParameter("email"), request.getParameter("password"));

			if (account != null) {
				request.getSession().setAttribute("account", account);
				response.sendRedirect("home.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (NoSuchAlgorithmException | SQLException e) {
			e.printStackTrace();
		}
	}
}