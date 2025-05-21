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

/**
 * Servlet implementation class SerachController
 */
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String checkin = request.getParameter("checkin");   // You have these inputs on the form
        String checkout = request.getParameter("checkout");

        List<RoomModel> allRooms = roomService.getAllRooms();
        List<RoomModel> filteredRooms = new ArrayList<>();

        for (RoomModel room : allRooms) {
            boolean matchCategory = (category == null || category.isEmpty()) || category.equalsIgnoreCase(room.getRoomType());
            // You might want to consider "Available" status here or not, based on your logic:
            // boolean isAvailable = "Available".equalsIgnoreCase(room.getStatus());

            // If you want to show only available rooms on initial load and when filtering:
            if (matchCategory /* && isAvailable */) {
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



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
