package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.GlamVaultDBController;
import model.product;
import util.stringUtil;

/**
 * Servlet implementation class productAdmin
 */
@WebServlet("/productAdmin")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class productAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GlamVaultDBController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productAdmin() {
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
		String productName = request.getParameter("product-name");
		String priceStr = request.getParameter("price");

		// Ensure that a product name and price are provided
		if (productName == null || productName.trim().isEmpty() || priceStr == null || priceStr.trim().isEmpty()) {
		    // Handle invalid input, perhaps by displaying an error message to the user
		    // For example:
		    request.setAttribute("error", "Product name and price are required.");
		    request.getRequestDispatcher("error.jsp").forward(request, response);
		    return;
		}

		double price;
		try {
		    price = Double.parseDouble(priceStr);
		} catch (NumberFormatException e) {
		    // Handle invalid price format
		    // For example:
		    request.setAttribute("error", "Invalid price format.");
		    request.getRequestDispatcher("error.jsp").forward(request, response);
		    return;
		}


	    Part product_image = request.getPart("image");

        // Create ProductAdmin object
        product product = new product(productName, price,product_image);
        String savePath = stringUtil.IMAGE_DIR_SAVE_PATH;
        String fileName = product.getUserImageUrl();
        if (!fileName.isEmpty() && fileName != null)
        	product_image.write(savePath + fileName);

        System.out.println(product.getUserImageUrl());
       

        // Add product to the database
        int result =dbController.ADDProduct(product);

        if (result > 0) {
         
            request.getRequestDispatcher("pages/Adminproduct.jsp").forward(request, response);

        } else {
         System.out.println("ashma bsdk");
        	// Error adding product, handle accordingly
        }
    }

}
