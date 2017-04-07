package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Project;
import model.db.ProjectDB;

@WebServlet("/GetProjectDetailServlet")
public class GetProjectDetailServlet extends HttpServlet{
	private static final long serialVersionUID = 7088413511272701143L;
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession(true);
		String acronym = req.getParameter("acronym");//since there is no name attribute in table td, we use "servlet?acronym= value" to query the data
		
		try{
			Project project = ProjectDB.getProject(acronym);
			session.setAttribute("project", project);
			RequestDispatcher dis = req.getRequestDispatcher("/add_document.jsp");
			if(!project.equals(null)){
				dis.forward(req, res);
			}
		}catch(Exception e){
			out.println(e.getMessage());
		}
	} 
	
	

}
