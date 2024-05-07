<%@ page import="util.stringUtil" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/register.css" />

</head>
<body>
  <div class="container">
  <% String error = (String) request.getAttribute(stringUtil.MESSAGE_ERROR); %>
        <% if (error != null && !error.isEmpty()) { %>
           <div style="color: red;"><%= error %></div>
        <% } %>
    <div class="title">Registration</div>
    <div class="content">
      <form action="/GlamVault_Cosmetics_Shimmer/RegisterServlet" method="POST" enctype="multipart/form-data">
        <div class="user-details">
          <!-- Existing input boxes for user details -->
          <!-- Full Name -->
          <div class="input-box">
            <span class="details">Full Name</span>
            <input type="text" placeholder="Enter your name"  required name="<%=stringUtil.Full_name %>" id="full-name">
          </div>
          <!-- Username -->
          <div class="input-box">
            <span class="details">Username</span>
            <input type="text" placeholder="Enter your username" required name="<%=stringUtil.User_name %>" id="username">
          </div>
          <!-- Phone Number -->
          <div class="input-box">
            <span class="details">Phone Number</span>
            <input type="text" placeholder="Enter your number" required name="<%=stringUtil.Phone_number %>" id="phone-number">
          </div>
          <!-- Email -->
          <div class="input-box">
            <span class="details">Email</span>
            <input type="text" placeholder="Enter your email" required name="<%=stringUtil.Email %>" id="email">
          </div>
          <!-- Date of Birth -->
          <div class="input-box">
            <span class="details">Date of Birth</span>
            <input type="date" required id="date-of-birth" name="<%=stringUtil.DOBSring %>" name="dob">

          </div>
          <!-- Address -->
          <div class="input-box">
            <span class="details">Address</span>
            <input type="text" placeholder="Enter your address" required name="<%=stringUtil.Address %>" id="address">
          </div>
          <!-- Password -->
          <div class="input-box">
            <span class="details">Password</span>
            <input type="password" placeholder="Enter your password" required name="<%=stringUtil.Password %>" id="password">
          </div>
          <!-- Confirm Password -->
          <div class="input-box">
            <span class="details">Confirm Password</span>
            <input type="password" placeholder="Confirm your password" required name="<%=stringUtil.retype_password %>" id="confirm_password">
          </div>
          
          <!-- New input box for image -->
          <div class="input-box">
            <span class="details">Profile Image</span>
            <input type="file" accept="image/*" id="user_image" name="user_image">
          </div>
        </div>
        
        <!-- Gender -->
        <div class="gender-details">
          <input type="radio" name="gender" id="dot-1">
          <input type="radio" name="<%=stringUtil.gender %>"  id="dot-2">
          <input type="radio" name="<%=stringUtil.gender %>"  id="dot-3">
          <span class="gender-title">Gender</span>
          <div class="category">
            <label for="dot-1">
              <span class="dot one"></span>
              <span class="gender">Male</span>
            </label>
            <label for="dot-2">
              <span class="dot two"></span>
              <span class="gender">Female</span>
            </label>
          </div>
        </div>
        
        <!-- Submit Button -->
        <div class="button">
          <input type="submit" value="Register">
        </div>
      </form>
    </div>
  </div>
</body>
</html>
