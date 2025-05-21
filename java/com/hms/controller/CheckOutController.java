package com.hms.controller;

import com.hms.model.BookingModel;
import com.hms.service.RoomService;
import com.hms.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// for checking out after booking
@WebServlet(asyncSupported = true, urlPatterns = { "/checkout"})
public class CheckOutController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private final UserService userService = new UserService();
    private final RoomService roomService = new RoomService();

    // change the value after check out
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));

            // Fetch the booking to get roomId
            BookingModel booking = userService.getBookingById(bookingId);

            if (booking == null) {
                request.setAttribute("error", "Booking not found.");
                request.getRequestDispatcher("/bookingHistory").forward(request, response);
                return;
            }

            // Update booking status to "Checked Out"
            boolean bookingUpdated = userService.updateBookingStatus(bookingId, "Checked Out"); // update the booking to the check out 

            // Update room status to "Available"
            boolean roomUpdated = roomService.updateRoomStatus(booking.getRoomId(), "Available"); // update the room status to Available

            if (bookingUpdated && roomUpdated) {
                request.setAttribute("message", "Checkout successful. Thank you!");
            } else {
                request.setAttribute("error", "Checkout failed.");
            }

            // Redirect or forward to profile or booking page
            response.sendRedirect(request.getContextPath() + "/profile");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during checkout.");
            request.getRequestDispatcher("/profile").forward(request, response);
        }
    }
}
