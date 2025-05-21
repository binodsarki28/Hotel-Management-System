package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.hms.model.BookingModel;
import com.hms.model.UserModel;
import com.hms.service.UserService;

import java.io.IOException;
import java.util.List;

// for handling the profile page
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // redirect to the profile page if user is logged in 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        req.setAttribute("loggedInUser", loggedInUser);

        // Forward to profile.jsp
        req.getRequestDispatcher("/WEB-INF/pages/profile/profile.jsp").forward(req, resp);
    }
}
