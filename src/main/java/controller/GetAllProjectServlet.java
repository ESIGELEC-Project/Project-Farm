package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import model.db.ProjectDB;

@WebServlet("/GetAllProjectServlet")
public class GetAllProjectServlet extends HttpServlet{
	private static final long serialVersionUID = 5850055388534480204L;
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		try{
			List<Project> projectList = ProjectDB.getAllProjects();
			for(int i=0;i<projectList.size();i++){
				out.println(projectList.get(i));
			}
		}catch(Exception e){
			out.println(e.getMessage());
		}
	}

}
