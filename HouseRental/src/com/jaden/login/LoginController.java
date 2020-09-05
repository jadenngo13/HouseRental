package com.jaden.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.connection.DBConnection;
import com.jaden.dao.LoginDao;
import com.jaden.dao.RentalDao;
import com.jaden.data.Rental;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDAO;
	private RentalDao rentalDAO;
	
	public void init() {
		if (DBConnection.conn == null) {
			DBConnection.conn = DBConnection.getConnection();
		}
		loginDAO = new LoginDao();
		rentalDAO = new RentalDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		int userID;
		String firstName;
		String lastName;
		String userType;
		String email;
		String bday;
		
		try {
			if (loginDAO.check(uname, pass)) {
				userType = loginDAO.getType(uname, pass);
				userID = loginDAO.getID(uname, pass);
				firstName = loginDAO.getFirstName(userID, userType);
				lastName = loginDAO.getLastName(userID, userType);
				email = loginDAO.getEmail(userID, userType);
				bday = loginDAO.getBirthday(userID, userType);
				
				
				HttpSession session = request.getSession();
				session.setAttribute("id", userID);
				session.setAttribute("username", uname);
				session.setAttribute("password", pass);
				session.setAttribute("fname", firstName);
				session.setAttribute("lname", lastName);
				session.setAttribute("type", userType);
				session.setAttribute("type1", userType.substring(0, userType.length()-1));
				session.setAttribute("email", email);
				session.setAttribute("birthday", bday);
				
				switch (userType) {
				case "admins":
					response.sendRedirect("adminMain.jsp");
					break;
				case "owners":
					response.sendRedirect("ownerMain.jsp");
					break;
				case "customers":
					session.setAttribute("listRentals", rentalDAO.selectAllRentals());
					response.sendRedirect("customerMain.jsp");
					break;
				default:
					System.out.println("Not a valid user type for login.");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
