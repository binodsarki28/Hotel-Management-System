package com.hms.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hms.service.LoginService;
import com.hms.util.ValidationUtil;
import com.hms.model.UserModel;
import com.hms.util.CookiesUtil;
import com.hms.util.SessionUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ValidationUtil validationUtil;
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        this.validationUtil = new ValidationUtil();
        this.loginService = new LoginService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/main/login.jsp").forward(req, resp);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!validationUtil.isNullOrEmpty("email") && !validationUtil.isNullOrEmpty("password")) {

            UserModel userModel = new UserModel(email, password);
            Boolean loginStatus = loginService.loginUser(userModel);

            if (loginStatus != null && loginStatus) {
            	
            	UserModel fullUser = loginService.getUserDetailsByEmail(email);

                // Set the full user details in the session
                SessionUtil.setAttribute(req, "loggedInUser", fullUser);

                // Check if the user is an admin or regular user and redirect accordingly
                if (email.equals("admin")) {
                    CookiesUtil.addCookie(resp, "role", "admin", 5 * 30); // Admin role cookie
                    resp.sendRedirect(req.getContextPath() + "/dashboard"); // Redirect to dashboard for admin
                } else {
                    CookiesUtil.addCookie(resp, "role", "user", 5 * 30); // Regular user role cookie
                    resp.sendRedirect(req.getContextPath() + "/profile"); // Redirect to profile page for regular user
                }
            } else {
                handleLoginFailure(req, resp, loginStatus);
            }
        } else {
            req.setAttribute("error", "Please fill all the fields!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/main/login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    /**
     * Handles login failures by setting attributes and forwarding to the login page.
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "Invalid email or password. Please try again!";
        }
        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher("/WEB-INF/pages/main/login.jsp").forward(req, resp);
    }
}
