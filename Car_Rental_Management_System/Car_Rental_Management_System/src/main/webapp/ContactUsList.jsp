
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Messages from Contact-Us</title>
</head>
<body>
	<%@include file="htmlTemplates/navigationBar.html"%>
	
	<div class="wrapper">
		<label for="orderBy" id="modifyTitle">Sort by:</label>
		<form method="get" action="list">
			<select name="orderBy" id="car">
				<option value="name">First Name</option>
				<option value="email">Email</option>
			</select>
			<button type="submit" class="button">Sort</button>
		</form>
		<div class="table">

			<div class="row header">
				<div class="cell">Name</div>
				<div class="cell">Email</div>
				<div class="cell">Message</div>
			</div>
			<c:forEach var="contactus" items="${listContactUs}">
				<div class="row">
					<div class="cell" data-title="name">
						<c:out value="${contactus.name}" />
					</div>
					<div class="cell" data-title="Email">
						<c:out value="${contactus.email}" />
					</div>
					<div class="cell" data-title="Last Name">
						<c:out value="${contactus.message}" />
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>