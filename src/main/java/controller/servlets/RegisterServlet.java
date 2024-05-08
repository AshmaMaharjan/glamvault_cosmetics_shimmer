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
     
        LocalDate dob;

       
        if (dobString == null || dobString.isEmpty()) {
            System.out.println("Date of birth is null or empty");
            response.sendRedirect(request.getContextPath() + "/pages/register.html?error=invalid_date_format");
          
        }

        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            
            dob = LocalDate.parse(dobString, formatter);
        } catch (DateTimeParseException e) {
            
            System.out.println("Error parsing date: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/pages/register.html?error=invalid_date_format");
            return; 
        }



        String address = request.getParameter(stringUtil.Address);
        System.out.println("Date of birth is null or empty"+address);
        String password = request.getParameter(stringUtil.Password);
        System.out.println("Date of birth is null or empty"+password);
        String gender = request.getParameter("gender");
        System.out.println("Date of birth is null or empty"+gender);
        Part user_image = request.getPart("user_image");
        System.out.println("Date of birth is null or empty"+user_image);

        if (!nameIsValid(fullname)) {
            String errorMessage = "Please provide a full name without symbols or numerical values.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
//        
        if (username.length() < 5) {
            String errorMessage = "Username must be longer than 5 characters.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (!username.matches("^[a-zA-Z0-9]{6,}$")) {
            String errorMessage = "Please enter a username without symbols.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (dob.isAfter(LocalDate.now())) {
            request.setAttribute(stringUtil.MESSAGE_ERROR, "Please enter a valid birthday date.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (phone_num.length() != 10) {
            request.setAttribute(stringUtil.MESSAGE_ERROR, "Please enter a valid phone number. Phone numbers must contain exactly 10 characters.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

       
        if (!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{6,}$")) {
            request.setAttribute(stringUtil.MESSAGE_ERROR,
                    "Please provide a password that contains at least one uppercase letter, one number, and one special character.");
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        if (password.length() < 6) {
            String errorMessage = "Please enter a password that contains more than 6 characters.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
//        
        
        String PasswordConfirmation = request.getParameter("confirm-password");
        if (!password.equals(PasswordConfirmation)) { 
            String errorMessage = "Password and confirm Password doesnot match.";
         request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
         request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request,
         response); return; }
         
        if (dbController.doesUsernameExist(username)) {
            String errorMessage = "The username you have chosen is already in use. Please select a different username.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
        
     // Check if email already exists
        if (dbController.doesEmailExists(email)) {
            String errorMessage = "Email already in use. Choose another.";
            request.setAttribute(stringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(stringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        // Check if phone number already exists
        if (dbController.doesPhoneNumberExists(phone_num)) {
            String errorMessage = "Phone number already in use. Choose another.";
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
    
    private boolean nameIsValid(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
}

