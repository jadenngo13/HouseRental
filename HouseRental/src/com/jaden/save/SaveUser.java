package com.jaden.save;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.dao.ProfileDAO;

@WebServlet("/saveUser")
public class SaveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileDAO profileDAO;
       
	public void init() {
		profileDAO = new ProfileDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String username = request.getParameter("uname");
		String email = request.getParameter("email");
		String bday = request.getParameter("birthday");
		String type = (String) session.getAttribute("type");
		
		try {
			profileDAO.saveUser((int) session.getAttribute("id"), firstName, lastName, username, email, bday, type);
			session.setAttribute("username", username);
			session.setAttribute("fname", firstName);
			session.setAttribute("lname", lastName);
			session.setAttribute("email", email);
			session.setAttribute("birthday", bday);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("viewProfile.jsp");
	}

}
