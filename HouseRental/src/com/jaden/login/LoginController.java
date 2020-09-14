package com.jaden.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.connection.DBConnection;
import com.jaden.dao.CustomerDAO;
import com.jaden.dao.LoginDao;
import com.jaden.dao.OwnerDAO;
import com.jaden.dao.RentalDao;
import com.jaden.dao.UserDAO;
import com.jaden.data.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDAO;
	private UserDAO userDAO;
	private OwnerDAO ownerDAO;
	private CustomerDAO custDAO;
	private RentalDao rentalDAO;
	
	public void init() {
		if (DBConnection.conn == null) {
			DBConnection.conn = DBConnection.getConnection();
		}
		loginDAO = new LoginDao();
		rentalDAO = new RentalDao();
		userDAO = new UserDAO();
		custDAO = new CustomerDAO();
		ownerDAO = new OwnerDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		User user = new User(null, null, null, null, null, null, null, null);

		try {
			if (loginDAO.check(uname, pass)) {
				user.setId(loginDAO.getID(uname, pass));
				user.setUserType(loginDAO.getType(uname, pass));
				user.setFirstName(loginDAO.getFirstName(user.getId(), user.getUserType()));
				user.setLastName(loginDAO.getLastName(user.getId(), user.getUserType()));
				user.setBday(loginDAO.getBirthday(user.getId(), user.getUserType()));
				user.setEmail(loginDAO.getEmail(user.getId(), user.getUserType()));
				user.setUsername(loginDAO.getUsername(user.getId(), user.getUserType()));
				user.setPassword(loginDAO.getPassword(user.getId(), user.getUserType()));
				user.setRentals(custDAO.getRentedString(user.getId()));
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("type1", user.getUserType().substring(0, user.getUserType().length()-1));
				
				switch (user.getUserType()) {
				case "admins":
					int[] data = userDAO.getNumUsers();
					session.setAttribute("numUsers", data[0]);
					session.setAttribute("numOwners", data[1]);
					session.setAttribute("numCustomers", data[2]);
					session.setAttribute("numRentals", data[3]);
					session.setAttribute("numRented", data[4]);
					session.setAttribute("tab", 1);
					session.setAttribute("type2", 1);
					response.sendRedirect("adminMain.jsp");
					break;
				case "owners":
					session.setAttribute("listRentals", ownerDAO.getRentals(user.getId()));
					session.setAttribute("ownerRented", ownerDAO.getRented(user.getId()));
					session.setAttribute("tab", 0);
					session.setAttribute("type2", 2);
					response.sendRedirect("ownerMain.jsp");
					break;
				case "customers":
					custDAO.updateRentals(user);
					session.setAttribute("customerRentals", custDAO.getCustomerRentals(user.getId()));
					session.setAttribute("customerRentalsString", user.getRentals());
					session.setAttribute("listRentals", rentalDAO.selectAllRentals());
					session.setAttribute("tab", 0);
					session.setAttribute("type2", 3);
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
