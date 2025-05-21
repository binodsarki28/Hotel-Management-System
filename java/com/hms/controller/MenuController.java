package com.hms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// for redirecting to menu page and show the menus in the page
@WebServlet(asyncSupported = true, urlPatterns = { "/menu" })
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// redirect to menu page
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/menu/menu.jsp").forward(req, resp);;
	}

	
}
