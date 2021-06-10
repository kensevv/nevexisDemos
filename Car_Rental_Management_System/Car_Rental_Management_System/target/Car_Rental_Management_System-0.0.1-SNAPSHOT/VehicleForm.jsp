<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Car Rental Management System Application</title>
</head>
<body>
	<%@include file="htmlTemplates/navigationBar.html"%>
	
	<h1 class="header">Welcome to Kensev's Car Rental Management
		System</h1>
	<div align="center">
		<form action="insert" method="get">
			<table border="1" cellpadding="5">
				<tr>
					<th>licPlate:</th>
					<td><input type="text" name="licPlate"
						placeholder="License Plate"></input></td>
				</tr>
				<tr>
					<th>Model:</th>
					<td><input type="text" name="model" placeholder="Model"></input>
					</td>
				</tr>
				<tr>
					<th>Insurance:</th>
					<td><input class="input" type="text" name="insurance"
						placeholder="insurance"></input></td>
				</tr>
				<tr>
					<th>isAvailable:</th>
					<td><input class="input" type="text" name="isAvailable"
						placeholder="is available"></input></td>
				</tr>
				<tr>
					<th>Milleage:</th>
					<td><input class="input" type="text" name="milleage"
						placeholder="Milleage"></input></td>
				</tr>
				<tr>
					<th>Price:</th>
					<td><input class="input" type="text" name="price"
						placeholder="price"></input></td>
				</tr>
				<tr>
				</tr>
			</table>
			<button class="button" type="submit" class="button">Add
				Vehicle</button>
		</form>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>