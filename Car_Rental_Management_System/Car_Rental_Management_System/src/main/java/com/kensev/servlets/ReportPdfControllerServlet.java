package com.kensev.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.kensev.cruds.ClientsCRUD;
import com.kensev.cruds.DealsCRUD;
import com.kensev.cruds.EmployeesCRUD;
import com.kensev.cruds.VehicleCRUD;
import com.kensev.reporting.Report;
import com.kensev.services.security.SecurityService;

@WebServlet("/pdf/*")
public class ReportPdfControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VehicleCRUD vehicleCrud = new VehicleCRUD();
	private ClientsCRUD clientsCrud = new ClientsCRUD();
	private DealsCRUD dealsCrud = new DealsCRUD();
	private EmployeesCRUD employeesCrud = new EmployeesCRUD();
	
	
	private SecurityService securityService = new SecurityService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!securityService.userIsAdmin(request)) {
			request.setAttribute("errorMessage", "Missing role : ADMIN");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../AccessDenied.jsp");
			dispatcher.forward(request, response);
			return;
		}

		String action = request.getPathInfo();

		switch (action) {
		case "/vehicles":
			getVehiclesPDF(request, response);
			break;
		case "/clients":
			getClientsPDF(request, response);
			break;
		case "/employees":
			getEmployeesPDF(request, response);
			break;
		case "/deals":
			getDealsPDF(request, response);
			break;
		}
	}

	private void getVehiclesPDF(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=Vehicles.pdf");

		try {
			Report.getPDF(vehicleCrud.listAllVehicles().toArray(), response.getOutputStream());
		} catch (IllegalArgumentException | IllegalAccessException | DocumentException | URISyntaxException
				| IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getClientsPDF(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=Clients.pdf");

		try {
			Report.getPDF(clientsCrud.getAllClients().toArray(), response.getOutputStream());
		} catch (IllegalArgumentException | IllegalAccessException | DocumentException | URISyntaxException
				| IOException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void getEmployeesPDF(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=Employees.pdf");

		try {
			Report.getPDF(employeesCrud.getAllEmployees().toArray(), response.getOutputStream());
		} catch (IllegalArgumentException | IllegalAccessException | DocumentException | URISyntaxException
				| IOException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void getDealsPDF(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=Deals.pdf");

		try {
			Report.getPDF(dealsCrud.listAllDeals().toArray(), response.getOutputStream());
		} catch (IllegalArgumentException | IllegalAccessException | DocumentException | URISyntaxException
				| IOException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
