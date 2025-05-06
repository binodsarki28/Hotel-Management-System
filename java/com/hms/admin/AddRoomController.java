package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.hms.model.RoomModel;
import com.hms.service.AddRoomService;
import com.hms.util.ImageUtil;

/**
 * Servlet implementation class RoomController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/addRoom"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final AddRoomService addRoomService = new AddRoomService();
	private final ImageUtil imageUtil = new ImageUtil();
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/room/addRoom.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RoomModel roomModel = extractRoomModel(req, resp);
			Boolean isAdded = addRoomService.addRoom(roomModel);

			if (isAdded == null) {
				handleError(req, resp, "Our server is under maintenance. Please try again later!");
			} else if (isAdded) {
				if (uploadImage(req)) {
					handleSuccess(req, resp, "Room Added Successfully", "/WEB-INF/pages/login.jsp");
				} else {
					handleError(req, resp, "Could not upload the image. Please try again later!");
				}
			} else {
				handleError(req, resp, "Could not add the room. Please try again later!");
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}


	private RoomModel extractRoomModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int roomNo  = Integer.parseInt(req.getParameter("roomNo")); 
		String roomType = req.getParameter("roomType");
		String roomDescription = req.getParameter("roomDescription");
		float pricePerDay = Float.parseFloat(req.getParameter("pricePerDay"));
		String status = req.getParameter("status");
		
		Part image = req.getPart("roomPhoto");
		String roomPhoto = imageUtil.getImageNameFromPart(image);

		return new RoomModel(roomNo, roomType, roomDescription, pricePerDay, status,
				roomPhoto);
	}

	private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("roomPhoto");
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
		req.getRequestDispatcher("/WEB-INF/pages/room/addRoom.jsp").forward(req, resp);
	}

}
