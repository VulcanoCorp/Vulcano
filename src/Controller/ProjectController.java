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

    public Project getProject(int projectId) throws Exception{
        try(ProjectDAO projectDAO = new ProjectDAO()) {
            return projectDAO.getProjectById(projectId);
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

    public void deleteProject(int projectId) throws Exception{
        try(ProjectDAO projectDAO = new ProjectDAO()) {
            projectDAO.deleteProject(projectId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ArrayList<Project> getAllProjects() throws Exception{
        try(ProjectDAO projectDAO = new ProjectDAO()) {
            return projectDAO.getAllProjects();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
