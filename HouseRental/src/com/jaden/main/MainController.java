package com.jaden.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jaden.dao.CustomerDAO;
import com.jaden.dao.OwnerDAO;
import com.jaden.dao.RentalDao;
import com.jaden.dao.UserDAO;
import com.jaden.data.Rental;
import com.jaden.data.User;


@WebServlet("/")
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HttpSession session;
    private UserDAO userDAO;
    private RentalDao rentalDAO;
    private OwnerDAO ownerDAO;
    private User owner;

    public void init() {
        userDAO = new UserDAO();
        rentalDAO = new RentalDao();
        ownerDAO = new OwnerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	session = request.getSession();
        String action = request.getServletPath();
        owner = (User)session.getAttribute("user");

        try {
            switch (action) {
                case "/anew":
                    showNewFormUser(request, response);
                    break;
                case "/ainsert":
                    insertUser(request, response);
                    break;
                case "/adelete":
                    deleteUser(request, response);
                    break;
                case "/aedit":
                    showEditFormUser(request, response);
                    break;
                case "/aupdate":
                    updateUser(request, response);
                    break;
                case "/alist":
                	session.setAttribute("tab", 2);
                    listUser(request, response);
                    break;
                case "/ahome":
                	session.setAttribute("tab", 1);
                	showHomeAdmin(request, response);
                	break;
                case "/onew":
                    showNewFormRental(request, response);
                    break;
                case "/oinsert":
                    insertRental(request, response);
                    break;
                case "/odelete":
                    deleteRental(request, response);
                    break;
                case "/oedit":
                    showEditFormRental(request, response);
                    break;
                case "/oupdate":
                    updateRental(request, response);
                    break;
                case "/olist":
                	session.setAttribute("tab1", 2);
                    listRental(request, response);
                    break;
                case "/ohome":
                	session.setAttribute("tab1", 1);
                	showHomeOwner(request, response);
                	break;
                default:
                	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < User > listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminMain.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormUser(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String type = request.getParameter("type");
        User existingUser = userDAO.selectUser(id, type);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String bday = request.getParameter("bday");
        String email = request.getParameter("email");
        String userType = request.getParameter("type");
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        User newUser = new User(fname, lname, bday, email, null, userType, uname, pass);
        userDAO.insertUser(newUser);
        response.sendRedirect("alist");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String bday = request.getParameter("bday");
        String email = request.getParameter("email");
        String userType = request.getParameter("type");
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        User user = new User(id, fname, lname, bday, email, null, userType, uname, pass);
        userDAO.updateUser(user);
        response.sendRedirect("alist");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String type = request.getParameter("type");
        userDAO.deleteUser(id, type);
        response.sendRedirect("alist");
    }
    
    private void showHomeAdmin(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        response.sendRedirect("adminMain.jsp");
    }
    
    /***** Owner Main *****/
    private void listRental(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        refreshOwner(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ownerMain.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormRental(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("rental-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormRental(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Rental existingRental = rentalDAO.selectRental(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rental-form.jsp");
        request.setAttribute("rental", existingRental);
        dispatcher.forward(request, response);

    }

    private void insertRental(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int price = Integer.parseInt(request.getParameter("price"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        Rental newRental = new Rental(owner.getId(), price, location, description, image);
        rentalDAO.insertRental(owner, newRental);
        response.sendRedirect("olist");
    }

    private void updateRental(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	int rentalID = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        Rental rental = new Rental(rentalID, owner.getId(), price, location, description, image);
        rentalDAO.updateRental(rental);
        refreshOwner(request, response);
        listRental(request, response);
        session.setAttribute("tab1", 1);
    }

    private void deleteRental(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Rental r = rentalDAO.selectRental(id);
        rentalDAO.deleteRental(owner, r);
        refreshOwner(request, response);
        response.sendRedirect("olist");
    }
    
    private void showHomeOwner(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        response.sendRedirect("ownerMain.jsp");
    }
    
    private void refreshOwner(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	session.setAttribute("listRentals", ownerDAO.getRentals(owner.getId()));
		session.setAttribute("ownerRented", ownerDAO.getRented(owner.getId()));
    }
}