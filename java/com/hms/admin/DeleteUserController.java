package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hms.service.UserService;

////This controller delete the particular user
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/deleteUser" })
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService = new UserService();

	// this method delete the room
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String roomIdParam = req.getParameter("userId");
		if (roomIdParam != null) {
			int userId = Integer.parseInt(roomIdParam);
			boolean deleted = userService.deleteUserById(userId);
			if (deleted) {
				resp.sendRedirect(req.getContextPath() + "/dashboard/userInfo?deleted=true");
			} else {
				resp.sendRedirect(req.getContextPath() + "/dashboard/userInfo?error=deleteFailed");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/dashboard/userInfo?error=invalidId");
		}
	}

}
