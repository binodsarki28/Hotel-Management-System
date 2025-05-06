package com.hms.filter;

import java.io.IOException;

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

            // Allow static content like CSS, JS, Images
            if (uri.endsWith(".css") || uri.contains("/resources/")) {
                chain.doFilter(request, response);
                return;
            }

            // Allow public pages even if not logged in
            if (!isLoggedIn && (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.equals(req.getContextPath() + ROOT))) {
                chain.doFilter(request, response);
                return;
            }

            // Redirect to login if accessing protected pages without login
            if (!isLoggedIn && uri.contains(PROFILE)) {
                res.sendRedirect(req.getContextPath() + LOGIN);
                return;
            }

            // Prevent access to login/register pages if already logged in
            if (isLoggedIn && (uri.endsWith(LOGIN) || uri.endsWith(REGISTER))) {
                res.sendRedirect(req.getContextPath() + HOME);
                return;
            }

            // Allow all other cases
            chain.doFilter(request, response);
        }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
