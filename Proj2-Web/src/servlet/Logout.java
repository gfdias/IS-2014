package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.LoginRemote;


@WebServlet("/Logout")
//@EJB(name="LoginRemote", beanInterface = LoginRemote.class)
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_BEAN_SESSION_KEY 
    = "user";
	
	

	static String message;
	static RequestDispatcher rd;
       
    
    public Logout() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		LoginRemote user = 
			      (LoginRemote) request.getSession()
			        .getAttribute(USER_BEAN_SESSION_KEY);
		
		String usercenas = user.getUsername();
		
		
		user.logout(usercenas);
		
		request.getSession(false);
		message = "Logged out Successfully";
		request.setAttribute("returnMessage", message);
		rd=getServletContext().getRequestDispatcher("/Login.jsp?returnMessage=message");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
