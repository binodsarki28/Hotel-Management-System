//package com.hms.controller;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import com.hms.util.CookiesUtil;
//import com.hms.util.SessionUtil;
//
///**
// * Servlet implementation class LogoutController
// */
//@WebServlet(asyncSupported = true, urlPatterns = {"/logout"})
//public class LogoutController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		CookiesUtil.deleteCookie(response, "role");
//		SessionUtil.invalidateSession(request);
//		response.sendRedirect(request.getContextPath() + "/login");
//	}
//} 

package com.hms.controller;

import com.hms.util.CookiesUtil;
import com.hms.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


// for logging out the user
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // delete the cookies stored after the logout
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Invalidate session
        SessionUtil.removeAttribute(req, "loggedInUser");

        // Remove role cookie
        Cookie roleCookie = CookiesUtil.getCookie(req, "role");
        if (roleCookie != null) {
            roleCookie.setMaxAge(0); // Delete cookie
            roleCookie.setPath(req.getContextPath());
            resp.addCookie(roleCookie);
        }

        // Redirect to login page
        resp.sendRedirect(req.getContextPath() + "/login"); // redirect to login page after logout
    }
}