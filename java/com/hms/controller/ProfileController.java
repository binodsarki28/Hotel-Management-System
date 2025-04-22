package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hms.model.UserModel;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the session
        HttpSession session = req.getSession(false); // false means don't create a new session if it doesn't exist

        // Check if the user is logged in (i.e., if the loggedInUser exists in the session)
        if (session == null || session.getAttribute("loggedInUser") == null) {
            // If the user is not logged in, redirect to the login page
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // User is logged in, retrieve the user object from the session
            UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");

            // Set the logged-in user's data in the request for use in the profile page
            req.setAttribute("loggedInUser", loggedInUser);

            // Forward the request to the profile page
            req.getRequestDispatcher("/WEB-INF/pages/profile/profile.jsp").forward(req, resp);
        }
    }
}
