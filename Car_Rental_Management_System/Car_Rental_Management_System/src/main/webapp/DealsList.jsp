
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deals List</title>
</head>
<body>
	<%@include file="htmlTemplates/navigationBar.html"%>
	
	<div class="wrapper">
		<label for="orderBy" id="modifyTitle">Sort by:</label>
		<form method="get" action="list">
			<select name="orderBy" id="deal">
				<option value="start_date">Start Date</option>
				<option value="end_date">End Date</option>
				<option value="vehicle_licPlate">Vehicle License Plate</option>
				<option value="client_id">Client</option>
				<option value="employee_id">Employee</option>
				<option value="branch_name">Branch</option>
				<option value="payment">Payment</option>
			</select>
			<button type="submit" class="button">Sort</button>
		</form>

		<div class="table">

			<div class="row header">
				<div class="cell">Start Date</div>
				<div class="cell">End Date</div>
				<div class="cell">Vehicle License Plate</div>
				<div class="cell">Client</div>
				<div class="cell">Employee</div>
				<div class="cell">Branch</div>
				<div class="cell">Payment</div>
			</div>
			<c:forEach var="deal" items="${listDeal}">
				<div class="row">
					<div class="cell" data-title="licPlate">
						<c:out value="${deal.start_date}" />
					</div>
					<div class="cell" data-title="Model">
						<c:out value="${deal.end_date}" />
					</div>
					<div class="cell" data-title="Insurance">
						<c:out value="${deal.vehicle_licPlate}" />
					</div>
					<div class="cell" data-title="Is Available">
						<c:out value="${deal.client_id}" />
					</div>
					<div class="cell" data-title="Mileage">
						<c:out value="${deal.employee_id}" />
					</div>
					<div class="cell" data-title="Price">
						<c:out value="${deal.branch_name}" />
					</div>
					<div class="cell" data-title="Branch">
						<c:out value="${deal.payment}" />
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>