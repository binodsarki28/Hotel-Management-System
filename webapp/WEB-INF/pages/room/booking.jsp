<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.hms.model.UserModel" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book a Room</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
            padding: 40px;
        }

        .booking-form {
            background: white;
            padding: 30px;
            max-width: 500px;
            margin: auto;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            border-radius: 10px;
        }

        .booking-form h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .booking-form label {
            display: block;
            margin-top: 10px;
        }

        .booking-form input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
        }

        .booking-form button {
            margin-top: 20px;
            width: 100%;
            background: #28a745;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
        }

        .booking-form button:hover {
            background: #218838;
        }
    </style>
</head>
<body>

<%
    UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
    int userId = loggedInUser != null ? loggedInUser.getUserId() : 0;

    String roomIdParam = request.getParameter("roomId");
    int roomId = roomIdParam != null ? Integer.parseInt(roomIdParam) : 0;
%>

<div class="booking-form">
    <h2>Room Booking</h2>
    <form action="${pageContext.request.contextPath}/booking" method="post">
        <input type="hidden" name="userId" value="<%= userId %>">
        <input type="hidden" name="roomId" value="<%= roomId %>">
        <input type="hidden" name="status" value="Pending">

        <label for="checkin">Check-in Date:</label>
        <input type="date" name="checkin" required>

        <label for="checkout">Check-out Date:</label>
        <input type="date" name="checkout" required>

        <label for="noOfGuest">Number of Guests:</label>
        <input type="number" name="noOfGuest" min="1" required>

        <button type="submit">Confirm Booking</button>
    </form>
</div>

</body>
</html>
