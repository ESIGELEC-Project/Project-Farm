package controller;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Owner;
import model.Project;
import model.db.*;
/**
 * Servlet implementation class AddProjectServlet
 */
@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		try{
			String acronym = req.getParameter("acronym");
			String description = req.getParameter("description");
			String funingDuration_str = req.getParameter("duration");
			String budget_str = req.getParameter("budget");
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			String created = df.format(new Date());
			
			String category = req.getParameter("category");
			
			String owner = req.getSession().getAttribute("email").toString();
			Owner o = UserDB.getOwner(owner);
			model.Category cat = CategoryDB.getCategory(category);
			Project project = new Project(acronym,description,Integer.parseInt(funingDuration_str)
					,Double.parseDouble(budget_str),o,cat);
			ProjectDB.saveProject(project);
//			out.println("<script type=\"text/javascript\">");
//			out.println("alert(\"save project successfully!\");");
//			out.println("window.location=\"/ProjectFarm/RequestMyProjectListServlet\";");
//			out.println("</script>");
			RequestDispatcher dis = req.getRequestDispatcher("/my_projects.jsp");
			dis.forward(req, res);
		}catch(Exception e){
			out.println(e.getMessage());
		}
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
