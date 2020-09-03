package com.jaden.login;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.connection.DBConnection;
import com.jaden.dao.LoginDao;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	Connection conn;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (DBConnection.conn == null) {
			DBConnection.conn = DBConnection.getConnection();
		}
		conn = DBConnection.conn;
		
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		int userID;
		String userName;
		String userType;
		
		LoginDao dao = new LoginDao();
		if (dao.check(uname, pass)) {
			userType = dao.getType(uname, pass);
			userID = dao.getID(uname, pass);
			userName = dao.getName(userID, userType);
			
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			session.setAttribute("password", pass);
			session.setAttribute("name", userName);
			session.setAttribute("type", userType);
			
			switch (userType) {
			case "admins":
				response.sendRedirect("adminMain.jsp");
				break;
			case "owners":
				response.sendRedirect("ownerMain.jsp");
				break;
			case "customers":
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
