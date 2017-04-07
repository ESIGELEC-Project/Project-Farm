package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Evaluation;
import model.Evaluator;
import model.Project;
import model.db.ProjectDB;

@WebServlet("/AddEvaluationServlet")
public class AddEvaluationServlet extends HttpServlet{
	private static final long serialVersionUID = 8041168495481984051L;
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession(true);
		try{
			int attractiveness = Integer.parseInt(req.getParameter("attractiveness"));
			int risk = Integer.parseInt(req.getParameter("risk"));
			String acronym = req.getParameter("acronym");
			
			Evaluation evaluation = new Evaluation((Evaluator)session.getAttribute("evaluator"), attractiveness, risk);
			Project project = ProjectDB.getProject(acronym);
			project.addEvaluation(evaluation);
			
			RequestDispatcher dis = req.getRequestDispatcher("/all_projects.jsp");
			dis.forward(req, res);
			
		}catch(Exception e){
			out.println(e.getMessage());
		}
	}

}
