package controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UserName = request.getParameter("user_name");
        String FullName = request.getParameter("full_name");
        String Email = request.getParameter("email");
        String Address = request.getParameter("address");

        // Set attributes to forward to the JSP
        request.setAttribute("user_name", UserName);
        request.setAttribute("full_name", FullName);
        request.setAttribute("email", Email);
        request.setAttribute("address", Address);

        // Forward to the JSP for editing
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/profileUpdate.jsp");
        dispatcher.forward(request, response);
    }

}
