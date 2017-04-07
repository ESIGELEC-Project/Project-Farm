package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Owner;
import model.Project;
import model.db.*;

@WebServlet("/RequestMyProjectListServlet")
public class RequestMyProjectListServlet extends HttpServlet{
	private static final long serialVersionUID = -2365313388507499732L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession(true);
		String email = session.getAttribute("email").toString() ;//this will be change to get from session
		String name = session.getAttribute("name").toString();//this will be change to get from session
		String password = session.getAttribute("password").toString();//this will be change to get from session
		Owner o = new Owner(email,name,password);
		try{
			List<Project> projectList = ProjectDB.getProjectsOfOwner(o);
			session.setAttribute("myProjects", projectList);
			RequestDispatcher dis = req.getRequestDispatcher("/my_projects.jsp");
			dis.forward(req, res);
		}catch(Exception e){
			out.println(e.getMessage());
		}
	}

}
