<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <p>Discover the story behind Glamify, your premier destination for top-notch makeup essentials that accentuate your natural beauty. At Glamify, we believe makeup isn't just about concealing imperfections but also about expressing your individuality and flair.</p>
    <p>Established in 2024 by shimmers, Glamify has evolved from a humble passion project to a globally adored brand cherished by makeup aficionados and beauty enthusiasts alike. Our goal is to build confidence and radiance in every individual.</p>
    <h2>Our Principles</h2>
    <ul>
      <li><strong>Excellence:</strong> We are dedicated to delivering makeup products of unparalleled quality, meticulously crafted with safe and efficacious ingredients.</li>
      <li><strong>Diversity:</strong> We embrace inclusivity and strive to develop products that cater to individuals of diverse skin tones and types.</li>
      <li><strong>Innovation:</strong> We are committed to continual research and innovation to bring you the latest trends and breakthrough formulations.</li>
      <li><strong>Sustainability:</strong> We are devoted to reducing our ecological footprint by employing eco-conscious packaging and sustainable practices.</li>
    </ul>
    <div class="makeupgallery">
      <div class="makeupItem">
        <img src="../resources/user/foundation.jpg" alt="Makeup Product 1">
      </div>
      <div class="makeupItem">
        <img src="../resources/user/blushh.jpg" alt="Makeup Product 2">
      </div>
      <div class="makeupItem">
        <img src="../resources/user/lipstick.jpg" alt="Makeup Product 3">
      </div>
    </div>
  </div>
</body>
</html>