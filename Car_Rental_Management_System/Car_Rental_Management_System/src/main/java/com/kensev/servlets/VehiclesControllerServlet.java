package com.kensev.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kensev.cruds.VehicleCRUD;
import com.kensev.entitites.Vehicle;
import com.kensev.security.SecurityService;

@WebServlet("/vehicles/*")
public class VehiclesControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final VehicleCRUD vehicleCRUD = new VehicleCRUD();
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
				insertVehicle(request, response);
				break;
			case "/delete":
				deleteVehicle(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateVehicle(request, response);
				break;
			case "/removeForm":
				removeForm(request, response);
			case "/list":
				listVehicle(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void denyAccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errorMessage", "Missing role : ADMIN");
		RequestDispatcher dispatcher = request.getRequestDispatcher("../AccessDenied.jsp");
		dispatcher.forward(request, response);		
	}
	
	private void listVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String orderBy = request.getParameter("orderBy");
		List<Vehicle> listVehicle = vehicleCRUD.listAllVehicles(orderBy);
		request.setAttribute("listVehicle", listVehicle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/VehicleList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		request.getRequestDispatcher("/VehicleForm.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		List<Vehicle> listVehicle = vehicleCRUD.listAllVehicles();
		request.setAttribute("listVehicle", listVehicle);
		request.getRequestDispatcher("/VehicleModify.jsp").forward(request, response);
	}

	private void insertVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		if (!securityService.userIsAdmin(request)) {
			response.sendRedirect("/AccessDenied.jsp");
			return;
		}
		String licPlate = request.getParameter("license_plate");
		String model = request.getParameter("model");
		String insurance = request.getParameter("insurance");
		boolean isAvailable = Boolean.parseBoolean(request.getParameter("is_available"));
		int milleage = Integer.parseInt(request.getParameter("mileage"));
		double price = Double.parseDouble(request.getParameter("price"));
		String branch_name = request.getParameter("branch_name");

		Vehicle newVehicle = new Vehicle(licPlate, model, insurance, isAvailable, milleage, price, branch_name);
		vehicleCRUD.addVehicle(newVehicle);
		response.sendRedirect("list");

	}

	private void updateVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		String licPlate = request.getParameter("license_plate");
		String model = request.getParameter("model");
		String insurance = request.getParameter("insurance");
		String isAvailable = request.getParameter("is_available");
		String milleage = request.getParameter("mileage");
		String price = request.getParameter("price");
		String branch_name = request.getParameter("branch_name");

		Vehicle existingVehicle = vehicleCRUD.getVehicle(licPlate);
		if (model != "")
			existingVehicle.setModel(model);
		if (insurance != "")
			existingVehicle.setInsurance(insurance);
		if (isAvailable != "")
			existingVehicle.setIs_available(Boolean.parseBoolean(isAvailable));
		if (milleage != "")
			existingVehicle.setMileage(Integer.parseInt(milleage));
		if (price != "")
			existingVehicle.setPrice(Double.parseDouble(price));
		if(branch_name != "")
			existingVehicle.setBranch_name(branch_name);
		
		vehicleCRUD.updateVehicle(existingVehicle);
		response.sendRedirect("list");
	}

	private void deleteVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		String licPlate = request.getParameter("license_plate");
		vehicleCRUD.removeVehicle(licPlate);
		response.sendRedirect("list");
		return;
	}

	private void removeForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		List<Vehicle> listVehicle = vehicleCRUD.listAllVehicles();
		request.setAttribute("listVehicle", listVehicle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RemoveVehicle.jsp");
		dispatcher.forward(request, response);
	}

}
