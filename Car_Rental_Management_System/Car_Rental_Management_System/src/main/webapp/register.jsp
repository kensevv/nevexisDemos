<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>

    <link rel="stylesheet" href="style/screen2Style.css">
</head>
<body>

    <div class="FormBox">
		<div class="inside">

			<h1>Kenan's Car Rental System</h1>
			<h2 style="color: grey">Register</h2>

			<form method="get" action="RegisterServlet">
				<input class="text" id="username" type="text" name="username"
					placeholder="User Name" required> <input class="text"
					id="email" type="text" name="email" placeholder="Email" required>
				<input class="text" id="password" type="password" name="password"
					placeholder="Password" required>
				<div class="redirect">
					<button type="submit" id="submit">Register</button>
				</div>
			</form>
		</div>

	</div>
    
</body>

<style><%@include file="css/registerStyle.css"%></style>
</html>