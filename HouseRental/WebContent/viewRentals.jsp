<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	<%@ include file="css/mainView.css"%>
</style>
<meta charset="UTF-8">
<title>View Rentals</title>
</head>
<body>
	<div class="topnav">
		<a href="${type1}Main.jsp">Home</a> <a href="viewProfile.jsp">Your Profile</a> <a class="active"
			href="viewRentals.jsp">View Rentals</a> <a href="<%=request.getContextPath()%>/logout">Logout</a>
	</div>
	<h2>View Rentals HERE!</h2>
</body>
</html>