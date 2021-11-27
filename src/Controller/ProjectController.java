package Controller;

import DAO.ProjectDAO;
import Model.Project;

import java.util.ArrayList;

public class ProjectController {

    public Project createProject(Project project) throws Exception{
        try(ProjectDAO projectDAO = new ProjectDAO()) {
            return projectDAO.createProject(project);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Project getProject(Project project) throws Exception{
        try(ProjectDAO projectDAO = new ProjectDAO()) {
            return projectDAO.getProjectById(project);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Project updateProject(Project project) throws Exception{
        try(ProjectDAO projectDAO = new ProjectDAO()) {
            return projectDAO.updateProject(project);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void deleteProject(Project project) throws Exception{
        try(ProjectDAO projectDAO = new ProjectDAO()) {
            projectDAO.deleteProject(project);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ArrayList<Project> getAllProjects(Project project) throws Exception{
        try(ProjectDAO projectDAO = new ProjectDAO()) {
            return projectDAO.getAllProjects();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
