package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.GlamVaultDBController;
import model.MakeupModel;

/**
 * Servlet implementation class UpdateMakeupServlet
 */
@WebServlet("/UpdateMakeupServlet")
public class UpdateMakeupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GlamVaultDBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMakeupServlet() {
        super();
        dbController = new GlamVaultDBController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve updated values from the form
        String MakeupId = request.getParameter("makeup_Id");
        String MakeupName = request.getParameter("makeup_Name");
        String priceparameter = request.getParameter("price");


        
        double price = 0.0;
        try {
            if (priceparameter != null && !priceparameter.isEmpty()) {
                price = Double.parseDouble(priceparameter);
            }
        } catch (NumberFormatException e) {
         
            e.printStackTrace(); 
            response.sendRedirect(request.getContextPath() + "/pages/productAdmin.jsp?error=true");
            return; 
        }

        
        if (MakeupId == null || MakeupId.isEmpty()) {
            
            response.sendRedirect(request.getContextPath() + "/pages/productAdmin.jsp?error=true");
            return; 
        }

     
        int result = dbController.updateMakeup(
        		new MakeupModel(Integer.parseInt(MakeupId),MakeupName,price,null));
	
        		
            

        
        if (result == 1) {
            response.sendRedirect(request.getContextPath() + "/pages/productAdmin.jsp?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/productAdmin.jsp?error=true");
        }
    }

}
