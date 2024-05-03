package controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditMakeupServlet
 */
@WebServlet("/EditMakeupServlet")
public class EditMakeupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMakeupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String MakeupId = request.getParameter("makeup_Id");
        String MakeupName = request.getParameter("makeup_Name");
        String price = request.getParameter("price");
       

        // Set attributes to forward to the JSP
        request.setAttribute("makeup_Id", MakeupId);
        request.setAttribute("makeup_Name", MakeupName);
        request.setAttribute("price", price);


        // Forward to the JSP for editing
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/updateProduct.jsp");
        dispatcher.forward(request, response);
    }

}
