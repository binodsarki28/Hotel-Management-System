package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hms.service.DashboardService;

// this page is redirecting admin to the dashboard page
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final DashboardService dashboardService = new DashboardService(); // making object to call its methods

	// redirect to dashboard page
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("total", dashboardService.getTotalUsers()); // store the total user
		req.setAttribute("room", dashboardService.getTotalRooms()); // store the total rooms
		req.setAttribute("menu", dashboardService.getTotalMenus()); // store the total menus
		req.setAttribute("booking", dashboardService.getTotalBookings()); // store the total bookings
		req.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(req, resp);
	}

}
