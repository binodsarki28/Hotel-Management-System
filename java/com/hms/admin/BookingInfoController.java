package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.hms.model.BookingModel;
import com.hms.service.BookingService;

// redirect to booking info page for see booking details
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/bookingInfo" })
public class BookingInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// this method redirect to the booking info page with the list of bookings for fetching booking details 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BookingService bookingService = new BookingService(); // making object

		List<BookingModel> bookingList = bookingService.getAllBookings(); // storing in bookingList to fetch data in
																			// booking info page

		req.setAttribute("bookingList", bookingList); // setting it in the attributes
		req.getRequestDispatcher("/WEB-INF/pages/admin/bookingInfo.jsp").forward(req, resp);
	}

}