<%@page import="util.stringUtil"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
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
    background: ##000000
;
    padding: 20px;
    position: relative;
}

.menuheader {
    position: absolute;
    top: 5px;
    left: 0;
    font-size: 20px;
    font-weight: bold;
    color: #fff;
    z-index: 2; /* Increase z-index to bring the header to the front */
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
    background: #AA336A; /*pink*/
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
.login-wrapper {
    display: flex;
    align-items: center;
    margin-left: 20px;
    color: #333;
    font-size: 14px;
}

.login-wrapper form {
    display: flex;
    align-items: center;
}

.login-wrapper input[type="submit"] {
    padding: 6px 15px;
    font-size: 14px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    background-color: #b59e7a; /* Dark beige color */
    color: #000000;
    text-decoration: none;
    transition: background-color 0.3s ease;
}

.login-wrapper input[type="submit"]:hover {
    background-color: #8b7a55; 
}

.login-wrapper span {
    margin-top: 5px; /* Add margin between icon and text */
}

</style>
<link rel="stylesheet" type="text/css" href="../stylesheets/header.css" />
</head>
<body>
<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute(stringUtil.User_name);
    String contextPath = request.getContextPath();
    
%>
    <nav class="navigation">
        <div class="menuheader">
            GlamVault Cosmetics Shimmer <!-- This is   ${pageContext.request.contextPath} your logo text -->
        </div>
        <ul class="menulinks">
            <li><a href="${pageContext.request.contextPath}/pages/home.jsp">Home</a></li>
            <li class="center"><a href="${pageContext.request.contextPath}/pages/profile.jsp">Profile</a></li>
            <li class="forward"><a href="${pageContext.request.contextPath}/pages/aboutus.jsp">About Us</a></li>
            <li class="forward"><a href="${pageContext.request.contextPath}/pages/product.jsp">Product</a></li>
           <li class="forward"><a href="${pageContext.request.contextPath}/pages/contact.jsp">Contact</a></li>
             <li>
             
                        <div class="login-wrapper">
                        
                <form action="<%
                    // Conditionally set the action URL based on user session
                    if (currentUser != null) {
                        out.print(contextPath + stringUtil.SERVLET_URL_LOGOUT);
                    } else {
                        out.print(contextPath + stringUtil.PAGE_URL_LOGIN);
                    }
                %>" method="post">
                    <input type="submit" value="<%
                        // Conditionally set the button label based on user session
                        if (currentUser != null) {
                            out.print(stringUtil.LOGOUT);
                        } else {
                            out.print(stringUtil.LOGIN);
                        }
                    %>"/>
                </form>
                </div>
               
            	</li>
            
        </ul>
    </nav>
</body>
</html>