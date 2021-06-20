package com.kensev.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kensev.cruds.ClientsCRUD;
import com.kensev.entitites.Clients;
import com.kensev.services.security.SecurityService;

@WebServlet("/clients/*")
public class ClientsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final ClientsCRUD clientsCrud = new ClientsCRUD();
	private SecurityService securityService = new SecurityService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertClient(request, response);
				break;
			case "/delete":
				deleteClient(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateClient(request, response);
				break;
			case "/removeForm":
				removeForm(request, response);
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
		String orderBy = request.getParameter("orderBy");
		List<Clients> listClient = clientsCrud.getAllClients(orderBy);
		request.setAttribute("listClient", listClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, InterruptedException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		List<Clients> listClient = clientsCrud.getAllClients();
		request.setAttribute("listClient", listClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientModify.jsp");
		dispatcher.forward(request, response);
	}

	private void insertClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, InterruptedException, ServletException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}

		String ID = request.getParameter("ID");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		Date birthdate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthdate")).getTime());
		String driver_lic = request.getParameter("driver_lic");

		Clients newClient = new Clients(ID, first_name, last_name, email, phone, birthdate, driver_lic);
		clientsCrud.addClient(newClient);
		response.sendRedirect("list");

	}

	private void updateClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, InterruptedException, ServletException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		String ID = request.getParameter("ID");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		Date birthdate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthdate")).getTime());
		String driver_lic = request.getParameter("driver_lic");

		Clients existingClient = clientsCrud.getClientById(ID);
		if (first_name != "")
			existingClient.setFirst_name(first_name);
		if (last_name != "")
			existingClient.setLast_name(last_name);
		if (email != "")
			existingClient.setEmail(email);
		if (phone != "")
			existingClient.setPhone(phone);
		if (birthdate != null) {
			existingClient.setBirthday(birthdate);
		}
		if(driver_lic != "")
			existingClient.setDriver_lic(driver_lic);
		
		clientsCrud.updateClient(existingClient);
		response.sendRedirect("list");
	}

	private void deleteClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, InterruptedException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		String ID = request.getParameter("ID");
		clientsCrud.removeClient(ID);
		response.sendRedirect("list");
		return;
	}

	private void removeForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, InterruptedException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		List<Clients> listClient = clientsCrud.getAllClients();
		request.setAttribute("listClient", listClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RemoveClient.jsp");
		dispatcher.forward(request, response);
	}
}
