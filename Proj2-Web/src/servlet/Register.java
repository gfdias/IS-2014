package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.RegisterClientRemote;
import data.Client;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	static String message;
	static RequestDispatcher rd;

	@EJB
	RegisterClientRemote registerRemote;

	public Register() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		message = "Enter registry info";
		request.setAttribute("returnMessage", message);
		rd = getServletContext().getRequestDispatcher(
				"/Register.jsp?returnMessage=message");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			Client user = new Client(request.getParameter("username"),
					request.getParameter("email"),
					request.getParameter("password"), 0);
			
			if(request.getParameter("username").isEmpty() || request.getParameter("email").isEmpty() || request.getParameter("password").isEmpty()){
				message = "Empty fields";
				request.setAttribute("returnMessage", message);
				rd = getServletContext().getRequestDispatcher(
						"/Register.jsp?returnMessage=message");
				rd.forward(request, response);
			}
			
			else if (user.getPassword().compareTo(request.getParameter("password2")) != 0) {
				message = "Passwords don't match";
				request.setAttribute("returnMessage", message);
				rd = getServletContext().getRequestDispatcher(
						"/Register.jsp?returnMessage=message");
				rd.forward(request, response);
			}
			
			

			else {

				boolean exists = registerRemote.verify(user.getClientname(),
						user.getPassword());

				if (!exists) {
					
					registerRemote.register(user.getClientname(),user.getEmail(), user.getPassword());
					message = "Registry Successful!";
				}

				else {

					message = "Username already in use";
				}

				request.setAttribute("returnMessage", message);
				rd = getServletContext().getRequestDispatcher(
						"/Register.jsp?returnMessage=message");
				rd.forward(request, response);
			}
		} catch (Throwable exception) {
			System.out.println(exception);
		}

	}
}
