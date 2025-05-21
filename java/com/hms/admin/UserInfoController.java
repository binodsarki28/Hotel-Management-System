package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.hms.model.UserModel;
import com.hms.service.UserService;


//redirect to user info page for see user details
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/userInfo"})
public class UserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// this method redirect to the user info page with the list of users for fetching user details 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService userService = new UserService();
		List<UserModel> userList = userService.getAllUserInfo();

		req.setAttribute("userList", userList);
		req.getRequestDispatcher("/WEB-INF/pages/admin/userInfo.jsp").forward(req, resp);
	}
	
	
}
