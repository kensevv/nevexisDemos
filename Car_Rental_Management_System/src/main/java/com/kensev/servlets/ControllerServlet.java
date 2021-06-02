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

		String license_plate = request.getParameter("license_plate");
		String model = request.getParameter("model");
		String insurance = request.getParameter("insurance");
		boolean is_available = Boolean.parseBoolean(request.getParameter("is_available"));
		int mileage = Integer.parseInt(request.getParameter("mileage"));
		double price = Double.parseDouble(request.getParameter("price"));

		Vehicle newVehicle = new Vehicle(license_plate, model, insurance, is_available, mileage, price);
		vehicleCRUD.addVehicle(newVehicle);
		response.sendRedirect("list");

	}

	private void updateVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String license_plate = request.getParameter("license_plate");
		String model = request.getParameter("model");
		String insurance = request.getParameter("insurance");
		String is_available = request.getParameter("is_available");
		String mileage = request.getParameter("mileage");
		String price = request.getParameter("price");

		Vehicle existingVehicle = vehicleCRUD.getVehicle(license_plate);
		if (model != "")
			existingVehicle.setModel(model);
		if (insurance != "")
			existingVehicle.setInsurance(insurance);
		if (is_available != "")
			existingVehicle.setAvailable(Boolean.parseBoolean(is_available));
		if (mileage != "")
			existingVehicle.setMilleage(Integer.parseInt(mileage));
		if (price != "")
			existingVehicle.setPrice(Double.parseDouble(price));
		
		vehicleCRUD.updateVehicle(existingVehicle);
		response.sendRedirect("list");
	}

	private void deleteVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String license_plate = request.getParameter("license_plate");
		vehicleCRUD.removeVehicle(license_plate);
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
