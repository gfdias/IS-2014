package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.LoginRemote;
import ejb.NewsHandlerRemote;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_BEAN_SESSION_KEY 
    = "user";
	
	@EJB
	NewsHandlerRemote newsHandler;

	public Main() {
		super();
	}

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	
		
		LoginRemote user = 
			      (LoginRemote) request.getSession()
			        .getAttribute(USER_BEAN_SESSION_KEY);
		
		request.getSession().setAttribute("username", user.getUsername());
		
		System.out.println("LOGGED " + user.getLogged());
		
		if (user.getLogged() == false) {

			response.sendRedirect("/Proj2-Web/Start");

		} else {
			
			List<data.News> list = newsHandler.getRecentNews();
			
			System.out.println("CARALHO!!!!!! " + list.size());
			
			
			request.setAttribute("recentNews", newsHandler.getRecentNews());
			request.getRequestDispatcher("/Main.jsp")
					.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
