package servlet;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.LoginRemote;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
@EJB(name="LoginRemote", beanInterface = LoginRemote.class)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_BEAN_SESSION_KEY 
    = "user";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	static String message;
	static RequestDispatcher rd;
	
	LoginRemote loginHandler;

	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		message = "Enter login info";
		request.setAttribute("returnMessage", message);
		rd = getServletContext().getRequestDispatcher(
				"/Login.jsp?returnMessage=message");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
	        //InitialContext ic = new InitialContext();
	        //loginHandler = (LoginRemote) 
	         //ic.lookup("java:jboss/exported/Proj2-EAR/Proj2-EJB/Login!ejb.LoginRemote");
	        
	         loginHandler = (LoginRemote) new InitialContext().lookup("java:comp/env/LoginRemote");

	        
	        request.getSession().setAttribute(
	          USER_BEAN_SESSION_KEY, 
	          loginHandler);
	        
	      } catch (NamingException e) {
	        throw new ServletException(e);
	      }
	 
		
		try {

			
			boolean exists = loginHandler.verify(request.getParameter("username"), request.getParameter("password"));

			if (exists) {
	
				loginHandler.login(request.getParameter("username"), request.getParameter("password"));
				response.sendRedirect("/Proj2-Web/Main");
			}

			else  {

				message = "Invalid credentials";
			}


			request.setAttribute("returnMessage", message);
			rd = getServletContext().getRequestDispatcher(
					"/Login.jsp?returnMessage=message");
			rd.forward(request, response);
		} catch (Throwable exception) {
			System.out.println(exception);
		}

	}

}
