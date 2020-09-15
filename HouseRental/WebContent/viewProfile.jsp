<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
	<%@ include file="css/mainView.css"%>
</style>
<title>View Profile</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="topnav">
		<c:if test="${user.userType == 'admins'}">
			<a href="<%=request.getContextPath()%>/ahome">Home</a>
			<a class="active" href="viewProfile.jsp">Your Profile</a>
			<a href="<%=request.getContextPath()%>/alist">View Users</a>
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if>
		<c:if test="${user.userType == 'owners'}"> <a href="<%=request.getContextPath()%>/ohome">Home</a> 
			<a class="active" href="viewProfile.jsp">Your Profile</a> 
			<a href="<%=request.getContextPath()%>/olist">View Rentals</a> 
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if> 
		<c:if test="${user.userType == 'customers'}"> <a href="${type1}Main.jsp">Home</a> 
			<a class="active" href="viewProfile.jsp">Your Profile</a> 
			<a href="viewRentals.jsp">View Rentals</a> 
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if> 
	</div>

	<div align="center">
	<br>
		<h1>Edit Your Profile</h1>
	</div>
	<div align="center">
		<c:if test="${user != null}">
			<form action="update" method="post"></form>
		</c:if>
		<c:if test="${user == null}">
			<form action="insert" method="post"></form>
		</c:if>
		<form action="<%=request.getContextPath()%>/saveUser" method="post">
			<table border="1" cellpadding="5">

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>
				<tr>
					<th>First Name:</th>
					<td><input type="text" name="fname" size="45"
						value="<c:out value='${user.firstName}' />" /></td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lname" size="45"
						value="<c:out value='${user.lastName}' />" /></td>
				</tr>
				<tr>
					<th>Username:</th>
					<td><input type="text" name="uname" size="45"
						value="<c:out value='${user.username}' />" /></td>
				</tr>
				<tr>
					<th>User Email:</th>
					<td><input type="text" name="email" size="45"
						value="<c:out value='${user.email}' />" /></td>
				</tr>
				<tr>
					<th>Birthday:</th>
					<td><input type="date" name="birthday" size="45"
						value="<c:out value='${user.bday}' />" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="submitButton" type="submit"
						value="Save" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>