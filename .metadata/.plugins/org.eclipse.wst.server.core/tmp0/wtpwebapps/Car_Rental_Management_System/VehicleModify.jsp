<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Vehicle</title>
</head>
<body>
	<%@include file="htmlTemplates/navigationBar.html"%>
	
	<div class="box">
		<label for="licPlate" id="modifyTitle">Choose a car to modify:</label>
		<form method="get" action="update">
			<select name="licPlate" id="cars">
				<c:forEach var="vehicle" items="${listVehicle}">
					<option value="${vehicle.licPlate}">
						<c:out value="${vehicle.licPlate}" />
					</option>
				</c:forEach>
			</select>
			<div class="modify">
				<input type="text" name="model" placeholder="Model"></input> <input
					class="input" type="text" name="insurance" placeholder="insurance"></input>
				<input class="input" type="text" name="isAvailable"
					placeholder="is available"></input> <input class="input"
					type="text" name="milleage" placeholder="Milleage"></input> <input
					class="input" type="text" name="price" placeholder="price"></input>

				<button type="submit" class="button">Modify Vehicle</button>
			</div>
		</form>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>