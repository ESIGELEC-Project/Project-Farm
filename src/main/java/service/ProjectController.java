package service;

import model.Project;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {

    @RequestMapping("/projects")
    public List<Project> geProjects() throws DatabaseAccessError {
        List<Project> projectList = ProjectDB.getAllProjects();
        return projectList;
    }

    @RequestMapping("/project")
    public Project getProject(@RequestParam(value = "acronym",defaultValue = "null")String acronym) throws DatabaseAccessError {
        return ProjectDB.getProject(acronym);
    }

}
