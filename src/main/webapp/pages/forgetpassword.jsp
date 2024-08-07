<%@ page import="util.stringUtil" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/forgetpassword.css" />
</head>
<body>
  <div class="Wrapper">
    <form action="/GlamVault_Cosmetics_Shimmer/Forgetpassword" method="POST" >
      <h2>Forget Password</h2>
      <div class="ControlForm">
        <input type="text" id="username" name="<%=stringUtil.Username %>" required>
        <label for="username">Enter your username</label>
      </div>
      <div class="ControlForm">
        <input type="password" id="newPassword" name="<%=stringUtil.Password %>" required>
        <label for="newPassword">Enter your new password</label>
      </div>
      <div class="ControlForm">
        <input type="password" id="confirmPassword" name="<%=stringUtil.LatestPassword %>" required>
        <label for="confirmPassword">Confirm your new password</label>
      </div>
      <button type="submit">Reset</button>
    </form>
  </div>
</body>
</html>