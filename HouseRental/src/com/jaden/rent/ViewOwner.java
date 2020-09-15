package com.jaden.rent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.dao.RentalDao;
import com.jaden.data.User;

@WebServlet("/viewOwner")
public class ViewOwner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User owner;
	private RentalDao rentalDAO;
	
	public void init() {
		rentalDAO = new RentalDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int rentalID = Integer.parseInt(request.getParameter("rentalID"));
		owner = rentalDAO.getOwner(rentalID);
		session.setAttribute("owner", owner);
		
		response.sendRedirect("viewOwner.jsp");
	}

}
