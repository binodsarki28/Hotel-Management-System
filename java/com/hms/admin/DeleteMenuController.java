package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hms.service.MenuService;

/**
 * Servlet implementation class DeleteRoomController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/deleteMenu"})
public class DeleteMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final MenuService menuService = new MenuService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        String menuIdParam = req.getParameter("menuId");
	        if (menuIdParam != null) {
	            int menuId = Integer.parseInt(menuIdParam);
	            boolean deleted = menuService.deleteMenuById(menuId);
	            if (deleted) {
	                resp.sendRedirect(req.getContextPath() + "/dashboard/menuInfo?deleted=true");
	            } else {
	                resp.sendRedirect(req.getContextPath() + "/dashboard/menuInfo?error=deleteFailed");
	            }
	        } else {
	            resp.sendRedirect(req.getContextPath() + "/dashboard/menuInfo?error=invalidId");
	        }
	    }

}