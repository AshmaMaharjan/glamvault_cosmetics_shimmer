<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/contact.css" />
</head>
<body>
<jsp:include page="header.jsp"/> 
  <header>
    <h1>Contact Us</h1>
  </header>
  <main>
    <section class="contact-form">
      <h2>Send us a message</h2>
      <form action="#" method="post">
        <div class="form-group">
          <label for="name">Your Name</label>
          <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
          <label for="email">Your Email</label>
          <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
          <label for="message">Message</label>
          <textarea id="message" name="message" required></textarea>
        </div>
        <button type="submit">Send</button>
      </form>
    </section>
    <section class="contact-info">
      <h2>Contact Information</h2>
      <ul>
        <li>Email: m@example.com</li>
        <li>Phone: 9865546789</li>
        <li>Address: Kathmandu</li>
      </ul>
    </section>
  </main>

</body>
</html>