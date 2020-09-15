<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
	<%@ include file="css/login.css"%>
</style>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
</head>
<body>
	<div class="wrapper fadeInDown">
 		<div id="formContent">
    		<h2 class="active"> Sign In </h2>
   			<a href="signUp.jsp" class="inactive underlineHover">Sign Up</a>
   			<br><br>
			<form action="<%=request.getContextPath()%>/login" method="post">
	     		 <input type="text" class="fadeIn second" name="uname" placeholder="username">
	     		 <input type="password" class="fadeIn third" name="pass" placeholder="password">
	     		 <br><br>
	     	 	 <input type="submit" class="fadeIn fourth" value="Log In">
	    	</form>
  		</div>
	</div>
</body>
</html>
