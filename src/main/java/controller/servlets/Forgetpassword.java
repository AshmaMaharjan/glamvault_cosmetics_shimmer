package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.GlamVaultDBController;

/**
 * Servlet implementation class Forgetpassword
 */
@WebServlet("/ForgetpasswordServlet")
public class Forgetpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GlamVaultDBController dbController;
	public Forgetpassword() {
        super();
        dbController = new GlamVaultDBController(); // Instantiate GadgetDbController
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phoneNumber = request.getParameter("phoneNumber");
		System.out.println("PhoneNumber"+phoneNumber);
        String newPassword = request.getParameter("newPassword");

        System.out.println("PhoneNumber"+newPassword);
        // Call the updateUserPasswordIfValid method from GadgetDbController
        int result = dbController.modifyPasswordValid(phoneNumber, "", newPassword); // Pass empty string for old password, assuming it's not needed

        if (result == 1) {
            // Password updated successfully
            // Redirect to a success page or show a success message
        } else if (result == -2) {
            // Incorrect old password
            // Redirect to a page showing an error message
        } else if (result == -1) {
            // Username not found
            // Redirect to a page showing an error message
        } else {
            // Error updating password
            // Redirect to a page showing an error message
        }
    }


}


