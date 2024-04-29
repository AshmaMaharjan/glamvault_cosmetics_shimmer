<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/home.css" /> --%>
</head>
<body>
<jsp:include page="header.jsp"/> 
    <!-- home content -->
    <section class="home">
        <div class="main_content">
            <h1> <span>Enhance Your Natural Beauty </span>
                <br>with Every Brushstroke
            </h1>
            <p>
                Here at GlamVault Cosmetics, welcome! Discover your ideal look with us, featuring fascinating eyeshadows and dazzling foundations. <br> With quick shipping and hassle-free returns, you can shop with confidence. Discover the GlamVault distinction right now!
                <br>
            </p>
            <div class="button"><button>Shop Now</button></div>
        </div>
        <div class="image">
            <img src="./images/a.png" alt="">
        </div>
    </section>
    <!-- product cards -->
    <section>
        <div class="Wrapper">
            <div class="container-box">
                <div class="unique-box">
                    <div class="image"><img alt="Blush" src="e.jpg"></div>
                    <h3>Blush</h3>
                    <p>Radiant confidence and blushing beauty</p>
                    <div class="price">
                        <p>$25</p>
                    </div>
                    <a href="#">Add to Cart</a>
                </div>
                <div class="unique-box">
                    <div class="image"><img alt="Eyeshadow" src="f.jpg"></div>
                    <h3>Eyeshadow</h3>
                    <p>Paint your dreams with every shade.</p>
                    <div class="price">
                        <p>$50</p>
                    </div>
                    <a href="#">Add to Cart</a>
                </div>
                <div class="unique-box">
                    <div class="image"><img alt="Foundation" src="g.jpg"></div>
                    <h3>Foundation</h3>
                    <p>Build your confidence, layer by layer.</p>
                    <div class="price">
                        <p>$45</p>
                    </div>
                    <a href="#">Add to Cart</a>
                </div>
            </div>
        </div>
    </section>
</body>
</html>