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
	<div class="topnav">
		<a class="active" href="<%=request.getContextPath()%>/home">Home</a> <a
			href="viewProfile.jsp">Your Profile</a> <a href="adminMain.jsp"> View Users</a> 
			<a href="<%=request.getContextPath()%>/logout">Logout</a>
	</div>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
                                    Edit User
                                </c:if>
						<c:if test="${user == null}">
                                    Add New User
                                </c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>First Name</label> <input type="text"
						value="<c:out value='${user.firstName}' />" class="form-control"
						name="fname" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Last Name</label> <input type="text"
						value="<c:out value='${user.lastName}' />" class="form-control"
						name="lname" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Birthday</label> <input type="text"
						value="<c:out value='${user.bday}' />" class="form-control"
						name="bday" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>User Type</label> <input type="text"
						value="<c:out value='${user.userType}' />" class="form-control"
						name="type" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Username</label> <input type="text"
						value="<c:out value='${user.username}' />" class="form-control"
						name="uname" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Password</label> <input type="text"
						value="<c:out value='${user.password}' />" class="form-control"
						name="pass" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>