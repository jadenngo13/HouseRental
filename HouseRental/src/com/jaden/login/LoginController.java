package com.jaden.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.connection.DBConnection;
import com.jaden.dao.LoginDao;
import com.jaden.dao.RentalDao;
import com.jaden.dao.UserDAO;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDAO;
	private UserDAO userDAO;
	private RentalDao rentalDAO;
	
	public void init() {
		if (DBConnection.conn == null) {
			DBConnection.conn = DBConnection.getConnection();
		}
		loginDAO = new LoginDao();
		rentalDAO = new RentalDao();
		userDAO = new UserDAO();
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
					int[] data = userDAO.getNumUsers();
					session.setAttribute("numUsers", data[0]);
					session.setAttribute("numOwners", data[1]);
					session.setAttribute("numCustomers", data[2]);
					session.setAttribute("numRentals", data[3]);
					session.setAttribute("numRented", data[4]);
					session.setAttribute("tab", 1);
					response.sendRedirect("adminMain.jsp");
					break;
				case "owners":
					session.setAttribute("tab", 0);
					response.sendRedirect("ownerMain.jsp");
					break;
				case "customers":
					List <Integer> rented = new ArrayList<>();
					rented.add(1);
					session.setAttribute("rentedRentals", rentalDAO.selectAllRented(rented));
					session.setAttribute("listRentals", rentalDAO.selectAllRentals());
					session.setAttribute("tab", 0);
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
