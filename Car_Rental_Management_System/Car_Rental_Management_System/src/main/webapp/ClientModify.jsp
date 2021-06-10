<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Client</title>
</head>
<body>
	<%@include file="htmlTemplates/navigationBar.html"%>
	
	<div class="box">
		<label for="ID" id="modifyTitle">Choose a Client to modify:</label>
		<form method="get" action="update">
			<select name="ID" id="cars">
				<c:forEach var="client" items="${listClient}">
					<option value="${client.ID}">
						<c:out value="${client.ID}" />
					</option>
				</c:forEach>
			</select>
			<div class="modify">
				<input type="text" name="first_name" placeholder="First Name"></input> 
				<input class="input" type="text" name="last_name" placeholder="Last Name"></input>
				<input class="input" type="text" name="email" placeholder="Email"></input> 
				<input class="input" type="text" name="phone" placeholder="Phone"></input> 
				<input class="input" type="text" name="birthdate" placeholder="Birthdate"></input>
				<input class="input" type="text" name="driver_lic" placeholder="Driver License"></input>
				<button type="submit" class="button">Modify Client</button>
			</div>
		</form>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>