package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Author;
import data.Client;
import data.News;
import data.Photo;
import ejb.NewsHandlerRemote;


@WebServlet("/SearchAuthor")
public class SearchAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	NewsHandlerRemote newsHandler;
	
    public SearchAuthor() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<Author> authors=newsHandler.getAllAuthors();
		 request.setAttribute("Authors", authors);
			request.getRequestDispatcher("/Authors.jsp")
					.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

}
