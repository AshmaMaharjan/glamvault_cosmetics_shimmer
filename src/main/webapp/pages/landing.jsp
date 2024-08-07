<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Makeup Glam</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
