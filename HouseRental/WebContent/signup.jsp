<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
	<%@ include file="css/login.css"%>
</style>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up Form</title>
</head>
<body>
	<div class="wrapper fadeInDown">
 		<div id="formContent">
    		<a href="login.jsp" class="inactive underlineHover""> Sign In </a>
   			<h2 class="active">Sign Up</h2>
   			<br><br>
			<form action="<%=request.getContextPath()%>/signUp" method="post">
	     		 <input type="text" class="fadeIn second" name="uname" placeholder="Username">
	     		 <input type="password" class="fadeIn third" name="pass" placeholder="Password">
	     		 <input type="password" class="fadeIn second" name="pass1" placeholder="Repeat Password">
	     		 <input type="text"class="fadeIn third" name="email" placeholder="Email">
	     		 <select class="select-css" name="type">
					 <option value="customers">Customer</option>
					 <option value="owners">Owner</option>
				 </select>
				 <br><br>
	     	 	 <input type="submit" class="fadeIn fourth" value="Sign Up">
	    	</form>
  		</div>
	</div>
</body>
</html>
