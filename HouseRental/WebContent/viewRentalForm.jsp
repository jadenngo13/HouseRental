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
	<%
		if(session.getAttribute("user") == null)
			response.sendRedirect("login.jsp");
	%>
	<div class="topnav">
		<c:if test="${user.userType == 'owners'}">
			<a href="ownerMain.jsp">Home</a>
			<a href="viewProfile.jsp">Your Profile</a>
			<a href="viewRentals.jsp">View Rentals</a>
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
		</c:if>
	</div>

	<div align="center">
		<br>
		<h1>Contract Agreement</h1>
	</div>
	<div align="center">
		<form action="<%=request.getContextPath()%>/olist">
			<script>
				$(function() {
				     $(".rentalDates").datepicker({
				    	 dateFormat: "yy-mm-dd",
				         showButtonPanel: true,
				         changeMonth: true,
				         changeYear: true,
						 yearRange: '2020:2021',
				         minDate: new Date(2020, 9 - 1, 11),
				         beforeShowDay:function(date){
				            return [false, ''];
				        }
				     });
				});		
			</script>
			<table border="1" cellpadding="5">
				<tr>
					<th>Rentee First Name:</th>
					<td><input type="text" name="cfname" size="45"
						value="<c:out value='${customer.firstName}' />" /></td>
				</tr>
				<tr>
					<th>Rentee Last Name:</th>
					<td><input type="text" name="clname" size="45"
						value="<c:out value='${customer.lastName}' />" /></td>
				</tr>
				<tr>
					<th>Rentee Email:</th>
					<td><input type="text" name="cemail" size="45"
						value="<c:out value='${customer.email}' />" /></td>
				</tr>
				<tr>
					<th>Rentee Birthday:</th>
					<td><input type="text" name="cbirthday" size="45"
						value="<c:out value='${customer.bday}' />" /></td>
				</tr>
				<tr>
					<th>Rental Start Date:</th>
					<td><input class="rentalDates" type="text" name="startDate" size="45"
						value="<c:out value='${rentalForm.startDate}' />" /></td>
				</tr>
				<tr>
					<th>Rental End Date:</th>
					<td><input class="rentalDates" type="text" name="endDate"  size="45"
						value="<c:out value='${rentalForm.endDate}' />" /></td>
				</tr>
				<tr>
					<th>Contract Total:</th>
					<td><input type="text" name="price" size="45"
						value="<c:out value='$ ${rentalForm.price}' />" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="submitButton" type="submit"
						value="Close" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>