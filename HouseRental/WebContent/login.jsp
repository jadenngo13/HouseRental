<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    
<html>
<style><%@include file="css/login.css"%></style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Java EE</title>
</head>
<body>
	<div class="wrapper fadeInDown">
	  <div id="formContent">
	    <!-- Tabs Titles -->
	    <h2 class="inactive underlineHover">Sign In </h2>
	    <h2 class="inactive underlineHover">Sign Up </h2>
	    <!-- <form action="login" method="post">
	    	<h2 class="inactive underlineHover">Sign In </h2>
	    	<input type="submit" value="Sign In">
	    </form>
	    
	    <form action="sign up" method="post">
	    	<h2 class="inactive underlineHover">Sign Up </h2>
	    	<input type="submit" value="Sign Up">
	    </form> -->
	    
	    <%-- <%
		    if((request.getParameter("signup") == null)?false:true){
		    	String url = "signup.jsp";
		        response.sendRedirect(url);
		    }
	    %> --%>
	    
	    <!-- Logo -->
	    <div id="logo" class="fadeIn first">
	      <img src="img/logo.png" width="30" height="85" id="icon" class="wrapper1" alt="Logo" />
	    </div>
	
	    <!-- Login Form -->
	    <form action="login" method="post">
	      <input type="text" id="login" class="fadeIn second" name="uname">
	      <input type="password" id="password" class="fadeIn third" name="pass">
	      <input type="submit" class="fadeIn fourth" value="Log In">
	    </form>
	  </div>
	</div>
</body>
</html>