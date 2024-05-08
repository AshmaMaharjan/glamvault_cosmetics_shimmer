package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.GlamVaultDBController;
import model.UserMakeupModel;

/**
 * Servlet implementation class AlterUserServlet
 */
@WebServlet("/AlterUserServlet")
public class AlterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GlamVaultDBController dbController;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterUserServlet() {
        super();
        dbController = new GlamVaultDBController();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve updated values from the form
        String UserName = request.getParameter("user_name");
        String FullName = request.getParameter("full_name");
        String Email = request.getParameter("email");
        String Address = request.getParameter("address");

        // Update the database with the new values
        UserMakeupModel alteredUser = new UserMakeupModel();
        alteredUser.setUsername(UserName);
        alteredUser.setFullname(FullName);
        alteredUser.setEmail(Email);
        alteredUser.setAddress(Address);

        // Update the user profile in the database
        int result = dbController.alterProfile(alteredUser);

        // Redirect back to the original JSP page with success or error message
        if (result == 1) {
            response.sendRedirect(request.getContextPath() + "/pages/profile.jsp?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/profile.jsp?error=true");
        }
    }

}