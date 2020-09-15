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
		<h6 style="text-align: center">Rent Now From: </h6>
		<form action="<%=request.getContextPath()%>/rent" method="post"
			style="text-align: center">
			<p id="rented_dates" class="hide" value="${allRentedDatesString}">${allRentedDatesString}</p> 
			<script>
				var dates = document.getElementById('rented_dates').innerHTML.split(',');
				//var dates = ["2020-09-12","2020-09-20","2020-09-21","2020-09-22"];
			
				function DisableDates(date) {
				    var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
				    return [dates.indexOf(string) == -1];
				}
				 
				$(function() {
				     $(".rentalDates").datepicker({
				    	 dateFormat: "yy-mm-dd",
				         showButtonPanel: true,
				         changeMonth: true,
				         changeYear: true,
						 yearRange: '2020:2021',
				         minDate: new Date(2020, 9 - 1, 11),
				         beforeShowDay: DisableDates
				     });
				});		
			</script>
			<input class="rentalDates" type="text" name="rentalStartDate" min="${firstAvailDate}" size="45"
				value="${firstAvailDate}" /> 
				<h5> to </h5>
			<input class="rentalDates" type="text" name="rentalEndDate" min="${firstAvailDate}" size="45"
				 size="45" value="${firstAvailDate}"/> 
				 <br><br>
			<input type="hidden" name="selectedRentalID" value="${rental.id}" /> 
			<input class="rentButton" type="submit" name="rent" value="Rent Now!" />
		</form>
		<br>
	</div>
</body>
</html>