package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.hms.model.UserModel;
import com.hms.service.RegisterService;
import com.hms.util.PasswordUtil;
import com.hms.util.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final RegisterService registerService = new RegisterService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/main/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String validationMessage = validateRegistrationForm(req);
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
			}

			UserModel userModel = extractUserModel(req, resp);
			Boolean isAdded = registerService.addUser(userModel);

			if (isAdded == null) {
				handleError(req, resp, "Our server is under maintenance. Please try again later!");
			} else if (isAdded) {
				handleSuccess(req, resp, "Your account is successfully created!", "/WEB-INF/pages/main/login.jsp");
			} else {
				handleError(req, resp, "Email or phone number already exists. Please use a different one.");
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			e.printStackTrace();
		}
	}

	private String validateRegistrationForm(HttpServletRequest req) {
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("retypePassword");

		if (!ValidationUtil.isValidFullName(fullName)) {
			return "Full name must be at least 6 characters long and contain only letters and spaces.";
		}
		if (!ValidationUtil.isValidEmail(email)) {
			return "Email must be a valid Gmail address (e.g., user@gmail.com).";
		}
		if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) {
			return "Phone number must start with 97 or 98 and be exactly 10 digits.";
		}
		if (!ValidationUtil.isValidPassword(password)) {
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		}
		if (!password.equals(retypePassword)) {
			return "Passwords do not match.";
		}
		return null;
	}

	private UserModel extractUserModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String gender = req.getParameter("gender");
		String password = req.getParameter("password");
		String role = req.getParameter("role");

		password = PasswordUtil.encrypt(email, password);

		return new UserModel(fullName, email, phoneNumber, gender, password, role);
	}

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.getRequestDispatcher("/WEB-INF/pages/main/register.jsp").forward(req, resp);
	}
}
