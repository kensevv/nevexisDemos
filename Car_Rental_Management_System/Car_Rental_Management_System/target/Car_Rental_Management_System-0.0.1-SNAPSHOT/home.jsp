<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<%@include file="htmlTemplates/navigationBar.html"%>
<body>
	<div id="root">
		<h1 class="header">Welcome to Kenan Yusein's Car Management System! ${sessionScope.user}</h1>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>