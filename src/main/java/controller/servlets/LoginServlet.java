package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.GlamVaultDBController;
import model.LoginModel;
import model.Loginresult;
import util.stringUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GlamVaultDBController dbController;

    public LoginServlet() {
        super();
        dbController = new GlamVaultDBController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String user_name = request.getParameter(stringUtil.User_name);
        String password = request.getParameter(stringUtil.Password);

        // Creating a LoginModel object with user input
        LoginModel loginModel = new LoginModel(user_name, password);

        // Calling the method to retrieve login information from the database
        Loginresult loginResult = dbController.getStudLoginInfo(loginModel);
        

        // Checking the status returned by the database operation
        if (loginResult.getStatus() == 1) 
        {
        	System.out.println("success");
            // Successful login
            if ("admin".equals(loginResult.getRole())) {
            	System.out.println("Admin"+loginResult);
                // If the user is an admin, redirect to the admin dashboard
                response.sendRedirect(request.getContextPath() + "/pages/dashboard.jsp");
            } else {
            	System.out.println("user");
                // If the user is not an admin, redirect to the home page
                response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            }
            // Set a success message attribute
            request.setAttribute(stringUtil.MESSAGE_SUCCESS, stringUtil.MESSAGE_SUCCESS_LOGIN);
        } else if (loginResult.getStatus() == 0) {
        	System.out.println("username or password");
            // Username or password mismatch
            // Set an error message attribute indicating the user should create a new account
            request.setAttribute(stringUtil.MESSAGE_ERROR, "Invalid email or password. Create a new account.");
            // Forward the request to the login page
            request.getRequestDispatcher(stringUtil.PAGE_URL_LOGIN).forward(request, response);
        } else if (loginResult.getStatus() == -1) {
        	System.out.println("username not found");
            // Username not found
            // Set an error message attribute indicating the user should create a new account
            request.setAttribute(stringUtil.MESSAGE_ERROR, "Username not found. Create a new account.");
            // Forward the request to the login page
            request.getRequestDispatcher(stringUtil.PAGE_URL_LOGIN).forward(request, response);
        } else {
        	System.out.println("");
            // Internal server error
            // Set an error message attribute indicating an internal server error
            request.setAttribute(stringUtil.MESSAGE_ERROR, stringUtil.MESSAGE_ERROR_SERVER);
            // Forward the request to the login page
            request.getRequestDispatcher(stringUtil.PAGE_URL_LOGIN).forward(request, response);
        }
    }
}

