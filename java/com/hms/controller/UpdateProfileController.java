package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.hms.model.UserModel;
import com.hms.service.UpdateProfileService;

@WebServlet(asyncSupported = true, urlPatterns = { "/updateProfile"})
public class UpdateProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UpdateProfileService updateProfileService = new UpdateProfileService();
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/profile/updateProfile.jsp").forward(req, resp);
	}


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Get existing user from session
        UserModel existingUser = (UserModel) session.getAttribute("loggedInUser");

        // Safely get userId parameter or fallback to session userId
        String userIdParam = req.getParameter("userId");
        int userId;
        if (userIdParam != null && !userIdParam.isEmpty()) {
            try {
                userId = Integer.parseInt(userIdParam);
            } catch (NumberFormatException e) {
                userId = existingUser.getUserId(); // fallback
            }
        } else {
            userId = existingUser.getUserId();
        }

        String fullName = req.getParameter("fullName");
        if (fullName == null || fullName.trim().isEmpty()) {
            req.setAttribute("error", "Full name is required");
            req.getRequestDispatcher("/WEB-INF/pages/profile/updateProfile.jsp").forward(req, resp);
            return;
        }
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String gender = req.getParameter("gender");
        String password = req.getParameter("newPassword");
        String profilePhoto = req.getParameter("profilePhoto");

        String role = existingUser.getRole();

        UserModel userModel = new UserModel(userId, fullName, email, phoneNumber, gender, password, profilePhoto, role);

        boolean success = updateProfileService.updateUserProfile(userModel);

        if (success) {
            session.setAttribute("loggedInUser", userModel);
            req.setAttribute("status", "updated");
        } else {
            req.setAttribute("status", "failed");
        }

        req.getRequestDispatcher("/WEB-INF/pages/profile/updateProfile.jsp").forward(req, resp);
    }

}
