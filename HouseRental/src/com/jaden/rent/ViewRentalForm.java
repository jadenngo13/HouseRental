package com.jaden.rent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.dao.RentalFormDAO;
import com.jaden.data.RentalForm;

/**
 * Servlet implementation class ViewRentalForm
 */
@WebServlet("/viewForm")
public class ViewRentalForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RentalFormDAO rentalFormDAO;
	private int rentalFormID;

	public void init() {
		rentalFormDAO = new RentalFormDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int rentalFormID = Integer.parseInt(request.getParameter("selectedRentalForm"));
		RentalForm form = rentalFormDAO.getRentalForm(rentalFormID);
		session.setAttribute("customerName", rentalFormDAO.getCustomerFirstName(rentalFormID) + rentalFormDAO.getCustomerLastName(rentalFormID));
		session.setAttribute("price", form.getPrice());
		session.setAttribute("rentalFormStartDate", form.getStartDate());
		session.setAttribute("rentalFormEndDate", form.getEndDate());
	
		response.sendRedirect("");
	}

}




