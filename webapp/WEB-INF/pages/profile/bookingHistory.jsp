<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking History</title>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/profile/profile.css" />
        <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/admin/dashboard.css" />
</head>
<body>
<c:if test="${not empty loggedInUser}">
    <div class="table-container">
        <h3>📜 My Booking History</h3>
        <table>
            <thead>
                <tr>
                    <th>Room ID</th>
                    <th>Check-in</th>
                    <th>Check-out</th>
                    <th>Total Price</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td>${booking.roomId}</td>
                        <td>${booking.checkInDate}</td>
                        <td>${booking.checkOutDate}</td>
                        <td>Rs.${booking.totalAmount}</td>
                        <td>${booking.status}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/checkout" method="post">
                                <input type="hidden" name="bookingId" value="${booking.bookingId}" />
                                <button type="submit" class="checkout-btn">Checkout</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>
