<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Header</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main/header.css" />
</head>
<body>
    <header class="site-header">
        <div class="container">
            <div class="logo-wrapper">
                <h1 class="logo">
                    <a href=""><img src="${pageContext.request.contextPath}/resources/logos/hotelLogo.jpg" alt="Hotel Logo" /></a>
                </h1>
                <h1 class="hotel-name">Sanskritik Ghar</h1>
            </div>
            <nav class="nav">
                <ul>
                    <li><a class="active-link" href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a class="active-link" href="${pageContext.request.contextPath}/room">Room</a></li>
                    <li><a class="active-link" href="${pageContext.request.contextPath}/menu">Menu</a></li>
                    <li><a class="active-link" href="${pageContext.request.contextPath}/aboutUs">About Us</a></li>
                    <li><a class="active-link" href="${pageContext.request.contextPath}/contactUs">Contact Us</a></li>

                    <!-- Show login when NO user or admin is logged in -->
                    <c:if test="${empty loggedInUser and empty admin}">
                        <li><a class="active-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                    </c:if>

                    <!-- Show profile when user IS logged in -->
                    <c:if test="${not empty loggedInUser}">
                        <li><a class="active-link" href="${pageContext.request.contextPath}/profile">Profile</a></li>
                    </c:if>

                    <!-- Show admin dashboard when admin IS logged in -->
                    <c:if test="${not empty admin}">
                        <li><a class="active-link" href="${pageContext.request.contextPath}/dashboard">Admin Dashboard</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </header>
</body>
</html>
