<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<head>
	<style>
		<%@ include file="css/mainView.css"%>
	</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<script>
		var dates = ["2020-09-12", "2020-09-13", "2020-09-14"];
		
		function DisableDates(date) {
		    var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
		    return [dates.indexOf(string) == -1];
		}
		 
		$(function() {
		     $(".rentalDates").datepicker({
		         beforeShowDay: DisableDates
		     });
		});
	</script>

	<div class="topnav">
		<a href="customerMain.jsp">Home</a> <a href="viewProfile.jsp">Your
			Profile</a> <a href="viewRentals.jsp">View Rentals</a> <a
			href="<%=request.getContextPath()%>/logout">Logout</a>
	</div>
	<br>
	<div class="container">
		<h4 class="text-center">Rent This Beautiful House In
			${rentalLocation} NOW!</h4>
		<hr>
		<br> <img src="${imageFileUrl}"
			alt="Image of ${rentalLocation} House" width="800" height="600">
		<br>
		<h5 style="text-align: center">${rentalDescription}</h5>
		<h5 style="text-align: center">Location: ${rentalLocation}</h5>
		<form action="<%=request.getContextPath()%>/rent" method="post"
			style="text-align: center">
			<input class="rentalDates" type="text" name="rentalEndDate" min="${rentalEndDate}" size="45"
				value="${rentalEndDate}" /> 
			<input class="rentalDates" type="text" name="rentalAvailDate" min="${rentalEndDate}" size="45"
				 size="45" value="${rentalEndDate}"/> 
			<input type="hidden" name="selectedRentalID" value="${rental.id}" /> 
			<div class="rentButton" style="text-align: center">
				<input type="submit" name="rent" value="Rent Now!" />
			</div>	
		</form>
		<br>
	</div>
</body>
</html>