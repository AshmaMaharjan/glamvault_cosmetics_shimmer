package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.GlamVaultDBController;
import model.MakeupModel;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GlamVaultDBController dbController;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        dbController = new GlamVaultDBController();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteMakeupIdString = request.getParameter("makeupId");
        //String updateIdString = request.getParameter("updateId");

        if (deleteMakeupIdString != null && !deleteMakeupIdString.isEmpty()) {
            int MakeupId = Integer.parseInt(deleteMakeupIdString);
            dbController.deleteMakeup(MakeupId);
            System.out.println("Deleted");
        }
        ArrayList<MakeupModel> makeupGlams = dbController.getAllMakeup();
		request.setAttribute("makeupGlams", makeupGlams);
		request.getRequestDispatcher("/pages/productAdmin.jsp").forward(request, response);
	}

}
