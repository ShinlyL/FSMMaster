package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import bean.User;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		Admin admin = null;
		user = (User) request.getSession().getAttribute("userInfo");
		admin = (Admin) request.getSession().getAttribute("adminInfo");
		if(user != null) {
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}else if(admin != null){
			request.getRequestDispatcher("/WEB-INF/page/admin/adminIndex.jsp").forward(request, response);
		}
		
	}

}
