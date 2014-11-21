package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.News;
import ejb.NewsHandlerRemote;

@WebServlet("/topicNews")
public class topicNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	NewsHandlerRemote newsHandler;
    
    public topicNews() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String topic = request.getParameter("topic");
		
		List<News> list = newsHandler.getNewsByTopic(topic);
		
		request.getRequestDispatcher("/US.jsp")
		.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
