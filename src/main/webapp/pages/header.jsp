<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

body {
    /* height: 100vh; */
    /* display: flex; */
    align-items: flex-start;
    justify-content: flex-end;
    background: #f8f8f7;
    padding: 20px;
    position: relative;
}

.menuheader {
    position: absolute;
    top: 5px;
    left: 0;
    font-size: 20px;
    font-weight: bold;
    color: #fff; /* Text color for the logo */
    z-index: 4; /* Ensure the header stays above the navigation */
}

.menulinks {
    position: absolute;
    top: 0;
    right: 0;
    width: 100%; /* Adjust width to accommodate the logo */
    display: flex;
    align-items: center;
    justify-content: flex-end; /* Align text to the right corner */
    background: #9F8C76; /* Background color for the navigation bar */
    padding: 20px 15px;
    border-radius: 12px;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.menulinks li {
    list-style-type: none;
    margin: 0 12px;
}

.menulinks li a {
    position: relative;
    color: #fff;
    font-size: 20px;
    font-weight: 500;
    padding: 6px 0;
    text-decoration: none;
}

.menulinks li a:before {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    height: 3px;
    width: 0%;
    background: #AA336A;
    border-radius: 12px;
    transition: all 0.4s ease;
}

.menulinks li a:hover:before {
    width: 100%;
}

.menulinks li.center a:before {
    left: 50%;
    transform: translateX(-50%);
}

.menulinks li.upward a:before {
    width: 100%;
    bottom: -5px;
    opacity: 0;
}

.menulinks li.upward a:hover:before {
    bottom: 0px;
    opacity: 1;
}

.menulinks li.forward a:before {
    width: 100%;
    transform: scaleX(0);
    transform-origin: right;
    transition: transform 0.4s ease;
}

.menulinks li.forward a:hover:before {
    transform: scaleX(1);
    transform-origin: left;
}

</style>
<link rel="stylesheet" type="text/css" href="../stylesheets/header.css" />
</head>
<body>
    <nav class="navigation">
        <div class="menuheader">
            GlamVault Cosmetics Shimmer <!-- This is   ${pageContext.request.contextPath} your logo text -->
        </div>
        <ul class="menulinks">
            <li><a href="#">Home</a></li>
            <li class="center"><a href="#">Profile</a></li>
            <li class="forward"><a href="#">About Us</a></li>
            <li class="forward"><a href="#">Product</a></li>
            <li><a href="#">LogIn</a></li>
        </ul>
    </nav>
</body>
</html>