<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product</title>

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

.update_product {
  background: #FFD1DF; /* Lavender */
  border-radius: 15px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  padding: 2rem;
  width: 100%;
  max-width: 400px;
}

.update_product h2 {
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

/* Center the form content */
.update_product form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* Target the submit button directly */
button[type="submit"] {
  color: #fff;
  background: #d81b60; /* New background color */
  font-size: 1.2rem;
  font-weight: 500;
  letter-spacing: 1px;
  padding: 12px 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.4s; /* Only transition the background color */
  margin-top: 20px; /* Add margin for spacing */
}

/* Hover state */
button[type="submit"]:hover {
  background: #7a5d8c; /* Darker Purple */
}




</style>
</head>
<body>
    <div class="update_product">
        <h2>Update Profile</h2>
        <form action="${pageContext.request.contextPath}/UpdateMakeupServlet" method="post">
            <div class="form-group">
                <label for="makeup_Id">Makeup ID:</label>
                <input type="text" id="makeup_Id" name="makeup_Id" value="${makeup_Id}" readonly>
            </div>
            <div class="form-group">
                <label for="makeup_Name">Makeup Name:</label>
                <input type="text" id="makeup_Name" name="makeup_Name" value="${makeup_Name}" required>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" value="${price}" required >
            </div>
            <div class="form-group">
                <button type="submit">Update</button>
            </div>
        </form>
    </div>
</body>
</html>