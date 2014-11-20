package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.LoginRemote;


/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_BEAN_SESSION_KEY 
    = "user";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		//Boolean logged = (Boolean) session.getAttribute("logged");
		//String username = (String) session.getAttribute("username");
		
		
		LoginRemote user = 
			      (LoginRemote) request.getSession()
			        .getAttribute(USER_BEAN_SESSION_KEY);
		
		request.getSession().setAttribute("username", user.getUsername());
		request.getSession().setAttribute("returnMessage", "Online");

		
		if (!user.getLogged()) {

			response.sendRedirect("/Proj2-Web/Start");

		} else {
			request.getRequestDispatcher("/Main.jsp")
					.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
