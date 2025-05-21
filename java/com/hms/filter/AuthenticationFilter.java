package com.hms.filter;

import java.io.IOException;

import com.hms.util.CookiesUtil;
import com.hms.util.SessionUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String HOME = "/home";
    private static final String PROFILE = "/profile";
    private static final String ROOT = "/";
    private static final String DASHBOARD = "/dashboard";
    private static final String LOGOUT = "/logout";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        boolean isLoggedIn = SessionUtil.getAttribute(req, "loggedInUser") != null;

        String userRole = CookiesUtil.getCookie(req, "role") != null
                ? CookiesUtil.getCookie(req, "role").getValue().toUpperCase()
                : null;

        // Allow static content like CSS, JS, Images, etc.
        if (uri.endsWith(".css") || uri.contains("/resources/") || uri.endsWith(".js")
                || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".jpeg")) {
            chain.doFilter(request, response);
            return;
        }

     // Public pages allowed without login
        if (!isLoggedIn && (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME)
                || uri.endsWith(LOGOUT) || uri.equals(req.getContextPath() + ROOT)
                || uri.endsWith("/room") || uri.endsWith("/search") || uri.endsWith("/menu") || uri.endsWith("/aboutUs") || uri.endsWith("/contactUs"))) {
            chain.doFilter(request, response);
            return;
        }
        // Redirect unauthenticated users trying to access protected resources
        if (!isLoggedIn) {
            res.sendRedirect(req.getContextPath() + LOGIN);
            return;
        }

        // Admin role logic
        if ("ADMIN".equals(userRole)) {
            if (uri.startsWith(req.getContextPath() + DASHBOARD) || uri.endsWith(LOGOUT)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + DASHBOARD);
            }
            return;
        }

        // Normal user logic
        if ("USER".equals(userRole)) {
            // Prevent access to login/register pages
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                res.sendRedirect(req.getContextPath() + HOME);
                return;
            }

            // Redirect user if trying to access admin dashboard
            if (uri.startsWith(req.getContextPath() + DASHBOARD)) {
                res.sendRedirect(req.getContextPath() + HOME);
                return;
            }

            // Allow all other valid pages including logout
            chain.doFilter(request, response);
            return;
        }

        // Fallback (unknown roles)
        res.sendRedirect(req.getContextPath() + LOGIN);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
