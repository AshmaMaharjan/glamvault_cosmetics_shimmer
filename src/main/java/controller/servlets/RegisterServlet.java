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
import model.UserModel;
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
        String user_name = request.getParameter(stringUtil.User_name);
        System.out.println("Date of birth is null or empty"+user_name);
        String full_name = request.getParameter(stringUtil.Full_name);
        System.out.println("Date of birth is null or empty"+full_name);
        String email = request.getParameter(stringUtil.Email);
        System.out.println("Date of birth is null or empty"+email);
        String phone_number = request.getParameter(stringUtil.Phone_number);
        System.out.println("Date of birth is null or empty"+phone_number);
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

        
        
        UserModel user = new UserModel(user_name, full_name, email, phone_number, dob, address, password, gender, user_image, "user");
        
        
        System.out.println("Date of birth"+user_name );
        System.out.println("Date of birth"+full_name );
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
    
//    private boolean isValidName(String name) {
//        for (char c : name.toCharArray()) {
//            if (!Character.isLetter(c) && c != ' ') {
//                return false;
//            }
//        }
//        return true;
//    }
}


