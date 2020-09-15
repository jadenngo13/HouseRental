package com.jaden.rent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.dao.CustomerDAO;
import com.jaden.dao.RentalFormDAO;
import com.jaden.data.RentalForm;
import com.jaden.data.User;

/**
 * Servlet implementation class ViewRentalForm
 */
@WebServlet("/viewForm")
public class ViewRentalForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RentalFormDAO rentalFormDAO;
	private CustomerDAO customerDAO;

	public void init() {
		rentalFormDAO = new RentalFormDAO();
		customerDAO = new CustomerDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int rentalFormID = Integer.parseInt(request.getParameter("selectedRentalForm"));
		RentalForm form = rentalFormDAO.getRentalForm(rentalFormID);
		User customer = customerDAO.getCustomer(form.getCustomerID());
		session.setAttribute("customer", customer);
		session.setAttribute("rentalForm", form);
	
		response.sendRedirect("viewRentalForm.jsp");
	}

}




