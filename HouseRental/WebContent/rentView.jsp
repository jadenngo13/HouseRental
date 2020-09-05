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
<title>Insert title here</title>
</head>
<body>
	<div class="topnav">
		<a href="customerMain.jsp">Home</a> <a
			href="viewProfile.jsp">Your Profile</a> <a href="viewRentals.jsp">View
			Rentals</a> <a href="<%=request.getContextPath()%>/logout">Logout</a>
	</div>
	<h2>Rent HERE!</h2>
	
			<div class="container">
			<h3 class="text-center">Rent This Beautiful Property In ${location}</h3>
			<h5 class="text-center">Your Adventure Starts Here</h5>
			<hr>
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
							<td><c:out value="${rental.imageFName}" /></td>
							<td><c:out value="$ ${rental.price}" /></td>
							<td><c:out value="${rental.location}" /></td>
							<td><c:out value="${rental.description}" /></td>
							<td><div class="container text-left">
							<form action="rentView.jsp"> 
								<input type="hidden" name="selectedRentalID" value="${rental.id}" />
   								<input type="submit" name="rent" value="Rent Now!" />


							<%-- <a href="<%=request.getContextPath()%>/rent"
										class="btn btn-success">Rent Now!</a> --%> </form>
							</div></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
</body>
</html>