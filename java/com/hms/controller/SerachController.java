package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hms.service.AddRoomService;
import com.hms.model.RoomModel;

// for searching the room 
@WebServlet(asyncSupported = true, urlPatterns = { "/search" })
public class SerachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final AddRoomService roomService = new AddRoomService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerachController() {
        super();
        // TODO Auto-generated constructor stub
    }

	// run after the user click on search button and fetch the room after filtering 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");

        List<RoomModel> allRooms = roomService.getAllRooms(); 
        List<RoomModel> filteredRooms = new ArrayList<>();

        for (RoomModel room : allRooms) {
            boolean matchCategory = (category == null || category.isEmpty()) || category.equalsIgnoreCase(room.getRoomType()); // store the room according to the user room type
            boolean isAvailable = room.getStatus() != null && room.getStatus().equalsIgnoreCase("Available"); // store the room which are available

            // Only add to filtered list if status is "Available"
            if (matchCategory && isAvailable) {
                filteredRooms.add(room);
            }
        }

        // Pass the list to JSP
        request.setAttribute("roomList", filteredRooms);

        // Also pass the search parameters back to JSP for showing current filter
        request.setAttribute("category", category);
        request.setAttribute("checkin", checkin);
        request.setAttribute("checkout", checkout);

        request.getRequestDispatcher("/WEB-INF/pages/room/room.jsp").forward(request, response);
    }
}
