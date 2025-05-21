package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.hms.service.AddRoomService;
import com.hms.model.RoomModel;

// for accessing room page
@WebServlet(asyncSupported = true, urlPatterns = { "/room" })
public class RoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final AddRoomService roomService = new AddRoomService();

    // redirecting to the room page after fetching all room from the database
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoomModel> allRooms = roomService.getAllRooms();
        req.setAttribute("roomList", allRooms);  // Set rooms here for initial load without filtering/searching 
        req.getRequestDispatcher("/WEB-INF/pages/room/room.jsp").forward(req, resp);
    }
}
