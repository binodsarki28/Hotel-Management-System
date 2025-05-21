package com.hms.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.hms.model.MenuModel;
import com.hms.service.AddMenuService;
import com.hms.util.ImageUtil;

//This servlet class is adding the menu to the database by doPost method at first it takes the value from addMenu 
//jsp file and store it to the menu table from menuService class
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/addMenu" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class AddMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final AddMenuService addMenuService = new AddMenuService();
	private final ImageUtil imageUtil = new ImageUtil();

	// This method help to redirect to the add menu page
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/menu/addMenu.jsp").forward(req, resp);
	}

	// This method add the menu data to the menu table
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MenuModel menuModel = extractMenuModel(req, resp);
			Boolean isAdded = addMenuService.addMenu(menuModel);

			if (isAdded == null) {
				handleError(req, resp, "Our server is under maintenance. Please try again later!");
			} else if (isAdded) {
				if (uploadImage(req)) {
					handleSuccess(req, resp, "Menu Added Successfully", "/WEB-INF/pages/admin/dashboard.jsp");
				} else {
					handleError(req, resp, "Could not upload the image. Please try again later!");
				}
			} else {
				handleError(req, resp, "Could not add the menu. Please try again later!");
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}

	// extract the menu data from the jsp file
	private MenuModel extractMenuModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String foodName = req.getParameter("foodName");
		String category = req.getParameter("category");
		String foodDescription = req.getParameter("foodDescription");
		float menuPrice = Float.parseFloat(req.getParameter("menuPrice"));

		Part image = req.getPart("menuPhoto");
		String menuPhoto = imageUtil.getImageNameFromPart(image);

		return new MenuModel(foodName, category, foodDescription, menuPrice, menuPhoto);
	}

	// helps to upload the image
	private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("menuPhoto");
		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "uploads");
	}

	// it sent the suitable message when menu added successfully
	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	// it sent the suitable message when menu added gets failed
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.getRequestDispatcher("/WEB-INF/pages/room/addMenu.jsp").forward(req, resp);
	}

}
