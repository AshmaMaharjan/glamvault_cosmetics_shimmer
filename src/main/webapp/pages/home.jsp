<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  
 
 <style>
 @charset "ISO-8859-1";
/* Home section styles */
.home {
    width: 100%;
    height: 80vh;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-wrap: wrap;
    
    background-color: #f5f5dc;
    z-index: 1; /* Decrease z-index to send the home section to the back */
}

.home .image img {
    margin-top: 30px;
    width: 100%;
}

.home .main_content {
    flex: 1 1 400px;
    margin-top: 20px;
}

.main_content h1 {
    color: #333; /* Change text color */
    font-weight: bold;
    margin-left: 23px;
    font-size: 55px;
    font-family: 'Arial', sans-serif; /* Change font */
    text-shadow: -1px 1px 1px black;
}

.main_content h1 span {
    color: #333; /* Change text color */
    text-shadow: 1px 1px 1px black;
}

.main_content p {
    margin-left: 23px;
    color: #808080; /* Change text color */
    font-family: 'Arial', sans-serif; /* Change font */
}

.button {
    margin-left: 13px;
}

.button button {
    width: 150px;
    height: 32px;
    letter-spacing: 3px;
    background-color: #AA336A; /* Change button background color */
    color: white;
    border-radius: 5px;
    border: none;
    transition: 0.5s ease;
    cursor: pointer;
    font-family: 'Arial', sans-serif; /* Change font */
}

.button button:hover {
    background-color: #AA336A; /* Change button background color on hover */
    color: black;
    border: none;
}

/* Product cards section styles */
.Wrapper {
    width: 100%;
    padding: 0 20px; /* Adjust padding */
}

.container-box {
    display: flex;
    flex-wrap: wrap; /* Allow cards to wrap to the next line if needed */
    justify-content: center; /* Center align cards horizontally */
    margin: 40px auto; /* Adjust margin for spacing */
}

.unique-box {
    width: 300px; /* Adjust card width */
    height: 450px; /* Adjust card height */
    background: #fff;
    margin: 20px; /* Adjust margin for spacing between cards */
    padding: 20px; /* Adjust padding */
    text-align: center;
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
}

.unique-box .image img {
    width: 100%;
    height: 200px; /* Adjust image height */
    object-fit: cover;
}

.unique-box h3 {
    margin-top: 15px; /* Adjust margin top */
    margin-bottom: 10px; /* Adjust margin bottom */
    font-size: 20px; /* Increase font size */
}

.unique-box p {
    line-height: 1.6; /* Adjust line height */
    font-size: 16px; /* Increase font size */
}

.unique-box a {
    display: inline-block; /* Ensure "Add to Cart" button is visible */
    margin-top: 20px; /* Adjust margin top */
    padding: 10px 20px; /* Adjust padding */
    font-size: 16px; /* Increase font size */
    background-color: #AA336A; /* Change button background color */
    color: white;
    border-radius: 5px;
    text-decoration: none; /* Remove underline */
}

.unique-box a:hover {
    background-color: #AA336A; /* Change button background color on hover */
    color: black;
}
 
 </style>
</head>
<body>
<jsp:include page="header.jsp"/> 
    <!-- home content -->
    <section class="home">
        <div class="main_content">
            <h1> <span>Enhance Your Natural Beauty </span>
                <br>with Every Brushstroke
            </h1>
            <p>
                Here at GlamVault Cosmetics, welcome! Discover your ideal look with us, featuring fascinating eyeshadows and dazzling foundations. <br> With quick shipping and hassle-free returns, you can shop with confidence. Discover the GlamVault distinction right now!
                <br>
            </p>
            <div class="button"><button>Shop Now</button></div>
        </div>
        <div class="image">
            <img src="./images/a.png" alt="">
        </div>
    </section>
    <!-- product cards -->
    <section>
        <div class="Wrapper">
            <div class="container-box">
                <div class="unique-box">
                    <div class="image"><img alt="Blush" src="e.jpg"></div>
                    <h3>Blush</h3>
                    <p>Radiant confidence and blushing beauty</p>
                    <div class="price">
                        <p>$25</p>
                    </div>
                    <a href="#">Add to Cart</a>
                </div>
                <div class="unique-box">
                    <div class="image"><img alt="Eyeshadow" src="f.jpg"></div>
                    <h3>Eyeshadow</h3>
                    <p>Paint your dreams with every shade.</p>
                    <div class="price">
                        <p>$50</p>
                    </div>
                    <a href="#">Add to Cart</a>
                </div>
                <div class="unique-box">
                    <div class="image"><img alt="Foundation" src="g.jpg"></div>
                    <h3>Foundation</h3>
                    <p>Build your confidence, layer by layer.</p>
                    <div class="price">
                        <p>$45</p>
                    </div>
                    <a href="#">Add to Cart</a>
                </div>
            </div>
        </div>
    </section>
</body>
</html>