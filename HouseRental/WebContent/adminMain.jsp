<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<style>
		<%@ include file="css/mainView.css"%>
	</style>
<head>
<title>Admin Main Menu</title>
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
		<c:if test="${tab == 1}"> <a class="active" href="<%=request.getContextPath()%>/ahome">Home</a> 
			<a href="viewProfile.jsp">Your Profile</a>
			<a href="<%=request.getContextPath()%>/alist">View Users</a>
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if>
		<c:if test="${tab == 2}"> <a href="<%=request.getContextPath()%>/ahome">Home</a> 
			<a href="viewProfile.jsp">Your Profile</a>
			<a class="active" href="<%=request.getContextPath()%>/alist">View Users</a>
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if>
	</div>
	<div class="row">
		<c:if test="${tab == 1}">
		<div class="container">
			<br>
			<h3 class="text-center">Welcome Jaden</h3>
			<table class="table table-bordered">
					<thead>
					<br>
						<tr>
							<th># of Total Users</th>
							<th># of Customers</th>
							<th># of Owners</th>
							<th># of Rentals</th>
							<th># of Rentals Rented out</th>
						</tr>
					</thead>
					<tbody>

					<tr>
						<td><c:out value="${numUsers}" /></td>
						<td><c:out value="${numCustomers}" /></td>
						<td><c:out value="${numOwners}" /></td>
						<td><c:out value="${numRentals}" /></td>
						<td><c:out value="${numRented}" /></td>
					</tr>
						<!-- } -->
					</tbody>

				</table>
		</div>
		</c:if>
		<c:if test="${tab == 2}">
			<div class="container">
				<br>
				<h3 class="text-center">List of Users</h3>
				<hr>
				<div class="container text-left">

					<a href="<%=request.getContextPath()%>/anew" class="btn btn-success">Add
						New User</a>
				</div>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Username</th>
							<th>Password</th>
							<th>User Type</th>
							<th>Options</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="user" items="${listUser}">

							<tr>
								<td><c:out value="${user.id}" /></td>
								<td><c:out value="${user.username}" /></td>
								<td><c:out value="${user.password}" /></td>
								<td><c:out value="${user.userType.substring(0,user.userType.length()-1)}" /></td>

								<td><a
									href="aedit?id=<c:out value='${user.id}' />&type=${user.userType}">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="adelete?id=<c:out value='${user.id}' />&type=${user.userType}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</c:if>
	</div>
</body>

</html>