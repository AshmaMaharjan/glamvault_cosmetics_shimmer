<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/productAdmin.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<header>
		<h1>Add Product</h1>
	</header>
	<div class="Content_Wrapper">
		<form action="${pageContext.request.contextPath}/productAdmin"
			method="POST" enctype="multipart/form-data">
			<div class="groupForm">
				<label for="product-name">Product Name:</label> <input type="text"
					id="product-name" name="product-name" required>
			</div>

			<div class="groupForm">
				<label for="price">Price:</label> <input type="number" id="price"
					name="price">
			</div>
			<div class="groupForm">
				<label for="image">Product Image:</label> <input type="file"
					id="image" name="image" accept="image/*" required>
			</div>
			<button type="submit">Add Product</button>
		</form>

		<h2>Product List</h2>
		<table>
			<thead>
				<tr>
					<th>ID_Makeup </th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Image</th>
				</tr>
			</thead>
			<tbody>
				<!-- This is where you can dynamically populate product data from your database -->
				<c:if test="${empty makeupGlams}">
					<tr>
						<td colspan="3">No products found.</td>
					</tr>
				</c:if>
				<c:forEach var="makeupGlam" items="${makeupGlams}">
			    	<tr>
			        <td>${makeupGlam.makeupID}</td>
			        <td>${makeupGlam.makeupName}</td>
			        <td>${makeupGlam.price}</td>
			        <td><img src="${pageContext.request.contextPath}/resources/user/${makeupGlam.productImage}" height="100" alt="product image"></td>
					<td>
			            <form action="${pageContext.request.contextPath}/EditMakeupServlet" method="post">
                                        <input type="hidden" id="makeup_Id" name="makeup_Id" value="${makeupGlam.makeupID}">
                                        <input type="hidden" id="makeup_Name" name="makeup_Name" value="${makeupGlam.makeupName}">
                                        <input type="hidden" id="price" name="price" value="${makeupGlam.price}">
                                     
                                        <button type="submit" class="edit-btn">Edit</button>
                                    </form>
                                    
                                    <form action="${pageContext.request.contextPath}/DeleteProductServlet" method="post">
								    <input type="hidden" id="makeupId" name="makeupId" value="${makeupGlam.makeupID}">
								    <button type="submit" class="delete-btn">Delete</button>
								</form>
								                                    
                                    
			        	</td>
			    	</tr>
				</c:forEach>
			</tbody>
			</tbody>
		</table>
	</div>
</body>
</html>