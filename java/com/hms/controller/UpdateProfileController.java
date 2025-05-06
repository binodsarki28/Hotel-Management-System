package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.hms.model.UserModel;

/**
 * Servlet implementation class UpdateProfileController
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/updateProfile"})
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateProfileController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false means don't create a new session if it doesn't exist

        if (session == null || session.getAttribute("loggedInUser") == null) {
            // If the user is not logged in, redirect to the login page
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // User is logged in, retrieve the user object from the session
            UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");

            // Set the logged-in user's data in the request for use in the profile page
            req.setAttribute("loggedInUser", loggedInUser);

            // Forward to JSP
            req.getRequestDispatcher("/WEB-INF/pages/profile/updateProfile.jsp").forward(req, resp);
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
