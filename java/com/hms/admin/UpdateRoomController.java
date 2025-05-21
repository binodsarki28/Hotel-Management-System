package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//update the menu after changing the value
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/updateRoom"})
public class UpdateRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// redirect to update menu page
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/room/updateRoom.jsp").forward(req, resp);
	}

	// gets the data from the update menu page and update the details using the menuService class
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
