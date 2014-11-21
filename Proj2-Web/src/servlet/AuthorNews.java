package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.News;
import ejb.NewsHandlerRemote;


@WebServlet("/AuthorNews")
public class AuthorNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@EJB
	NewsHandlerRemote newsHandler;
    public AuthorNews() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> topic = request.getParameterNames();
		String param = null;
		
		while (topic.hasMoreElements()) {
			param = topic.nextElement();
		}
		System.out.println("value "+param);
		
		List<News> a=newsHandler.newsWithAuthor(param);
		 List<String> photos=newsHandler.getOnePhotoPerNews(a);
		 request.setAttribute("news", a);
		 request.setAttribute("photos", photos);

			request.getRequestDispatcher("/Search.jsp")
					.forward(request, response);
			
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
