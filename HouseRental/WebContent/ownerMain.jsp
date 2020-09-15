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
		<c:if test="${tab1 == 1}"> <a class="active" href="<%=request.getContextPath()%>/ohome">Home</a> 
			<a href="viewProfile.jsp">Your Profile</a>
			<a href="<%=request.getContextPath()%>/olist">View Rentals</a>
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if>
		<c:if test="${tab1 == 2}"> <a href="<%=request.getContextPath()%>/ohome">Home</a> 
			<a href="viewProfile.jsp">Your Profile</a>
			<a class="active" href="<%=request.getContextPath()%>/olist">View Rentals</a>
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if>
	</div>
	<br>
	
	<c:if test="${tab1 == 1}">
	<div class="row">
		<div class="container">
			<h3 class="text-center">Welcome ${user.firstName}</h3>
			<h5 class="text-center">Creating Adventures Starts Here</h5>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/onew" class="btn btn-success">Add Rental</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Image</th>
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
							<td><c:out value="$ ${rental.price}" /></td>
							<td><c:out value="${rental.location}" /></td>
							<td><c:out value="${rental.description}" /></td>
							<td><a href="oedit?id=<c:out value='${rental.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="odelete?id=<c:out value='${rental.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	</c:if>
	<c:if test="${tab1 == 2}">
		<div class="row">
			<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
	
			<div class="container">
				<br>
				<h3 class="text-center">Your Rented Rentals</h3>
				<hr>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Image</th>
							<th>Location</th>
							<th>Rentee</th>
							<th>Dates</th>
							<th>Info</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rental" items="${ownerRented}">
	
							<tr>
								<td><img  height="200px" width="400px" src="<c:out value="${rental.imageFName}"/>"/></td>
								<td><c:out value="${rental.location}" /></td>
								<td><c:out value="${rental.rentee}" /></td>
								<td><c:out value="${rental.startDate} to ${rental.endDate}" /></td>
								<td><form action="<%=request.getContextPath()%>/viewForm" method="post">
										<input type="hidden" name="selectedRentalForm" value="${rental.formID}"/> 
										<div class="infoButton">
											<input class="submitButton" type="submit" width="100" name="viewForm" value="View Contract"/>
										</div>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
</body>
</html>