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

import com.kensev.cruds.DealsCRUD;
import com.kensev.entitites.Deals;
import com.kensev.entitites.Vehicle;

@WebServlet("/deals/*")
public class DealsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DealsCRUD dealsCRUD;

	public DealsControllerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dealsCRUD = new DealsCRUD();
		String action = request.getPathInfo();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertDeal(request, response);
				break;
			case "/list":
				listVehicle(request, response);
				break;
			}
		} catch (SQLException | InterruptedException | ParseException ex) {
			throw new ServletException(ex);
		}
	}

	private void listVehicle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, InterruptedException {
		String orderBy = request.getParameter("orderBy");
		List<Deals> listDeal = dealsCRUD.listAllDeals(orderBy);
		request.setAttribute("listDeal", listDeal);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DealsList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DealsForm.jsp");
		dispatcher.forward(request, response);
	}

	private void insertDeal(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, InterruptedException {
		
		Date start_date = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("start_date")).getTime());
		Date end_date = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("end_date")).getTime());
		Double payment = Double.parseDouble(request.getParameter("payment"));
		String client_id = request.getParameter("client_id");
		String employee_id = request.getParameter("employee_id");
		String vehicle_licPlate = request.getParameter("vehicle_licPlate");
		String branch_name = request.getParameter("branch_name");

		Deals newDeal = new Deals(start_date, client_id, employee_id, vehicle_licPlate, branch_name, end_date, payment);
		dealsCRUD.addDeal(newDeal);
		response.sendRedirect("list");
	}
}
