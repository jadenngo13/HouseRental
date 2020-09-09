package com.jaden.rent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;

import com.jaden.dao.RentalDao;

@WebServlet("/rentView")
public class RentViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RentalDao rentalDao;
	private DateTimeFormatter formatter;
	private LocalDateTime today;
	private Date dt;
	private LocalDate tdy, tmmr, twoDays;

	public void init(ServletConfig config) throws ServletException {
		rentalDao = new RentalDao();
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		today = LocalDateTime.now();  
		dt = new Date();
		tdy = new LocalDate(dt);
		tmmr = tdy.plusDays(1);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("selectedRentalID"));
		int price;
		String location;
		String desc;
		String rentalStartDate;
		String rentalEndDate;
		String imageFileUrl;
		String currDate;
		String tmmrsDate;
		String availRentDate;
		
		price = rentalDao.getPrice(id);
		location = rentalDao.getLocation(id);
		desc = rentalDao.getDescription(id);
		rentalStartDate = rentalDao.getRentStartDate(id);
		rentalEndDate = rentalDao.getRentEndDate(id);
		imageFileUrl = rentalDao.getImageFile(id);
		if (rentalStartDate == null) {
			rentalStartDate = formatter.format(today);
			rentalEndDate = tmmr.toString();
		}
		currDate = formatter.format(today);
		tmmrsDate = tmmr.toString();
		

		
		System.out.println("rental info: " + price + " " + location + " " + desc + " " + rentalStartDate + " " + rentalEndDate);
		
		session.setAttribute("rentalID", id);
		session.setAttribute("rentalPrice", price);
		session.setAttribute("rentalLocation", location);
		session.setAttribute("rentalDescription", desc);
		session.setAttribute("rentalStartDate", rentalStartDate);
		session.setAttribute("rentalEndDate", rentalEndDate);
		session.setAttribute("imageFileUrl", imageFileUrl);
		session.setAttribute("currentDate", currDate);
		session.setAttribute("tmmrsDate", tmmrsDate);
		
		
		response.sendRedirect("rentView.jsp");
	}
}
