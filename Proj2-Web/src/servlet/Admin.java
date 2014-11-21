package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Client;
import ejb.AdminRemote;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@EJB
	AdminRemote admin;
    public Admin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<Client> clients=admin.getUsers();
		 request.setAttribute("Users", clients);
			request.getRequestDispatcher("/Admin.jsp")
					.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("remove")){
			//delete
			admin.removeUser(request.getParameter("userId"));
			
		}else{
			
			admin.setUser(request.getParameter("userId"),request.getParameter("username") ,  request.getParameter("email"), request.getParameter("password"));

		}
	}

}
