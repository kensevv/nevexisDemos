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
				<label for="ID" id="modifyTitle">Choose a Client to
					remove:</label>
				<form method="get" action="delete">
					<select name="ID" id="cars">
						<c:forEach var="client" items="${listClient}">
							<option value="${client.ID}">
								<c:out value="${client.ID}" />
							</option>
						</c:forEach>
					</select>
					<button type="submit" class="button">Remove Client</button>
				</form>
			</div>
		</main>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>