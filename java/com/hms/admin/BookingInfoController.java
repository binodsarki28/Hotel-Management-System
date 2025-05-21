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


/**
 * Servlet implementation class HomeController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/bookingInfo"})
public class BookingInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookingService bookingService = new BookingService();
		List<BookingModel> bookingList = bookingService.getAllBookings();

		req.setAttribute("bookingList", bookingList);
		req.getRequestDispatcher("/WEB-INF/pages/admin/bookingInfo.jsp").forward(req, resp);
	}
	
	
}