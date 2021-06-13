<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Employee</title>
</head>
<body>
	<%@include file="htmlTemplates/navigationBar.html"%>
	
	<div class="box">
		<label for="ID" id="modifyTitle">Choose an Employee to modify:</label>
		<form method="get" action="update">
			<select name="ID" id="cars">
				<c:forEach var="employee" items="${listEmployee}">
					<option value="${employee.ID}">
						<c:out value="${employee.ID}" />
					</option>
				</c:forEach>
			</select>
			<div class="modify">
				<input type="text" name="first_name" placeholder="First Name"></input> 
				<input class="input" type="text" name="last_name" placeholder="Last Name"></input>
				<input class="input" type="text" name="email" placeholder="Email"></input> 
				<input class="input" type="text" name="phone" placeholder="Phone"></input> 
				<input class="input" type="text" name="birthdate" placeholder="Birthdate"></input>
				<input class="input" type="text" name="work_number" placeholder="Work Number"></input>
				<input class="input" type="text" name="branch_name" placeholder="Branch Name"></input>
				<input class="input" type="text" name="manager_id" placeholder="Manager Id"></input>
				<button type="submit" class="button">Modify Employee</button>
			</div>
		</form>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>