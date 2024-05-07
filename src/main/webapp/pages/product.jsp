<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Makeup Glam</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/product.css" />
</head>
<body>
<jsp:include page="header.jsp"/> 
    <header>
        <h1>Welcome to Makeup Glam</h1>
    </header>
    <main>
        <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                    url="jdbc:mysql://localhost:3306/glamvault_Cosmetics_shimmer" user="root" password="" />
                <sql:query dataSource="${dataSource}" var="makeupGlams">
                    SELECT * FROM makeup;
                </sql:query>
        <div class="product-container">
            <c:forEach var="makeupGlam" items="${makeupGlams.rows}">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/user/${makeupGlam.product_image}" alt="Product Image">
                    <h2>${makeupGlam.Makeup_Name}</h2>
                    <p class="price">${makeupGlam.price}</p>
                    <button>Buy now</button>
                </div>
            </c:forEach>
        </div>
    </main>
    
</body>
</html>
