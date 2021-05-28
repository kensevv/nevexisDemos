<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="root">
<%@include file="htmlTemplates/navigationBar.html"%>
		<main>
			<div class="box">
				<label for="license_plate" id="modifyTitle">Choose a car to
					remove:</label>
				<form method="get" action="delete">
					<select name="license_plate" id="cars">
						<c:forEach var="vehicle" items="${listVehicle}">
							<option value="${vehicle.license_plate}">
								<c:out value="${vehicle.license_plate}" />
							</option>
						</c:forEach>
					</select>
					<button type="submit" class="button">Remove Vehicle</button>
				</form>
			</div>
		</main>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>