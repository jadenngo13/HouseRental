package com.jaden.save;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.dao.ProfileDAO;
import com.jaden.data.User;

@WebServlet("/saveUser")
public class SaveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileDAO profileDAO;
       
	public void init() {
		profileDAO = new ProfileDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String username = request.getParameter("uname");
		String email = request.getParameter("email");
		String bday = request.getParameter("birthday");
		
		try {
			profileDAO.saveUser(user.getId(), firstName, lastName, username, email, bday, user.getUserType());
			user = new User(user.getId(), firstName, lastName, bday, email, user.getRentals(), user.getUserType(), user.getUsername(), user.getPassword());
			session.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("viewProfile.jsp");
	}
}
