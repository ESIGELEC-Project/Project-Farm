package model.db;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Owner;
import model.Project;
import model.db.exception.DatabaseAccessError;

public class ProjectDB {

	private static Map<String, Project> projects;
	static {
		projects = DBUtil.queryProjectDB();
	}

	public static Boolean saveProject(Project project) throws SQLException {
		return DBUtil.addProject(project);
	}
	
	public static Project getProject(String acronym) throws DatabaseAccessError {
		return projects.get(acronym);
	}

	public static List<Project> getProjectsOfOwner(Owner owner) throws DatabaseAccessError {

		List<Project> projectsOfOwner = new LinkedList<Project>();

		for (Project p : projects.values()) {
			if (p.getOwner().equals(owner)) {
				projectsOfOwner.add(p);
			}
		}
		return projectsOfOwner;
	}
	
	public static List<Project> getAllProjects() throws DatabaseAccessError {
		return new LinkedList<>(projects.values());
	}
}
