<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<style>
		<%@ include file="css/mainView.css"%>
	</style>
<head>
<title>Owner Main Menu</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>
	<div class="topnav">
		<a href="<%=request.getContextPath()%>/ohome">Home</a> <a
			href="viewProfile.jsp">Your Profile</a> <a href="ownerMain.jsp"> View Rentals</a> 
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
	</div>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${rental != null}">
					<form action="<%=request.getContextPath()%>/oupdate" method="post">
				</c:if>
				<c:if test="${rental == null}">
					<form action="<%=request.getContextPath()%>/oinsert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${rental != null}">
                                    Edit Rental
                                </c:if>
						<c:if test="${rental == null}">
                                    Add New Rental
                                </c:if>
					</h2>
				</caption>

				<c:if test="${rental != null}">
					<input type="hidden" name="id" value="<c:out value='${rental.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Price</label> <input type="text"
						value="<c:out value='${rental.price}' />" class="form-control"
						name="price" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Location</label> <input type="text"
						value="<c:out value='${rental.location}' />" class="form-control"
						name="location" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${rental.description}' />" class="form-control"
						name="description" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Image</label> <input type="text" class="form-control"
						value="<c:out value='${rental.imageFName}' />" 
						placeholder="Enter Image URL" name="image" required="required">
				</fieldset>
				<button class="submitButton" type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>