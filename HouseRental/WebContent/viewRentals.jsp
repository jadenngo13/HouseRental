<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
	<%@ include file="css/mainView.css"%>
</style>
<meta charset="UTF-8">
<title>View Rentals</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="topnav">
		<a href="${type1}Main.jsp">Home</a> <a href="viewProfile.jsp">Your Profile</a> <a class="active"
			href="viewRentals.jsp">View Rentals</a> <a href="<%=request.getContextPath()%>/logout">Logout</a>
	</div>
	
	<c:if test="${type2 == 2}">
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
										<div class="rentButton" style="text-align: center">
											<input type="submit" name="viewForm" value="View Info"/>
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
	
	<c:if test="${type2 == 3}">
		<div class="row">
			<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
	
			<div class="container">
				<br>
				<h3 class="text-center">Your Rentals</h3>
				<hr>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Number of Days</th>
							<th>Location</th>
							<th>Description</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rental" items="${customerRentals}">
	
							<tr>
								<td><c:out value="${rental.daysMsg}" /></td>
								<td><c:out value="${rental.location}" /></td>
								<td><c:out value="${rental.description}" /></td>
								<td><img  height="200px" width="400px" src="<c:out value="${rental.imageFName}"/>"/></td>
							</tr>
						</c:forEach>
					</tbody>
	
				</table>
			</div>
		</div>
	</c:if>
</body>
</html>