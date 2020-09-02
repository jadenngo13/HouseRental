package com.jaden.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.login.dao.LoginDao;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String userType;
		
		LoginDao dao = new LoginDao();
		dao.getConnection();
		if (dao.check(uname, pass)) {
			userType = dao.getType(uname, pass);
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			session.setAttribute("password", pass);
			session.setAttribute("type", userType);
			
			switch (userType) {
			case "admin":
				response.sendRedirect("adminMain.jsp");
				break;
			case "owner":
				response.sendRedirect("ownerMain.jsp");
				break;
			case "customer":
				response.sendRedirect("customerMain.jsp");
				break;
			default:
				System.out.println("Not a valid user type for login.");
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
