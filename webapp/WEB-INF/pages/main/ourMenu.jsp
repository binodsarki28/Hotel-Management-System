<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main/ourMenu.css" />
</head>
<body>
	<div class="menu-section">
        <h1 class="menu-title">Our Food Menu</h1>

        <div class="menu-container">
            <div class="menu-card">
                <img src="${pageContext.request.contextPath}/resources/menu/Thakali-Set.jpg" alt="thakali">
                <h3>Thakali Khana Set</h3>
                <p>Enjoy the authentic taste of thakali</p>
            </div>

            <div class="menu-card">
                <img src="${pageContext.request.contextPath}/resources/menu/Nepali Beverage(tongba).jpg" alt="nepali beverage">
                <h3>Neplai Beverage</h3>
                <p>Try pure local nepali beverages</p>
            </div>

            <div class="menu-card">
                <img src="${pageContext.request.contextPath}/resources/menu/Nepali Khaja Set.jpg" alt="khaja">
                <h3>Nepali Khaja Set</h3>
                <p>Enjoy nepali khaja set at Snack time</p>
            </div>

            <div class="menu-card">
                <img src="${pageContext.request.contextPath}/resources/menu/Continental-set.jpg" alt="continental">
                <h3>Continental Dish</h3>
                <p>Many type of continental dish also available</p>
            </div>
        </div>

        <div class="view-menu">
            <a href="${pageContext.request.contextPath}/pages/menu/menu.jsp">View All Menu</a>
        </div>
    </div>
</body>
</html>