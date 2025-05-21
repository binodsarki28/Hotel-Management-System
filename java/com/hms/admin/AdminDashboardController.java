package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hms.service.DashboardService;

/**
 * Servlet implementation class AdminDashboardController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard"})
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private final DashboardService dashboardService = new DashboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("total", dashboardService.getTotalUsers());
        req.setAttribute("room", dashboardService.getTotalRooms());
        req.setAttribute("menu", dashboardService.getTotalMenus());
        req.setAttribute("booking", dashboardService.getTotalBookings());
        req.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(req, resp);
    }

}
