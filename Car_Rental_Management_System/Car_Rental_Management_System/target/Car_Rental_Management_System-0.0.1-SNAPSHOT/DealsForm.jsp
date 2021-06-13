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
		<form action="deals/insert" method="get">
			<table border="1" cellpadding="5">
				<tr>
					<th>start date:</th>
					<td><input type="text" name="start_date"
						placeholder="Start Date"></input></td>
				</tr>
				<tr>
					<th>end date:</th>
					<td><input type="text" name="end_date" placeholder="End Date"></input>
					</td>
				</tr>
				<tr>
					<th>vehicle license plate:</th>
					<td><input class="input" type="text" name="vehicle_licPlate"
						placeholder="Vehicle License Plate"></input></td>
				</tr>
				<tr>
					<th>client:</th>
					<td><input class="input" type="text" name="client_id"
						placeholder="Client ID"></input></td>
				</tr>
				<tr>
					<th>employee:</th>
					<td><input class="input" type="text" name="employee_id"
						placeholder="Employee ID"></input></td>
				</tr>
				<tr>
					<th>branch:</th>
					<td><input class="input" type="text" name="branch_name"
						placeholder="Branch Name"></input></td>
				</tr>
				<tr>
					<th>payment:</th>
					<td><input class="input" type="text" name="payment"
						placeholder="Payment"></input></td>
				</tr>
				<tr>
				</tr>
			</table>
			<button class="button" type="submit" class="button">Add
				Deal</button>
		</form>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>