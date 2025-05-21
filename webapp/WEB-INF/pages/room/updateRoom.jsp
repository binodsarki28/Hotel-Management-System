<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Update Room</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/room/updateRoom.css" />
</head>
<body>

	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>
	<c:if test="${not empty success}">
		<p style="color: green;">${success}</p>
	</c:if>

	<div class="register-container">
    <h2 class="register-title">Update Room</h2>
    
    <div class="form-container">
        <form action="${pageContext.request.contextPath}/updateRoom" method="post" enctype="multipart/form-data">
            <!-- Hidden field for room ID -->
            <input type="hidden" name="roomId" value="${room.roomId}" />

            <!-- Room Number and Type -->
            <div class="form-row">
                <div class="form-field">
                    <label for="roomNo" class="form-label">Room No:</label>
                    <input type="number" id="roomNo" name="roomNumber" class="form-input" value="${room.roomNumber}">
                </div>
                <div class="form-field">
                    <label for="roomType" class="form-label">Room Type:</label>
                    <input type="text" id="roomType" name="roomType" class="form-input" value="${room.roomType}">
                </div>
            </div>

            <!-- Room Description -->
            <div class="form-row">
                <div class="form-field" style="width: 100%;">
                    <label for="roomDescription" class="form-label">Room Description:</label>
                    <textarea id="roomDescription" name="roomDescription" class="form-input" rows="4">${room.roomDescription}</textarea>
                </div>
            </div>

            <!-- Price and Status -->
            <div class="form-row">
                <div class="form-field">
                    <label for="pricePerDay" class="form-label">Price Per Day:</label>
                    <input type="number" step="0.01" id="pricePerDay" name="roomPrice" class="form-input" value="${room.roomPrice}">
                </div>
                <div class="form-field">
                    <label for="status" class="form-label">Status:</label>
                    <select id="status" name="status" class="form-input" required>
                        <option value="available" ${room.status == 'available' ? 'selected' : ''}>Available</option>
                        <option value="unavailable" ${room.status == 'unavailable' ? 'selected' : ''}>Unavailable</option>
                    </select>
                </div>
            </div>

            <!-- Current Image -->
            <div class="form-row">
                <div class="form-field" style="width: 100%;">
                    <label class="form-label">Current Image:</label>
                    <img src="${pageContext.request.contextPath}/menu/${room.roomPhoto}" alt="Room Image" class="room-image-preview">
                </div>
            </div>

            <!-- Upload New Image -->
            <div class="form-row">
                <div class="form-field" style="width: 100%;">
                    <label for="roomPhoto" class="form-label">Upload New Image (optional):</label>
                    <input type="file" id="roomPhoto" name="roomPhoto" class="form-input" accept="image/*">
                </div>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="submit-btn">Update Room</button>
        </form>
    </div>
</div>



</body>
</html>
