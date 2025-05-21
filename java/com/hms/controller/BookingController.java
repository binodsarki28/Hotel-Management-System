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

// this servlet class control the booking function of the system
@WebServlet(asyncSupported = true, urlPatterns = { "/booking"})
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // making objects
    private final BookingService bookingService = new BookingService();
    private final RoomService roomService = new RoomService();
    
    // redirect to booking page
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/room/booking.jsp").forward(req, resp);
	}

    // get the necessary details from the client side to make successful booking
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int roomId = Integer.parseInt(request.getParameter("roomId"));
            int noOfGuest = Integer.parseInt(request.getParameter("noOfGuest"));
            String status = request.getParameter("status");

            Date checkin = Date.valueOf(request.getParameter("checkin"));
            Date checkout = Date.valueOf(request.getParameter("checkout"));

            // logic of calculating the no of days between the check in date and check out date 
            long days = ChronoUnit.DAYS.between(checkin.toLocalDate(), checkout.toLocalDate()); //  ChronoUnit calculate the number of days between two local dates
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

            boolean success = bookingService.addBooking(booking); // add booking to the database
            if (success) {
                // Update room status to "Not Available"
                boolean roomUpdateSuccess = roomService.updateRoomStatus(roomId, "Not Available"); // after booking room status is changed to the Not Available
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
