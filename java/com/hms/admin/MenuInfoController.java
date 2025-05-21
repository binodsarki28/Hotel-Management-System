package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.hms.model.MenuModel;
import com.hms.service.MenuService;

//redirect to menu info page for see menu details
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/menuInfo"})
public class MenuInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// this method redirect to the menu info page with the list of menus for fetching menu details 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MenuService menuService = new MenuService();
		List<MenuModel> menuList = menuService.getAllMenuInfo();

		req.setAttribute("menuList", menuList);
		req.getRequestDispatcher("/WEB-INF/pages/admin/menuInfo.jsp").forward(req, resp);
	}	

}
