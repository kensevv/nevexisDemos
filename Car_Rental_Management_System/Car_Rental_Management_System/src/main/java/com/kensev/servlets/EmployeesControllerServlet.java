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

import com.kensev.cruds.EmployeesCRUD;
import com.kensev.entitites.Employees;
import com.kensev.security.SecurityService;

@WebServlet("/employees/*")
public class EmployeesControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final EmployeesCRUD employeesCrud = new EmployeesCRUD();
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
		List<Employees> listEmployee = employeesCrud.getAllEmployees(orderBy);
		request.setAttribute("listEmployee", listEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, InterruptedException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		List<Employees> listEmployee = employeesCrud.getAllEmployees();
		request.setAttribute("listEmployee", listEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeModify.jsp");
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
		String work_number = request.getParameter("work_number");
		String branch_name = request.getParameter("branch_name");
		String manager_id = request.getParameter("manager_id");

		Employees newEmployee = new Employees(ID, first_name, last_name, email, phone, birthdate, work_number, branch_name, manager_id);
		employeesCrud.addEmployee(newEmployee);
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
		String work_number = request.getParameter("work_number");
		String branch_name = request.getParameter("branch_name");
		String manager_id = request.getParameter("manager_id");

		Employees existingEmployee = employeesCrud.getEmployeeById(ID);
		if (first_name != "")
			existingEmployee.setFirst_name(first_name);
		if (last_name != "")
			existingEmployee.setLast_name(last_name);
		if (email != "")
			existingEmployee.setEmail(email);
		if (phone != "")
			existingEmployee.setPhone(phone);
		if (birthdate != null) {
			existingEmployee.setBirthday(birthdate);
		}
		if(work_number!=null) {
			existingEmployee.setWork_number(work_number);
		}
		if(branch_name!= null) {
			existingEmployee.setBranch_name(branch_name);
		}
		if(manager_id!= null) {
			existingEmployee.setManager_id(manager_id);
		}
		employeesCrud.updateEmployee(existingEmployee);
		response.sendRedirect("list");
	}

	private void deleteClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, InterruptedException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		String ID = request.getParameter("ID");
		employeesCrud.removeEmployee(ID);
		response.sendRedirect("list");
		return;
	}

	private void removeForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, InterruptedException {
		if (!securityService.userIsAdmin(request)) {
			denyAccess(request, response);
			return;
		}
		
		List<Employees> listEmployee = employeesCrud.getAllEmployees();
		request.setAttribute("listEmployee", listEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RemoveEmployee.jsp");
		dispatcher.forward(request, response);
	}
}
