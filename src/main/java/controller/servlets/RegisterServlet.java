package controller.servlets;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.GlamVaultDBController;
import model.UserMakeupModel;
import util.stringUtil;

@WebServlet(urlPatterns = "/RegisterServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GlamVaultDBController dbController;

    public RegisterServlet() {
        super();
        dbController = new GlamVaultDBController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // You can process the GET request here
        response.getWriter().println("GET request received");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter(stringUtil.User_name);
        System.out.println("Date of birth is null or empty"+username);
        String fullname = request.getParameter(stringUtil.Full_name);
        System.out.println("Date of birth is null or empty"+fullname);
        String email = request.getParameter(stringUtil.Email);
        System.out.println("Date of birth is null or empty"+email);
        String phone_num = request.getParameter(stringUtil.Phone_number);
        System.out.println("Date of birth is null or empty"+phone_num);
        String dobString = request.getParameter(stringUtil.DOBSring);
     // Initialize dob variable
     // Initialize dob variable
        LocalDate dob;

        // Check if dobString is null or empty
        if (dobString == null || dobString.isEmpty()) {
            System.out.println("Date of birth is null or empty");
            response.sendRedirect(request.getContextPath() + "/pages/register.html?error=invalid_date_format");
            return; // Exit the method to prevent further processing
        }

        // Define the date format using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Parse the dobString into a LocalDate object using the specified formatter
            dob = LocalDate.parse(dobString, formatter);
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            System.out.println("Error parsing date: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/pages/register.html?error=invalid_date_format");
            return; // Exit the method to prevent further processing
        }





        String address = request.getParameter(stringUtil.Address);
        System.out.println("Date of birth is null or empty"+address);
        String password = request.getParameter(stringUtil.Password);
        System.out.println("Date of birth is null or empty"+password);
        String gender = request.getParameter("gender");
        System.out.println("Date of birth is null or empty"+gender);
        Part user_image = request.getPart("user_image");
        System.out.println("Date of birth is null or empty"+user_image);

        if (!isValidName(fullname)) {
            String errorMessage = "Invalid Full name. Please don't enter symbols and numerical value.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
//        
        if (username.length() < 6) {
            String errorMessage = "Invalid User name. Please enter more than 6 characters";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (!username.matches("^[a-zA-Z0-9]{6,}$")) {
            String errorMessage = "Invalid User name. Please don't enter symbols.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (dob.isAfter(LocalDate.now())) {
            request.setAttribute(stringUtil.MESSAGE_ERROR, "Invalid birthday date.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (phone_num.length() != 14) {
            request.setAttribute(stringUtil.MESSAGE_ERROR, "Invalid number. Phone Number must be of 14 characters.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (!phone_num.startsWith("+")) {
            request.setAttribute(stringUtil.MESSAGE_ERROR, "Invalid number. Phone Number must start with + sign.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
        if (!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{6,}$")) {
            request.setAttribute(stringUtil.MESSAGE_ERROR,
                    "Invalid password. Password must contain at least one uppercase letter, one number, and one special character.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (password.length() < 6) {
            String errorMessage = "Invalid Password. Please enter more than 6 characters";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
//        
        
        String retypePassword = request.getParameter("confirm-password");
        if (!password.equals(retypePassword)) { 
            String errorMessage = "Password and Retype Password do not match.";
         request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
         request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request,
         response); return; }
         
        if (dbController.isUsernameExists(username)) {
            String errorMessage = "Username already exists. Please choose a different username.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
        
     // Check if email already exists
        if (dbController.isEmailExists(email)) {
            String errorMessage = "Email already exists. Please use a different email address.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        // Check if phone number already exists
        if (dbController.isPhoneNumberExists(phone_num)) {
            String errorMessage = "Phone number already exists. Please use a different phone number.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
        
        
        UserMakeupModel user = new UserMakeupModel(username, fullname, email, phone_num, dob, address, password, gender, user_image, "user");
        
        
        System.out.println("Date of birth"+username );
        System.out.println("Date of birth"+fullname );
        System.out.println("Date of birth"+dob );
        
        String savePath = stringUtil.IMAGE_DIR_SAVE_PATH;
        String fileName = user.getImageUserUrl();
        if (!fileName.isEmpty() && fileName != null)
            user_image.write(savePath + fileName);

        System.out.println(user.getImageUserUrl());
        int result = dbController.NewUserAdd(user);

        if (result == 1) {
            request.setAttribute(stringUtil.MESSAGE_SUCCESS_REGISTER, stringUtil.MESSAGE_SUCCESS_REGISTER);
            response.sendRedirect(request.getContextPath() + stringUtil.PAGE_URL_LOGIN);
        } else if (result == 0) {
            // No rows affected
            request.setAttribute(stringUtil.MESSAGE_ERROR, "Registration failed. Please try again.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
        } else if (result == -1) {
            // Error occurred
            request.setAttribute(stringUtil.MESSAGE_ERROR,
                    "An unexpected error occurred. Please try again later.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
        }
    }
    
    private boolean isValidName(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
}

