package com.hms.controller;

import com.hms.model.BookingModel;
import com.hms.model.RoomModel;
import com.hms.service.BookingService;
import com.hms.service.RoomService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.temporal.ChronoUnit;

@WebServlet(asyncSupported = true, urlPatterns = { "/booking"})
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final BookingService bookingService = new BookingService();
    private final RoomService roomService = new RoomService();
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/room/booking.jsp").forward(req, resp);
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            int noOfGuest = Integer.parseInt(request.getParameter("noOfGuest"));
            String status = request.getParameter("status");

            Date checkin = Date.valueOf(request.getParameter("checkin"));
            Date checkout = Date.valueOf(request.getParameter("checkout"));

            long days = ChronoUnit.DAYS.between(checkin.toLocalDate(), checkout.toLocalDate());
            if (days <= 0) {
                request.setAttribute("error", "Check-out must be after check-in.");
                request.getRequestDispatcher("/WEB-INF/pages/room/booking.jsp").forward(request, response);
                return;
            }

            RoomModel room = roomService.getRoomById(roomId);
            float pricePerDay = room.getPricePerDay();

            float totalAmount = days * pricePerDay;

            BookingModel booking = new BookingModel();
            booking.setUserId(userId);
            booking.setRoomId(roomId);
            booking.setCheckInDate(checkin);
            booking.setCheckOutDate(checkout);
            booking.setNoOfGuest(noOfGuest);
            booking.setTotalAmount(totalAmount);
            booking.setStatus(status);

            boolean success = bookingService.addBooking(booking);
            if (success) {
                // Update room status to "Not Available"
                boolean roomUpdateSuccess = roomService.updateRoomStatus(roomId, "Not Available");
                if (!roomUpdateSuccess) {
                    System.out.println("Failed to update room status.");
                    // Optionally, handle this failure gracefully
                }

                request.setAttribute("booking", booking); // send booking data to JSP
                request.getRequestDispatcher("/WEB-INF/pages/room/successBooking.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Booking failed.");
                request.getRequestDispatcher("/WEB-INF/pages/room/booking.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong.");
            request.getRequestDispatcher("/WEB-INF/pages/room/booking.jsp").forward(request, response);
        }
    }

}
