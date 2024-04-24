<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/productAdmin.css" />
</head>
<body>
  <header>
    <h1>Add Product</h1>
  </header>
  <div class="Content_Wrapper">
    <form action="${pageContext.request.contextPath}/productAdmin" method="POST" enctype="multipart/form-data">
      <div class="groupForm">
        <label for="product-name">Product Name:</label>
        <input type="text" id="product-name" name="product-name" required>
      </div>
      
      <div class="groupForm">
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" >
      </div>
      <div class="groupForm">
        <label for="image">Product Image:</label>
        <input type="file" id="image" name="image" accept="image/*" required>
      </div>
      <button type="submit">Add Product</button>
    </form>
    
    <h2>Product List</h2>
    <table>
      <thead>
        <tr>
          <th>Product Name</th>
          <th>Description</th>
          <th>Price</th>
          <th>Image</th>
        </tr>
      </thead>
      <tbody>
        <!-- This is where you can dynamically populate product data from your database -->
        <tr>
          <td>Product 1</td>
          <td>Description of Product 1</td>
          <td>$19.99</td>
          <td><img src="product1.jpg" alt="Product 1"></td>
        </tr>
        <tr>
          <td>Product 2</td>
          <td>Description of Product 2</td>
          <td>$29.99</td>
          <td><img src="product2.jpg" alt="Product 2"></td>
        </tr>
        <!-- Add more rows as needed -->
      </tbody>
    </table>
  </div>
</body>
</html>