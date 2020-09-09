package com.jaden.rent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;

import com.jaden.dao.RentalDao;

@WebServlet("/rent")
public class RentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RentalDao rentalDao;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		rentalDao = new RentalDao();
		
		int rentalID = (int)session.getAttribute("rentalID");
		int customerID = (int)session.getAttribute("id");
		String rentStart = request.getParameter("rentalStartDate");
		String rentEnd = request.getParameter("rentalEndDate");
		
		System.out.println(rentalID + " " + customerID + " " + rentStart + " " + rentEnd);
		
		rentalDao.rentRental(rentalID, rentStart, rentEnd, customerID);
	}
}
