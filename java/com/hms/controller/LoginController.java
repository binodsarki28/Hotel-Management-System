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

// for logging the user to the system
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ValidationUtil validationUtil = new ValidationUtil();
    private final LoginService loginService = new LoginService();

    
    // redirect to the login page
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/main/login.jsp").forward(req, resp);
    }

    // this method handle the user details for logging the user securely
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
    	// takes email and password for login
    	String email = req.getParameter("email");
        String password = req.getParameter("password");

       
        if (!validationUtil.isNullOrEmpty(email) && !validationUtil.isNullOrEmpty(password)) {

            UserModel userModel = new UserModel(email, password);
            Boolean loginStatus = loginService.loginUser(userModel);

            if (loginStatus != null && loginStatus) {

                UserModel fullUser = loginService.getUserDetailsByEmail(email);

                if (fullUser != null) {
                    SessionUtil.setAttribute(req, "loggedInUser", fullUser);

                    if (email.equals("admin@gmail.com")) { // check for amdin login 
                        CookiesUtil.addCookie(resp, "role", "admin", 5 * 60); //for 5 hours
                        resp.sendRedirect(req.getContextPath() + "/dashboard");// redirect to dashboard page
                    } else { //else user gets login 
                        CookiesUtil.addCookie(resp, "role", "user", 5 * 60);
                        resp.sendRedirect(req.getContextPath() + "/home"); // redirect to home page
                    }
                } else {
                    req.setAttribute("error", "Could not retrieve user details. Please try again.");
                    req.getRequestDispatcher("/WEB-INF/pages/main/login.jsp").forward(req, resp);
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

    // handle the message after login gets failed
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
