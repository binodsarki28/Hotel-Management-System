package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hms.service.RoomService;

////This controller delete the particular room
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/deleteRoom" })
public class DeleteRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final RoomService roomService = new RoomService();

	// this method delete the room
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String roomIdParam = req.getParameter("roomId"); // get room id
		if (roomIdParam != null) {
			int roomId = Integer.parseInt(roomIdParam);
			boolean deleted = roomService.deleteRoomById(roomId);
			if (deleted) {
				resp.sendRedirect(req.getContextPath() + "/dashboard/roomInfo?deleted=true");
			} else {
				resp.sendRedirect(req.getContextPath() + "/dashboard/roomInfo?error=deleteFailed");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/dashboard/roomInfo?error=invalidId");
		}
	}
}
