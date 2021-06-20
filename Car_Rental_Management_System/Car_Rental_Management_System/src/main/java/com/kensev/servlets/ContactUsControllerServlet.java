package com.kensev.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kensev.cruds.ContactUsCRUD;
import com.kensev.entitites.ContactUs;
import com.kensev.services.security.SecurityService;

@WebServlet("/contactus/*")
public class ContactUsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final ContactUsCRUD contactUsCrud = new ContactUsCRUD();
	private SecurityService securityService = new SecurityService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();

		try {
			switch (action) {
			case "/insert":
				insertClient(request, response);
				break;
			case "/list":
				listClient(request, response);
				break;
			}
		} catch (SQLException | InterruptedException | ParseException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void denyAccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errorMessage", "Missing role : ADMIN");
		RequestDispatcher dispatcher = request.getRequestDispatcher("../AccessDenied.jsp");
		dispatcher.forward(request, response);		
	}
	
	private void listClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, InterruptedException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		String orderBy = request.getParameter("orderBy");
		List<ContactUs> listContactUs = contactUsCrud.listAllContactUs(orderBy);
		request.setAttribute("listContactUs", listContactUs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ContactUsList.jsp");
		dispatcher.forward(request, response);
	}

	private void insertClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, InterruptedException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");

		ContactUs newContactUs = new ContactUs(name, email,message);
		contactUsCrud.addContactUs(newContactUs);
		response.sendRedirect("../home.jsp");
	}
	
	
}
