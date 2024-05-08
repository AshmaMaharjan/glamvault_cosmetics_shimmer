<%@ page import="util.stringUtil" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/login.css" />
</head>
<body>
  <div class="Wrapper">
    <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
      <h2>Login</h2>
      <div class="input_wrapper">
        <input type="text" required id="username" name="<%=stringUtil.User_name %>">
        <label for="username">Enter your username</label>
      </div>
      <div class="input_wrapper">
        <input type="password" required id="password" name="<%=stringUtil.Password %>">
        <label for="password">Enter your password</label>
      </div>
      <div class="forget">
        <label for="RememberMe">
          <input type="checkbox" id="RememberMe" name="RememberMe">
          <p>Remember me</p>
        </label>
        <a href="forgetpassword.jsp">Forget password?</a>
      </div>
      <button type="submit" id="loginButton">Log In</button>
      <div class="register">
        <p>Don't have an account? <a href="register.jsp" id="registerLink">Register</a></p>
      </div>
    </form>
  </div>
</body>
</html>