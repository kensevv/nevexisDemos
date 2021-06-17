
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee List</title>
</head>
<body>
	<%@include file="htmlTemplates/navigationBar.html"%>
	
	<div class="wrapper">
		<label for="orderBy" id="modifyTitle">Sort by:</label>
		<form method="get" action="list">
			<select name="orderBy" id="employee">
				<option value="ID">Employee ID</option>
				<option value="first_name">First Name</option>
				<option value="last_name">Last Name</option>
				<option value="email">Email</option>
				<option value="phone">Phone number</option>
				<option value="birthday">Birthday</option>
				<option value="work_number">Work Number</option>
				<option value="branch_name">Branch Name</option>
				<option value="manager_id">Manager Id</option>
			</select>
			<button type="submit" class="button">Sort</button>
		</form>

		<div class="table">

			<div class="row header">
				<div class="cell">Client ID</div>
				<div class="cell">First Name</div>
				<div class="cell">Last Name</div>
				<div class="cell">Email</div>
				<div class="cell">Phone number</div>
				<div class="cell">Birthday</div>
				<div class="cell">Work Number</div>
				<div class="cell">Branch Name</div>
				<div class="cell">Manager ID</div>
			</div>
			<c:forEach var="employee" items="${listEmployee}">
				<div class="row">
					<div class="cell" data-title="ID">
						<c:out value="${employee.ID}" />
					</div>
					<div class="cell" data-title="First Name">
						<c:out value="${employee.first_name}" />
					</div>
					<div class="cell" data-title="Last Name">
						<c:out value="${employee.last_name}" />
					</div>
					<div class="cell" data-title="Email">
						<c:out value="${employee.email}" />
					</div>
					<div class="cell" data-title="Phone">
						<c:out value="${employee.phone}" />
					</div>
					<div class="cell" data-title="Birthday">
						<c:out value="${employee.birthday}" />
					</div>
					<div class="cell" data-title="Work Number">
						<c:out value="${employee.work_number}" />
					</div>
					<div class="cell" data-title="Branch Name">
						<c:out value="${employee.branch_name}" />
					</div>
					<div class="cell" data-title="Manager ID">
						<c:out value="${employee.manager_id}" />
					</div>
				</div>
			</c:forEach>
		</div>
		<a class="fcc-btn" href="../pdf/employees">DOWNLOAD EMPLOYEES PDF</a>	
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>