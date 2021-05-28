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

@WebServlet("/")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VehicleCRUD vehicleCRUD;

	public ControllerServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		vehicleCRUD = new VehicleCRUD();
		String action = request.getServletPath();

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

	private void listVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String orderBy = request.getParameter("orderBy");
		List<Vehicle> listVehicle = vehicleCRUD.listAllVehicles(orderBy);
		request.setAttribute("listVehicle", listVehicle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("VehicleList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("VehicleForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Vehicle> listVehicle = vehicleCRUD.listAllVehicles();
		request.setAttribute("listVehicle", listVehicle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("VehicleModify.jsp");
		dispatcher.forward(request, response);
	}

	private void insertVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String licPlate = request.getParameter("license_plate");
		String model = request.getParameter("model");
		String insurance = request.getParameter("insurance");
		boolean isAvailable = Boolean.parseBoolean(request.getParameter("is_available"));
		int milleage = Integer.parseInt(request.getParameter("mileage"));
		double price = Double.parseDouble(request.getParameter("price"));

		Vehicle newVehicle = new Vehicle(licPlate, model, insurance, isAvailable, milleage, price);
		vehicleCRUD.addVehicle(newVehicle);
		response.sendRedirect("list");

	}

	private void updateVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String licPlate = request.getParameter("license_plate");
		String model = request.getParameter("model");
		String insurance = request.getParameter("insurance");
		String isAvailable = request.getParameter("is_available");
		String milleage = request.getParameter("mileage");
		String price = request.getParameter("price");

		Vehicle existingVehicle = vehicleCRUD.getVehicle(licPlate);
		if (model != "")
			existingVehicle.setModel(model);
		if (insurance != "")
			existingVehicle.setInsurance(insurance);
		if (isAvailable != "")
			existingVehicle.setAvailable(Boolean.parseBoolean(isAvailable));
		if (milleage != "")
			existingVehicle.setMilleage(Integer.parseInt(milleage));
		if (price != "")
			existingVehicle.setPrice(Double.parseDouble(price));
		
		vehicleCRUD.updateVehicle(existingVehicle);
		response.sendRedirect("list");
	}

	private void deleteVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String licPlate = request.getParameter("license_plate");
		vehicleCRUD.removeVehicle(licPlate);
		response.sendRedirect("list");
		return;
	}

	private void removeForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Vehicle> listVehicle = vehicleCRUD.listAllVehicles();
		request.setAttribute("listVehicle", listVehicle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("RemoveVehicle.jsp");
		dispatcher.forward(request, response);
	}

}
