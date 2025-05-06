<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hms.model.RoomModel, com.hms.service.AddRoomService, java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    AddRoomService roomService = new AddRoomService();
    List<RoomModel> roomList = roomService.getAllRooms();
    request.setAttribute("roomList", roomList);
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
    <c:forEach var="room" items="${roomList}">
        <div style="border: 1px solid #ccc; border-radius: 8px; width: 250px; padding: 15px; box-shadow: 2px 2px 10px rgba(0,0,0,0.1);">
            <img src="${pageContext.request.contextPath}/uploads/${room.roomPhoto}" alt="Room Image" style="width: 100%; height: 150px; object-fit: cover; border-radius: 5px;">

            <h3>Room No: ${room.roomNo}</h3>
            <p><strong>Type:</strong> ${room.roomType}</p>
            <p><strong>Description:</strong> ${room.roomDescription}</p>
            <p><strong>Price:</strong> Rs. ${room.pricePerDay}</p>
            <p><strong>Status:</strong> ${room.status}</p>
        </div>
    </c:forEach>
</div>

</body>
</html>
