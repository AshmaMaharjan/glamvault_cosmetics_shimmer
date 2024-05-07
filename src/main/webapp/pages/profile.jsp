<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="controller.database.GlamVaultDBController" %>

<%@ page import="model.UserMakeupModel" %>
<%@ page import = "util.stringUtil" %>
<%@ page import="model.Loginresult" %>
    <%
    // Check if the user is logged in
    // Assuming you have a session attribute named "loggedInUser" that stores the logged-in user's username
    String loggedInUsername = (String) session.getAttribute(stringUtil.User_name);
    if (loggedInUsername == null || loggedInUsername.isEmpty()) {
        // Redirect to login page if not logged in
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return; // Stop further execution
    }

    // Initialize the database controller
    GlamVaultDBController dbController = new GlamVaultDBController();
    
    // Retrieve user profile information from the database based on the logged-in username
    UserMakeupModel profileUser = dbController.getProfileUser(loggedInUsername);

    // Check if user profile is null
    if (profileUser == null) {
        // Handle case where user profile is not found
        // For example, display an error message
        out.println("User profile not found");
        return; // Stop further execution
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
        body {
            font-family: Arial, sans-serif;
            
            margin: 0;
            padding: 0;
        }

        .profile-container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #ffffff;
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            padding: 30px 20px; /* Increased padding */
        }

        .profile-header {
            background-color: #f48fb1;
            color: #333;
            padding: 20px;
            text-align: center;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
        }

        .profile-header h1 {
            margin: 0;
            font-size: 2rem;
        }

        .profile-details {
            padding: 30px;
        }

        .detail {
            margin-bottom: 20px;
        }

        .detail label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        .detail span {
            color: #555;
        }

        .edit-button {
            text-align: center;
            margin-top: 30px;
        }

        .edit-button button {
            background-color: #f48fb1;
            color: #333;
            border: none;
            border-radius: 5px;
            padding: 12px 20px;
            font-size: 1.1rem;
            cursor: pointer;
            transition: background-color 0.4s;
            outline: none;
        }

        .edit-button button:hover {
            background-color: #7a5d8c;
        }
    </style>
<body>
<jsp:include page="header.jsp"/> 
    <div class="profile-container">
        <div class="profile-header">
            <h1>User's Profile</h1>
        </div>
        <div class="profile-details">
            <div class="detail">
                <label>Username:</label>
                <span><%= profileUser.getUsername()%></span> 
            </div>
            <div class="detail">
                <label>Full Name:</label>
                <span><%= profileUser.getFullname()%></span>
            </div>
            <div class="detail">
                <label>Email:</label>
                <span><%=profileUser.getEmail()%></span>
            </div>
            <div class="detail">
                <label>Address:</label>
                <span><%=profileUser.getAddress()%></span>
            </div>
        </div>
       <form action="${pageContext.request.contextPath}/ModifyUserServlet" method="post">
                <input type="hidden" id="user_name" name="user_name" value="<%= profileUser.getUsername()%>">
                <input type="hidden" id="full_name" name="full_name" value="<%= profileUser.getFullname() %>">
                <input type="hidden" id="email" name="email" value="<%= profileUser.getEmail() %>">
                <input type="hidden" id="address" name="address" value="<%= profileUser.getAddress() %>">
                <div class="edit-button">
                    <button type="submit">Update</button>
                </div>
            </form>
    </div>
</body>
</html>