package com.jaden.rent;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.dao.RentalDao;

@WebServlet("/rent")
public class RentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RentalDao rentalDao;

	public void init(ServletConfig config) throws ServletException {
		rentalDao = new RentalDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("selectedRentalID"));
		int price;
		String location;
		String desc;
		String imageFileName;
		
		System.out.println("here with name: " + id);
		
		price = rentalDao.getPrice(id);
		location = rentalDao.getLocation(id);
		desc = rentalDao.getDescription(id);
		imageFileName = rentalDao.getImageFile(id);
		
		System.out.println("rental info: " + price + " " + location + " " + desc + " " + imageFileName);
		
		response.sendRedirect("rentView.jsp");
		session.setAttribute("rentalID", id);
		
		
	}
}
