<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
</head>
<body>
	<div class="login-in" align="center">
		<h1>Login Form</h1>
		<a href="signUp.jsp">Sign Up</a>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<table style="with: 100%">
				<tr>
					<td>Username</td>
					<td><input type="text" name="uname" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="pass" /></td>
				</tr>
			</table>
			<input type="submit" value="Login"/>
		</form>
	</div>
</body>
</html>