package controller.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.GlamVaultDBController;
import model.MakeupModel;
import util.stringUtil;


@WebFilter(urlPatterns = {"/pages/productAdmin.jsp", "/pages/dashboard.jsp"})
public class AuthenticationFilter implements Filter {
	private GlamVaultDBController dbController;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		dbController = new GlamVaultDBController();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Cast the request and response to HttpServletRequest and HttpServletResponse
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean SignedIn = session != null && session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn");
       
        String user_Role = session != null ? (String) session.getAttribute(stringUtil.role) : null;

      

        
        if (SignedIn && "admin".equals(user_Role)) {
           
        	if (httpRequest.getRequestURI().endsWith("/pages/productAdmin.jsp")) {
                
        		ArrayList<MakeupModel> makeupGlams = dbController.getAllMakeup();
				request.setAttribute("makeupGlams", makeupGlams);
            }

            chain.doFilter(request, response);
        } else {
           
            System.out.println("Admin access denied!"); // Add logging
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/pages/login.jsp");
        }
    }
	

	@Override
	public void destroy() {
	}
}
