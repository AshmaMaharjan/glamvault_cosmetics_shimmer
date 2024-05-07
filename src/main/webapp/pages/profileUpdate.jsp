<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Roboto', sans-serif; 
}

body {
  min-height: 100vh;
  width: 100%;
  background: #f5f5f5; /* Light Gray */
  display: flex;
  justify-content: center;
  align-items: center;
}

.update_profile {
  background: #F7EEED; /* Light Pastel Pink */
  border-radius: 15px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  padding: 2rem;
  width: 100%;
  max-width: 400px;
}

.update_profile h2 {
  font-size: 2rem;
  font-weight: 500;
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-group label {
  display: block;
  font-size: 1.1rem;
  color: #555; /* Dark Gray */
}

.form-group input {
  height: 50px;
  width: 100%;
  padding: 0 15px;
  font-size: 1rem;
  border: 1px solid #ddd; /* Light Gray */
  border-radius: 6px;
  outline: none;
}

.form-group input:focus {
  box-shadow: 0 1px 0 rgba(0, 0, 0, 0.2);
}

.form-group input[type="submit"] {
  color: #fff;
  background: #f48fb1; /* Darker Purple */
  font-size: 1.2rem;
  font-weight: 500;
  letter-spacing: 1px;
  padding: 12px 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: 0.4s;
}

.form-group input[type="submit"]:hover {
  background: #e23d74; /* Darker shade of purple */
}


</style>
<body>
    <div class="update_profile">
        <h2>Update Profile</h2>
       <form action="${pageContext.request.contextPath}/AlterUserServlet" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="user_name" name="user_name" value="${user_name}" readonly>
            </div>
            <div class="form-group">
                <label for="fullname">Full Name:</label>
                <input type="text" id="full_name" name="full_name" value="${full_name}" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${email}" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${address}" required>
            </div>
            <div class="form-group">
                <input type="submit" value="Done">
            </div>
        </form>
    </div>
</body>
</html>