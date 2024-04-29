<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/dashboard.css" />
<!-- <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css"> -->
</head>
<body>
    <nav>
        <div class="logo">
            <div class="logoimg">
                <img src="images/logo.png" alt="">
            </div>
            <span class="logo">GLAMVAULT</span>
        </div>
        <div class="menu">
            <ul class="nav-links">
                
                <li><a href="#">
                    <i class="uil uil-user"></i>
                    <span class="link-name">Profile</span>
                </a></li>
                <li><a href="#">
                    <i class="uil uil-list-ul"></i>
                    <span class="link-name">Product List</span>
                </a></li>
            </ul>
            <ul class="logout-mode">
                <li><a href="#">
                    <i class="uil uil-signout"></i>
                    <span class="link-name">Logout</span>
                </a></li>
                
    </nav>
    <section class="dashboard">
        <div class="top">
            <i class="uil uil-bars sidebar-toggle"></i>
            <div class="search-box">
                <i class="uil uil-search"></i>
                <input type="text" placeholder="Search here...">
            </div>
        </div>
        <div class="dash-content">
            <div class="overview">
                <div class="title">
                    <i class="uil uil-tachometer-fast-alt"></i>
                    <span class="text">Dashboard</span>
                </div>
                <div class="boxes">
                    <div class="box box1">
                        <i class="uil uil-thumbs-up"></i>
                        <span class="text">Total Likes</span>
                        <span class="number">10,50,120</span>
                    </div>
                    <div class="box box2">
                        <i class="uil uil-comments"></i>
                        <span class="text">Comments</span>
                        <span class="number">40,120</span>
                    </div>
                    <div class="box box3">
                        <i class="uil uil-share"></i>
                        <span class="text">Total Share</span>
                        <span class="number">10,120</span>
                    </div>
                </div>
            </div>
            <div class="activity">
                <div class="title">
                    <i class="uil uil-clock-three"></i>
                    <span class="text">Recent Activity</span>
                </div>
                <div class="activity-data">
                    <div class="data names">
                        <span class="data-title">Id</span>
                        <span class="data-list">102</span>
                        <span class="data-list">230</span>
                        <span class="data-list">302</span>
                        <span class="data-list">900</span>
                        <span class="data-list">120</span>
                        <span class="data-list">401</span>
                        <span class="data-list">301</span>
                    </div>
                    <div class="data email">
                        <span class="data-title">Product Name</span>
                        <span class="data-list">Foundation</span>
                        <span class="data-list">Eye Shadow</span>
                        <span class="data-list">Lipstick</span>
                        <span class="data-list">Eye Liner</span>
                        <span class="data-list">Blush</span>
                        <span class="data-list">Highlighter</span>
                        <span class="data-list">Mascara</span>
                    </div>
                    <div class="data joined">
                        <span class="data-title">Price</span>
                        <span class="data-list">$50</span>
                        <span class="data-list">$45</span>
                        <span class="data-list">$35</span>
                        <span class="data-list">$25</span>
                        <span class="data-list">$30</span>
                        <span class="data-list">$30</span>
                        <span class="data-list">$34</span>
                    </div>
                    <div class="data type">
                        <span class="data-title">Brand</span>
                        <span class="data-list">Estee Lauder </span>
                        <span class="data-list">Morphe</span>
                        <span class="data-list">Fenty Beauty</span>
                        <span class="data-list">Maybelline</span>
                        <span class="data-list">Rare Beauty</span>
                        <span class="data-list">Fenty beauty</span>
                        <span class="data-list">charlotte tilbury </span>
                    </div>
                    <div class="data status">
                        <span class="data-title">Color</span>
                        <span class="data-list">Medium Beige</span>
                        <span class="data-list">Pink</span>
                        <span class="data-list">Red</span>
                        <span class="data-list">Black</span>
                        <span class="data-list">Grace</span>
                        <span class="data-list">Pearly</span>
                        <span class="data-list">Black</span>
                    </div>
                    <div class="data status">
                        <span class="data-title">Stock_Availability</span>
                        <span class="data-list">10</span>
                        <span class="data-list">20</span>
                        <span class="data-list">60</span>
                        <span class="data-list">20</span>
                        <span class="data-list">30</span>
                        <span class="data-list">70</span>
                        <span class="data-list">6</span>
                    </div>
                    <div class="data status">
                        <span class="data-title">Size</span>
                        <span class="data-list">M</span>
                        <span class="data-list">S</span>
                        <span class="data-list">L</span>
                        <span class="data-list">M</span>
                        <span class="data-list">S</span>
                        <span class="data-list">s</span>
                        <span class="data-list">M</span>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>