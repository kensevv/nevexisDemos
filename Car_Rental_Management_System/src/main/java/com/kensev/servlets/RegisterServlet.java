package com.kensev.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kensev.entitites.Account;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = new Account();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean registered;
		try {
			registered = acc.register(username, email, password);
		} catch (SQLException e) {
			throw new Error("register error ......");
		}
		if(registered) {
			request.getSession().setAttribute("account", acc);
			response.sendRedirect("home.jsp");
		}
		else {
			response.sendRedirect("register.jsp");
		}
	}
}