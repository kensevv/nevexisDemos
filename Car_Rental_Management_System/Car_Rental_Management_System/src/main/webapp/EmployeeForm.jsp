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
		<form action="employees/insert" method="get">
			<table border="1" cellpadding="5">
				<tr>
					<th>ID:</th>
					<td><input type="text" name="ID"
						placeholder="Employee ID"></input></td>
				</tr>
				<tr>
					<th>First Name:</th>
					<td><input type="text" name="first_name" placeholder="First Name"></input>
					</td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td><input class="input" type="text" name="last_name"
						placeholder="Last Name"></input></td>
				</tr>
				<tr>
					<th>Email:</th>
					<td><input class="input" type="text" name="email"
						placeholder="Email"></input></td>
				</tr>
				<tr>
					<th>Phone Number:</th>
					<td><input class="input" type="text" name="phone"
						placeholder="Phone"></input></td>
				</tr>
				<tr>
					<th>Birthday:</th>
					<td><input class="input" type="text" name="birthdate"
						placeholder="Birthdate"></input></td>
				</tr>
				<tr>
					<th>Work Number:</th>
					<td><input class="input" type="text" name="work_number"
						placeholder="Work Number"></input></td>
				</tr>
				<tr>
					<th>Branch Name:</th>
					<td><input class="input" type="text" name="branch_name"
						placeholder="Branch Name"></input></td>
				</tr>
				<tr>
					<th>Manager Id:</th>
					<td><input class="input" type="text" name="manager_id"
						placeholder="Manager Id"></input></td>
				</tr>
				<tr>
				</tr>
			</table>
			<button class="button" type="submit" class="button">Add
				Employee</button>
		</form>
	</div>
</body>
<style><%@include file="css/style.css"%></style>
</html>