<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hms.model.MenuModel, com.hms.service.AddMenuService, java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    AddMenuService menuService = new AddMenuService();
    List<MenuModel> menuList = menuService.getAllMenus();
    request.setAttribute("menuList", menuList);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rooms</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/room/room.css" />
</head>
<body>

<jsp:include page="../main/header.jsp" />
<br><br><br>

<h2>Available Rooms</h2>

<div style="display: flex; flex-wrap: wrap; gap: 20px; padding: 20px;">
    <c:forEach var="menu" items="${menuList}">
        <div style="border: 1px solid #ccc; border-radius: 8px; width: 250px; padding: 15px; box-shadow: 2px 2px 10px rgba(0,0,0,0.1);">
            <img src="${pageContext.request.contextPath}/uploads/${menu.menuPhoto}" alt="Menu Image" style="width: 100%; height: 150px; object-fit: cover; border-radius: 5px;">

            <h3>Food Name: ${menu.foodName}</h3>
            <p><strong>Category:</strong> ${menu.category}</p>
            <p><strong>Description:</strong> ${menu.foodDescription}</p>s
            <p><strong>Price:</strong> Rs. ${menu.menuPrice}</p>
        </div>
    </c:forEach>
</div>

</body>
</html>
