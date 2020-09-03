<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<style>
<%@ include file="css/login.css"%>
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Java EE</title>
</head>
<body>

	<%-- <%
		    if((request.getParameter("signup") == null)?false:true){
		    	String url = "signup.jsp";
		        response.sendRedirect(url);
		    }
	    %> --%>

	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Sign In</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"><label for="tab-2"
				class="tab">Sign Up</label>
			<div class="login-form">
				<div class="sign-in-htm">
					<form action="login" method="post">
						<div class="group">
							<label for="user" class="label">Username</label> <input id="user"
								type="text" class="input" name="uname">
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input id="pass"
								type="password" class="input" data-type="password" name="pass">
						</div>
						<div class="group">
							<input id="check" type="checkbox" class="check" checked>
							<label for="check"><span class="icon"></span> Keep me
								Signed in</label>
						</div>
						<div class="group">
							<input type="submit" class="button" value="Log In">
						</div>
					</form>
				</div>
				<div class="sign-up-htm">
					<form action="signUp" method="post">
						<div class="group">
							<label for="user" class="label">Username</label> <input id="user"
								type="text" class="input" name="uname">
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input id="pass"
								type="password" class="input" name="pass">
						</div>
						<div class="group">
							<label for="pass" class="label">Repeat Password</label> <input
								id="pass" type="password" class="input" name="pass1">
						</div>
						<div class="group">
							<label for="pass" class="label">Email Address</label> <input
								id="pass" type="text" class="input" name="email">
						</div>
						<div class="group">
							<select class="select-css" id="select-css">
								<option value="customers">Customer</option>
								<option value="owners">Owner</option>
							</select>
						</div>
						<div class="group">
							<input type="submit" class="button" value="Sign Up">
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<label for="tab-1">Already Member?</label>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>