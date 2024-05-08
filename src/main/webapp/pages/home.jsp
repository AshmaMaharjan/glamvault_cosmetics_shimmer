<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <style>
 @charset "ISO-8859-1";
/* Home section styles */
.home {
    width: 100%;
    height: 50vh;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-wrap: wrap;
    
    background-color: #f5f5dc;
    z-index: 1; /* Decrease z-index to send the home section to the back */
}

.home .image img {
    margin-top: 30px;
    width: 100%;
}

.home .main_content {
    flex: 1 1 400px;
    margin-top: 20px;
}

.main_content h1 {
    color: #333; /* Change text color */
    font-weight: bold;
    margin-left: 23px;
    font-size: 55px;
    font-family: 'Arial', sans-serif; /* Change font */
    text-shadow: -1px 1px 1px black;
    height: 20vh;
}

.main_content h1 span {
    color: #333; /* Change text color */
    text-shadow: 1px 1px 1px black;
}

.main_content p {
    margin-left: 23px;
    color: #808080; /* Change text color */
    font-family: 'Arial', sans-serif; /* Change font */
}

.button {
    margin-left: 13px;
}

.button button {
    width: 150px;
    height: 32px;
    letter-spacing: 3px;
    background-color: #AA336A; /* Change button background color */
    color: white;
    border-radius: 5px;
    border: none;
    transition: 0.5s ease;
    cursor: pointer;
    font-family: 'Arial', sans-serif; /* Change font */
}

.button button:hover {
    background-color: #AA336A; /* Change button background color on hover */
    color: black;
    border: none;
}

/* Product cards section styles */
.product-container {
    display: flex;
    justify-content: space-around; /* Adjust spacing between cards */
    flex-wrap: nowrap; /* Prevent wrapping */
    overflow-x: auto; /* Enable horizontal scrolling */
    margin-top: 40px; /* Adjust margin top */
    padding: 20px; /* Adjust padding */
}

.product-card {
    width: 250px; /* Adjust card width */
    height: 350px; /* Adjust card height */
    background: #fff;
    margin: 0 10px; /* Adjust margin for spacing between cards */
    padding: 20px; /* Adjust padding */
    text-align: center;
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}

.product-card img {
    width: 100%;
    height: 200px; /* Adjust image height */
    object-fit: cover;
}

.product-card h2 {
    margin-top: 15px; /* Adjust margin top */
    margin-bottom: 10px; /* Adjust margin bottom */
    font-size: 20px; /* Increase font size */
}

.product-card p {
    line-height: 1.6; /* Adjust line height */
    font-size: 16px; /* Increase font size */
}

.product-card button {
    margin-top: 20px; /* Adjust margin top */
    padding: 10px 20px; /* Adjust padding */
    font-size: 16px; /* Increase font size */
    background-color: #AA336A; /* Change button background color */
    color: white;
    border-radius: 5px;
    text-decoration: none; /* Remove underline */
    border: none; /* Remove border */
}

.product-card button:hover {
    background-color: #AA336A; /* Change button background color on hover */
    color: black;
}

 </style>
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
<section id="products" class="products">
        <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                    url="jdbc:mysql://localhost:3306/glamvault_Cosmetics_shimmer" user="root" password="" />
                <sql:query dataSource="${dataSource}" var="makeupGlams">
                    SELECT * FROM makeup;
                </sql:query>
        <div class="product-container">
            <c:forEach var="makeupGlam" items="${makeupGlams.rows}">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/user/${makeupGlam.product_image}" alt="Product Image">
                    <div class="product-details">
                        <h2>${makeupGlam.Makeup_Name}</h2>
                       
                        <button>Buy now</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
    
</body>
</html>
