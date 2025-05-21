package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.hms.model.UserModel;
import com.hms.service.RegisterService;
import com.hms.util.ImageUtil;
import com.hms.util.PasswordUtil;
import com.hms.util.ValidationUtil;

// for registering the user to the system
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final RegisterService registerService = new RegisterService();
	private final ImageUtil imageUtil = new ImageUtil();

	// redirect to the register page
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/main/register.jsp").forward(req, resp);
	}

	// this method takes the details entered by user from client side and store it to the database through the registerServie class
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String validationMessage = validateRegistrationForm(req); // check for validation error 
			if (validationMessage != null) {  // if error 
				handleError(req, resp, validationMessage); // send suitable error message
				return;
			}
			UserModel userModel = extractUserModel(req, resp);
			Boolean isAdded = registerService.addUser(userModel);

			if (isAdded == null) {
				handleError(req, resp, "Our server is under maintenance. Please try again later!");
			} else if (isAdded) {
				if (uploadImage(req)) {
					handleSuccess(req, resp, "User Added Successfully", "/WEB-INF/pages/main/login.jsp");
				} else {
					handleError(req, resp, "Could not upload the image. Please try again later!");
				}
			} else {
				handleError(req, resp, "Could not add the user. Please try again later!");
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}

	// takes all user inputed values
	private String validateRegistrationForm(HttpServletRequest req) {
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("retypePassword");
		

		// checks for the validation
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

	// gets values from the user
	private UserModel extractUserModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String gender = req.getParameter("gender");
		String password = req.getParameter("password");
		String role = req.getParameter("role");

		password = PasswordUtil.encrypt(email, password); // encrypt the password gets from user for security 
		
		Part image = req.getPart("profilePhoto"); // get picture 
		String profilePhoto = imageUtil.getImageNameFromPart(image);

		return new UserModel(fullName, email, phoneNumber, gender, password, profilePhoto, role);
	}
	
	private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("profilePhoto");
		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "uploads");
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
