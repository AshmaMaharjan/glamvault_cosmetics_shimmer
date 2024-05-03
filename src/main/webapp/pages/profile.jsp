<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFE6EE;
            margin: 0;
            padding: 0;
        }

        .profile-container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #ffffff;
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            padding: 30px 20px; /* Increased padding */
        }

        .profile-header {
            background-color: #FFB6C1;
            color: #fff;
            padding: 20px;
            text-align: center;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
        }

        .profile-header h1 {
            margin: 0;
            font-size: 2rem;
        }

        .profile-details {
            padding: 30px;
        }

        .detail {
            margin-bottom: 20px;
        }

        .detail label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        .detail span {
            color: #555;
        }

        .edit-button {
            text-align: center;
            margin-top: 30px;
        }

        .edit-button button {
            background-color: #FFB6C1;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 12px 20px;
            font-size: 1.1rem;
            cursor: pointer;
            transition: background-color 0.4s;
            outline: none;
        }

        .edit-button button:hover {
            background-color: #7a5d8c;
        }
    </style>
<body>
    <div class="profile-container">
        <div class="profile-header">
            <h1>User's Profile</h1>
        </div>
        <div class="profile-details">
            <div class="detail">
                <label>Username:</label>
                <span>Asshh</span>
            </div>
            <div class="detail">
                <label>Full Name:</label>
                <span>Ashma Maharjan</span>
            </div>
            <div class="detail">
                <label>Email:</label>
                <span>ashma@gmail.com</span>
            </div>
            <div class="detail">
                <label>Address:</label>
                <span>chakupat</span>
            </div>
        </div>
        <div class="edit-button">
            <button onclick="window.location.href='../newww/profileUpdate.html'">Edit Profile</button>
        </div>
    </div>
</body>
</html>