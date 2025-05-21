<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Update Menu</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/menu/updateMenu.css" />
</head>
<body>

	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>
	<c:if test="${not empty success}">
		<p style="color: green;">${success}</p>
	</c:if>

	<div class="register-container">
    <h2 class="register-title">Update Menu</h2>
    
    <div class="form-container">
        <form action="${pageContext.request.contextPath}/updateMenu" method="post" enctype="multipart/form-data">
            <!-- Hidden field for room ID -->
            <input type="hidden" name="menuId" value="${menu.menuId}" />

            <!-- Room Number and Type -->
            <div class="form-row">
                <div class="form-field">
                    <label for="foodName" class="form-label">Food Name:</label>
                    <input type="text" id="foodName" name="foodName" class="form-input" value="${menu.foodName}">
                </div>
                <div class="form-field">
                    <label for="category" class="form-label">Category:</label>
                    <input type="text" id="category" name="category" class="form-input" value="${menu.category}">
                </div>
            </div>

            <!-- Room Description -->
            <div class="form-row">
                <div class="form-field" style="width: 100%;">
                    <label for="foodDescription" class="form-label">Food Description:</label>
                    <textarea id="foodDescription" name="foodDescription" class="form-input" rows="4">${menu.foodDescription}</textarea>
                </div>
            </div>

            <!-- Price and Status -->
            <div class="form-row">
                <div class="form-field">
                    <label for="menuPrice" class="form-label">Food Price:</label>
                    <input type="number" id="menuPrice" name= "menuPrice" class="form-input" value="${menu.menuPrice}">
                </div>
            </div>

            <!-- Current Image -->
            <div class="form-row">
                <div class="form-field" style="width: 100%;">
                    <label class="form-label">Current Image:</label>
                    <img src="${pageContext.request.contextPath}/menu/${menu.menuPhoto}" alt="Room Image" class="room-image-preview">
                </div>
            </div>

            <!-- Upload New Image -->
            <div class="form-row">
                <div class="form-field" style="width: 100%;">
                    <label for="roomPhoto" class="form-label">Upload New Image (optional):</label>
                    <input type="file" id="menuPhoto" name="menuPhoto" class="form-input" accept="image/*">
                </div>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="submit-btn">Update Menu</button>
        </form>
    </div>
</div>



</body>
</html>