package com.jaden.signup;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaden.connection.DBConnection;
import com.jaden.dao.SignUpDao;

@WebServlet("/signUp")
public class SignUpController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (DBConnection.conn == null) {
			DBConnection.conn = DBConnection.getConnection();
		}
		
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String pass1 = request.getParameter("pass1");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		
		if (!pass.equals(pass1)) {
			System.out.println("Passwords do not match.");
			response.sendRedirect("login.jsp");
		}
		
		SignUpDao dao = new SignUpDao();
		dao.insertEntry(uname, pass, email, type);
		
		response.sendRedirect("login.jsp");
	}
}
