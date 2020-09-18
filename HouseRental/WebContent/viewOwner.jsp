<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
	<%@ include file="css/mainView.css"%>
</style>
<title>Owner Info</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<%
		if(session.getAttribute("user") == null)
			response.sendRedirect("login.jsp");
	%>
	<div class="topnav">
		<c:if test="${user.userType == 'customers'}"> <a href="${type1}Main.jsp">Home</a> 
			<a class="active" href="viewProfile.jsp">Your Profile</a> 
			<a href="viewRentals.jsp">View Rentals</a> 
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if> 
	</div>

	<div align="center">
	<br>
		<h1>Owner Info</h1>
	</div>
	<div align="center">
		<form action="customerMain.jsp" method="post">
			<table border="1" cellpadding="5">
				<tr>
					<th>First Name:</th>
					<td><input type="text" name="fname" size="45"
						value="<c:out value='${owner.firstName}' />" /></td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lname" size="45"
						value="<c:out value='${owner.lastName}' />" /></td>
				</tr>
				<tr>
					<th>Email:</th>
					<td><input type="text" name="email" size="45"
						value="<c:out value='${owner.email}' />" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="submitButton" type="submit"
						value="Close" /></td>
				</tr>
			</table>
			<br>
			<p>For actions such as cancelling rentals, please contact the respective owner of the house.</p>
		</form>
	</div>
</body>
</html>