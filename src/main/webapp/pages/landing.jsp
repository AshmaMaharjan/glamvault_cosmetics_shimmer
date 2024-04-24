<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/landing.css" />
</head>
<body>
<jsp:include page="header.jsp"/> 
    
    <section class="hero">
        <div class="hero-content">
            <h1>Welcome to Our Makeup Store</h1>
            <p>Discover amazing makeup products to enhance your beauty.</p>
            <a href="#products" class="btn">Shop Now</a>
        </div>
    </section>
    <section id="products" class="products">
        <div class="product-card">
            <div class="product-image">
                <img src="blush.jpg" alt="Blush">
            </div>
            <div class="product-details">
                <h2>Blush</h2>
                <p>Radiant confidence and blushing beauty.</p>
                <span class="price">$25</span>
            </div>
        </div>
        <div class="product-card">
            <div class="product-image">
                <img src="eyeshadow.jpg" alt="Eyeshadow">
            </div>
            <div class="product-details">
                <h2>Eyeshadow</h2>
                <p>Paint your dreams with every shade.</p>
                <span class="price">$50</span>
            </div>
        </div>
        <div class="product-card">
            <div class="product-image">
                <img src="foundation.jpg" alt="Foundation">
            </div>
            <div class="product-details">
                <h2>Foundation</h2>
                <p>Build your confidence, layer by layer.</p>
                <span class="price">$45</span>
            </div>
        </div>
    </section>
    <footer>
        <!-- Footer content here -->
    </footer>
</body>
</html>