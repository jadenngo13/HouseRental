<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
	<div class="sign-up" align="center">
	<h1>Sign Up</h1>
	<a href="login.jsp">Sign In</a>
		<form action="signUp" method="post">
			<table style="with: 100%">
				<tr>
					<td>Username</td>
					<td><input type="text" name="uname" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="pass" /></td>
				</tr>
				<tr>
					<td>Repeat Password</td>
					<td><input type="password" name="pass1" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td><select class="select-css" id="select-css" name="type">
							<option value="customers">Customer</option>
							<option value="owners">Owner</option>
					</select></td>
				</tr>

			</table>
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>