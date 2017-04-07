package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.db.UserDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 3311297485258766639L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();

		String login = req.getParameter("login");
		String password = req.getParameter("password");

		boolean loginOk;
		try {
			loginOk = UserDB.checkLogin(login, password);

			if (loginOk) {
				out.println("ok");
			} else
				out.println("nok");
		} catch (DatabaseAccessError e) {
			out.println("Sorry: " + e.getMessage());
		}

	}


}
