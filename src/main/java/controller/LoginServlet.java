package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.UserDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			if(UserDB.getUser(email) != null){//if the email exist
				boolean isLogin = UserDB.checkLogin(email, password);
				//UserDB.checkConnected();
				String name = UserDB.getUser(email).getName();
				if(isLogin){
					session.setAttribute("email", email);
					session.setAttribute("name", name);
					session.setAttribute("password", password);
					RequestDispatcher dis = req.getRequestDispatcher("/index.jsp");
					dis.forward(req, resp);
				}else{
					req.setAttribute("error", "password error");
					RequestDispatcher dis = req.getRequestDispatcher("/index.jsp");
					dis.forward(req, resp);
				}
			}else{
				req.setAttribute("error","email error");
				RequestDispatcher dis = req.getRequestDispatcher("/index.jsp");
				dis.forward(req, resp);
			}
		} catch (DatabaseAccessError e) {
			req.setAttribute("error","database error");
			RequestDispatcher dis = req.getRequestDispatcher("/index.jsp");
			dis.forward(req, resp);
			
		}
	}
}
