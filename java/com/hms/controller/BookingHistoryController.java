package com.hms.controller;

import java.io.IOException;
import java.util.List;

import com.hms.model.BookingModel;
import com.hms.model.UserModel;
import com.hms.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// helps to fetch the booking histroy for a user after clicking on booking history button by the user
@WebServlet(asyncSupported = true, urlPatterns = { "/bookingHistory" })
public class BookingHistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        req.setAttribute("loggedInUser", loggedInUser);
    	UserService userService = new UserService();
        List<BookingModel> bookingList = userService.getBookingsByUserId(loggedInUser.getUserId()); // store the list of booking history of user for fetching in page

        // Set booking list in request
        req.setAttribute("bookings", bookingList);
       
        req.getRequestDispatcher("/WEB-INF/pages/profile/bookingHistory.jsp").forward(req, resp);
    }
}
