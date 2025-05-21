package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hms.model.MenuModel;
import com.hms.service.MenuService;

// update the menu after changing the value
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/updateMenu" })
public class UpdateMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// redirect to update menu page
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/pages/menu/updateMenu.jsp").forward(request, response);
	}

	// gets the data from the update menu page and update the details using the menuService class
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menuIdParam = request.getParameter("menuId");

		if (menuIdParam == null || menuIdParam.isEmpty()) {
			request.setAttribute("error", "Menu ID is missing.");
			request.getRequestDispatcher("/WEB-INF/pages/menu/updateMenu.jsp").forward(request, response);
			return;
		}

		int menuId = Integer.parseInt(menuIdParam);

		MenuService menuService = new MenuService();
		MenuModel menu = menuService.getMenuById(menuId);

		request.setAttribute("menu", menu);
		request.getRequestDispatcher("/WEB-INF/pages/menu/updateMenu.jsp").forward(request, response);
	}

}
