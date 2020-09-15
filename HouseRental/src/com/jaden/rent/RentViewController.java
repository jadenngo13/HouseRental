package com.jaden.rent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
	private RentalDao rentalDAO;
	private DateTimeFormatter formatter;
	private LocalDateTime today;
	private Date dt;
	private LocalDate tdy, tmmr;

	public void init(ServletConfig config) throws ServletException {
		rentalDAO = new RentalDao();
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
		String firstAvailRentDate;
		List<String> rentedDates;
		String rentedDatesString;
		String allRentedDatesString;
		
		price = rentalDAO.getPrice(id);
		location = rentalDAO.getLocation(id);
		desc = rentalDAO.getDescription(id);
		rentalStartDate = null;
		imageFileUrl = rentalDAO.getImageFile(id);
		if (rentalStartDate == null) {
			rentalStartDate = formatter.format(today);
			rentalEndDate = tmmr.toString();
		}
		currDate = formatter.format(today);
		tmmrsDate = tmmr.toString();
		
		rentedDates = rentalDAO.getRentedDates(id);
		rentedDatesString = rentalDAO.getRentedDatesString(id);
		allRentedDatesString = rentalDAO.getAllRentedDatesString(id);
		firstAvailRentDate = rentalDAO.getFirstAvailDate(allRentedDatesString);
		
		session.setAttribute("rentalID", id);
		session.setAttribute("rentalPrice", price);
		session.setAttribute("rentalLocation", location);
		session.setAttribute("rentalDescription", desc);
		session.setAttribute("imageFileUrl", imageFileUrl);
		session.setAttribute("currentDate", currDate);
		session.setAttribute("tmmrsDate", tmmrsDate);
		session.setAttribute("firstAvailDate", firstAvailRentDate);
		session.setAttribute("rentedDates", rentedDates);
		session.setAttribute("rentedDatesString", rentedDatesString);
		session.setAttribute("allRentedDatesString", allRentedDatesString);
		
		
		response.sendRedirect("rentView.jsp");
	}
}
