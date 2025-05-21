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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/main/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!validationUtil.isNullOrEmpty(email) && !validationUtil.isNullOrEmpty(password)) {

            UserModel userModel = new UserModel(email, password);
            Boolean loginStatus = loginService.loginUser(userModel);

            if (loginStatus != null && loginStatus) {

                UserModel fullUser = loginService.getUserDetailsByEmail(email);

                if (fullUser != null) {
                    SessionUtil.setAttribute(req, "loggedInUser", fullUser);

                    if (email.equals("admin@gmail.com")) {
                        CookiesUtil.addCookie(resp, "role", "admin", 5 * 60 * 24);
                        resp.sendRedirect(req.getContextPath() + "/dashboard");
                    } else {
                        CookiesUtil.addCookie(resp, "role", "user", 5 * 60 * 24);
                        resp.sendRedirect(req.getContextPath() + "/home");
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
