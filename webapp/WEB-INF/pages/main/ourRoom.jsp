<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main/ourRoom.css" />
</head>
<body>
	<div class="room-section">
        <h1>Our Room Type</h1>

        <div class="room-container">
            <div class="room-card">
                <img src="${pageContext.request.contextPath}/resources/room/Room1.png" alt="Room 1">
                <h3>Single Room</h3>
                <p>Best for the single person</p>
            </div>
            <div class="room-card">
                <img src="${pageContext.request.contextPath}/resources/room/2 sitter.jpg" alt="Room 2">
                <h3>Couple Room</h3>
                <p>Enjoy you best time here while traveling</p>
            </div>
            <div class="room-card">
                <img src="${pageContext.request.contextPath}/resources/room/3 sitter.jpg" alt="Room 3">
                <h3>Family Room</h3>
                <p>For family person</p>
            </div>
            <div class="room-card">
                <img src="${pageContext.request.contextPath}/resources/room/cottage.png" alt="Room 4">
                <h3>Cottage</h3>
                <p>For small party and family gathering</p>
            </div>
        </div>

        <div class="view-room">
            <a href="${pageContext.request.contextPath}/pages/room/room.jsp">View All Room</a>
        </div>
    </div>
</body>
</html>