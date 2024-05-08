<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About Us</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/aboutus.css" />
</head>
<body>
<jsp:include page="header.jsp"/> 
<header>
    <h1>Welcome to GlamVault</h1>
</header>
<div class="Content_Wrapper">
    <h2>Our Journey</h2>
    <p>Discover the origins of Glamify, your go-to destination for high-quality makeup that enhances your natural beauty. At Glamify, we believe that makeup is about more than just covering flaws; it is also about expressing your own personality and style.</p>
    <p>Glamify, founded in 2024 by shimmers, has grown from a small passion project to an internationally adored company cherished by makeup lovers and beauty enthusiasts alike. Our mission is to instill confidence and brilliance in each individual.</p>
    <br>
    <h2>Our Principles</h2>
    <ul>
      <li><strong>Excellence:</strong> We are committed to providing makeup products of the highest quality, precisely designed from safe and effective materials.</li>
      <li><strong>Diversity:</strong> We value diversity and seek to provide goods that cater to people with different skin tones and kinds.</li>
      <li><strong>Innovation:</strong> We are committed to ongoing research and innovation to provide you the most recent trends and breakthrough formulations.</li>
      <li><strong>Sustainability:</strong> We are devoted to lowering our environmental impact by using eco-friendly packaging and sustainable procedures.</li>
    </ul>
    <div class="makeupgallery">
      <div class="makeupItem">
        <img src="../resources/user/l.jpg" alt="Makeup Product 1">
      </div>
      <div class="makeupItem">
        <img src="../resources/user/f.jpg" alt="Makeup Product 2">
      </div>
      <div class="makeupItem">
        <img src="../resources/user/B.jpg" alt="Makeup Product 3">
      </div>
    </div>
</div>
</body>
</html>
