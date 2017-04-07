package model.db;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Evaluation;
import model.Owner;
import model.Project;
import model.db.exception.DatabaseAccessError;

public class ProjectDB {

	private static Map<String, Project> projects;

	static {
		//projects = new LinkedHashMap<String, Project>();
		projects = DBUtil.queryProjectDB();
	}
	
	

	public static void saveProject(Project project) throws DatabaseAccessError, SQLException {
		//projects.put(project.getAcronym(), project);
		
		DBUtil.addProject(project);
	}

	
	
	public static Project getProject(String acronym) throws DatabaseAccessError {
		projects = DBUtil.queryProjectDB();
		return projects.get(acronym);
	}

	public static List<Project> getProjectsOfOwner(Owner owner) throws DatabaseAccessError {

		List<Project> projectsOfOwner = new LinkedList<Project>();
		
		projects = DBUtil.getProjectOfOwner(owner);

		for (Project p : projects.values()) {
			if (p.getOwner().equals(owner)) {
				projectsOfOwner.add(p);
			}
		}
		return projectsOfOwner;
	}
	
	public static List<Project> getAllProjects() throws DatabaseAccessError {
		projects = DBUtil.queryProjectDB();
		return new LinkedList<Project>(projects.values());
	}
	
}
