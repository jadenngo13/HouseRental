package com.jaden.rent;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;

import com.jaden.dao.CustomerDAO;
import com.jaden.dao.RentalDao;
import com.jaden.data.User;

@WebServlet("/rent")
public class RentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RentalDao rentalDAO;
	private CustomerDAO custDAO;

	public void init() {
		rentalDAO = new RentalDao();
		custDAO = new CustomerDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int rentalID = (int)session.getAttribute("rentalID");
		User user = (User)session.getAttribute("user");
		int ownerID = rentalDAO.getOwnerID(rentalID);
		String custRentalsString = (String)session.getAttribute("customerRentalsString");
		String rentedDates = (String) session.getAttribute("rentedDatesString");
		String rentStart = request.getParameter("rentalStartDate");
		String rentEnd = request.getParameter("rentalEndDate");
		
		LocalDate s = LocalDate.parse(rentStart);
		LocalDate e = LocalDate.parse(rentEnd);
		if (e.isBefore(s) && !rentalDAO.checkDateInterfere(rentedDates, rentStart, rentEnd)) {
			System.out.println("Incorrect Date.");
		} else {
			rentalDAO.rentRental(rentalID,  (rentedDates != null) ? rentedDates : "", rentStart, rentEnd, user.getId(), custRentalsString, ownerID);
			
			// Refresh customer
			try {
				refreshCustomer(request, response, user);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			response.sendRedirect("customerMain.jsp");
		}
	}
	
	private void refreshCustomer(HttpServletRequest request, HttpServletResponse response, User user) throws SQLException {
		HttpSession session = request.getSession();
		session.setAttribute("customerRentals", custDAO.getCustomerRentals(user.getId()));
		session.setAttribute("customerRentalsString", custDAO.getRentedString(user.getId()));
		session.setAttribute("listRentals", rentalDAO.selectAllRentals());
	}
}
