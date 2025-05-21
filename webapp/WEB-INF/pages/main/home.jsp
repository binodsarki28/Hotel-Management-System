<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main/home.css" />
</head>
<body> 
	<jsp:include page="header.jsp" />

	<br><br>

	<div
		style="text-align: center; padding: 20px; background-color: #f2f2f2; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); max-width: 800px; margin: auto;">
		<h1
			style="font-size: 2.5em; color: #608196; font-family: 'Arial', sans-serif; margin-bottom: 15px;">
			Welcome to Sanskritik Ghar</h1>
		<p
			style="font-size: 1.2em; color: #333; line-height: 1.6; font-family: 'Georgia', serif; font-style: italic; text-align: center;">
			"सांस्कृतिक घरमा स्वागत छ, जहाँ परम्परा र आतिथ्यताको संगम हुन्छ।
			काठमाण्डौको मुटुमा आधुनिक सुविधासँगै नेपाली सांस्कृतिक अनुभवको मजा
			लिनुहोस्। यहाँ तपाईंलाई आत्मियता, शान्ति र घरको जस्तो अनुभव हुनेछ।"</p>
	</div>
	
	<br><br>
	
	
	<jsp:include page="ourRoom.jsp" />
	
	<br><br>
	
	<jsp:include page="ourMenu.jsp" />

	<br><br>
	
	<jsp:include page="hotelService.jsp" />
		
	<br><br>
	<jsp:include page="footer.jsp" />

</body>
</html>
