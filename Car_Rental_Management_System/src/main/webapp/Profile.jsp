<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile</title>
</head>
<body>
    <div id="root">
       
<%@include file="htmlTemplates/navigationBar.html"%>
        <main>
            <div id="Profile">
                <div id="myProfile">
                    <img class="avatar" src="icons/Georgi.jpg" alt="${sessionScope.account.username}" />
                    <label>${sessionScope.account.username}</label>
                    <label>${sessionScope.account.email}</label>
                </div>
                <div id="Info">
                    <p>
                        <label>Cars</label>
                        <label id="number">0</label>
                    </p>
                    <p>
                        <label>Money</label>
                        <label id="number">100</label>
                    </p>
                    
                    <button id="logout">Log Out</button>
                </div>
            </div>
        </main>
    </div>
</body>

<style><%@include file="css/profileStyle.css"%></style>
<style><%@include file="css/style.css"%></style>
</html>