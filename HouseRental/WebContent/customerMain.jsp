<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<style>
		<%@ include file="css/mainView.css"%>
	</style>
<head>
<meta charset="UTF-8">
<title>Main Menu</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>
	<div class="topnav">
		<a class="active" href="customerMain.jsp">Home</a> <a
			href="viewProfile.jsp">Your Profile</a> <a href="viewRentals.jsp">View
			Rentals</a> <a href="<%=request.getContextPath()%>/logout">Logout</a>
	</div>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Hello ${fname}</h3>
			<h5 class="text-center">Your Adventure Starts Here</h5>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th> </th>
						<th>Price</th>
						<th>Location</th>
						<th>Description</th>
						<th>Rent Now</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rental" items="${listRentals}">

						<tr>
							<td><img  height="200px" width="400px" src="<c:out value="${rental.imageFName}"/>"/></td>
						<%-- 	<td><c:out value="${rental.imageFName}" /></td> --%>
							<td><c:out value="$ ${rental.price}" /></td>
							<td><c:out value="${rental.location}" /></td>
							<td><c:out value="${rental.description}" /></td>
							<td><div class="container text-left">
							<form action="<%=request.getContextPath()%>/rentView" method="get"> 
								<input type="hidden" name="selectedRentalID" value="${rental.id}" />
   								<input type="submit" name="rent" value="Start My Adventure!" /> 


							<%-- <a href="<%=request.getContextPath()%>/rent"
										class="btn btn-success">Rent Now!</a> --%> </form>
							</div></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>