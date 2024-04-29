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
    <form action="/GlamVault_Cosmetics_Shimmer/ForgetpasswordServlet" method="POST" >
      <h2>Forget Password</h2>
      <div class="ControlForm">
        <input type="text" id="phoneNumber" name="phoneNumber" required>
        <label for="phoneNumber">Enter your phone number</label>
      </div>
      <div class="ControlForm">
        <input type="password" id="newPassword" name="newPassword" required>
        <label for="newPassword">Enter your new password</label>
      </div>
      <div class="ControlForm">
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <label for="confirmPassword">Confirm your new password</label>
      </div>
      <button type="submit">Reset</button>
    </form>
  </div>
</body>
</html>