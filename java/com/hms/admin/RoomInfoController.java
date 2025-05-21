package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.hms.model.RoomModel;
import com.hms.service.RoomService;

//redirect to room info page for see room details
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/roomInfo" })
public class RoomInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// this method redirect to the room info page with the list of rooms for fetching room details
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoomService roomService = new RoomService();
		List<RoomModel> roomList = roomService.getAllRoomsInfo();

		req.setAttribute("roomList", roomList);
		req.getRequestDispatcher("/WEB-INF/pages/admin/roomInfo.jsp").forward(req, resp);
	}

}
