<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
	<%@ include file="css/mainView.css"%>
</style>
<title>View Profile</title>
</head>
<body>
	<div class="topnav">
		<a href="${type1}Main.jsp">Home</a> <a class="active" href="viewProfile.jsp">Your Profile</a> <a
			href="viewRentals.jsp">View Rentals</a> <a href="<%=request.getContextPath()%>/logout">Logout</a>
	</div>

	<div align="center">
		<h1>Hello ${name}</h1>
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
						value="<c:out value='${fname}' />" /></td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lname" size="45"
						value="<c:out value='${lname}' />" /></td>
				</tr>
				<tr>
					<th>Username:</th>
					<td><input type="text" name="uname" size="45"
						value="<c:out value='${username}' />" /></td>
				</tr>
				<tr>
					<th>User Email:</th>
					<td><input type="text" name="email" size="45"
						value="<c:out value='${email}' />" /></td>
				</tr>
				<tr>
					<th>Birthday:</th>
					<td><input type="text" name="birthday" size="45"
						value="<c:out value='${birthday}' />" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>