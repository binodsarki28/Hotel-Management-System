<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Room</title>
</head>
<body>

<h2>Update Room</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<c:if test="${not empty success}">
    <p style="color:green;">${success}</p>
</c:if>

<form action="${pageContext.request.contextPath}/updateRoom" method="post" enctype="multipart/form-data">
    <!-- Hidden field to store room ID -->
    <input type="hidden" name="roomId" value="${room.roomId}" />

    <label for="roomNumber">Room Number:</label><br>
    <input type="text" id="roomNumber" name="roomNumber" value="${room.roomNumber}" required><br><br>

    <label for="roomType">Room Type:</label><br>
    <input type="text" id="roomType" name="roomType" value="${room.roomType}" required><br><br>

    <label for="roomDescription">Room Description:</label><br>
    <textarea id="roomDescription" name="roomDescription" rows="4" cols="50">${room.roomDescription}</textarea><br><br>

    <label for="roomPrice">Room Price:</label><br>
    <input type="number" id="roomPrice" name="roomPrice" value="${room.roomPrice}" step="0.01" ><br><br>

    <!-- Preview current image -->
    <label>Current Image:</label><br>
    <img src="${pageContext.request.contextPath}/menu/${room.roomPhoto}" alt="Room Image" style="width: 200px; height: auto; border-radius: 5px;"><br><br>

    <!-- Upload new image (optional) -->
    <label for="roomPhoto">Upload New Image (optional):</label><br>
    <input type="file" id="roomPhoto" name="roomPhoto" accept="image/*"><br><br>

    <button type="submit">Update Room</button>
</form>

</body>
</html>
