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

/**
 * Servlet implementation class MenuInfoController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/menuInfo"})
public class MenuInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MenuService menuService = new MenuService();
		List<MenuModel> menuList = menuService.getAllMenuInfo();

		req.setAttribute("menuList", menuList);
		req.getRequestDispatcher("/WEB-INF/pages/admin/menuInfo.jsp").forward(req, resp);
	}

	

}
