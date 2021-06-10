
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clients List</title>
</head>
<body>
	<%@include file="htmlTemplates/navigationBar.html"%>
	
	<div class="wrapper">
		<label for="orderBy" id="modifyTitle">Sort by:</label>
		<form method="get" action="list">
			<select name="orderBy" id="car">
				<option value="ID">Client ID</option>
				<option value="first_name">First Name</option>
				<option value="last_name">Last Name</option>
				<option value="email">Email</option>
				<option value="phone">Phone number</option>
				<option value="birthday">Birthday</option>
				<option value="driver_lic">Driver License ID</option>
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
				<div class="cell">Driver License ID</div>
			</div>
			<c:forEach var="client" items="${listClient}">
				<div class="row">
					<div class="cell" data-title="ID">
						<c:out value="${client.ID}" />
					</div>
					<div class="cell" data-title="First Name">
						<c:out value="${client.first_name}" />
					</div>
					<div class="cell" data-title="Last Name">
						<c:out value="${client.last_name}" />
					</div>
					<div class="cell" data-title="Email">
						<c:out value="${client.email}" />
					</div>
					<div class="cell" data-title="Phone">
						<c:out value="${client.phone}" />
					</div>
					<div class="cell" data-title="Birthday">
						<c:out value="${client.birthday}" />
					</div>
					<div class="cell" data-title="Driver License">
						<c:out value="${client.driver_lic}" />
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>