package DAO;

import Connection.BaseDAO;
import Model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProjectDAO extends BaseDAO{

    public ProjectDAO() throws Exception {super();}

    public Project createProject(Project project) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        try {

            String insert = "INSERT INTO Project VALUE (DEFAULT, ?, ?, ?)";

            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,project.getName());
            stmt.setString(2,project.getDescription());
            stmt.setInt(3,project.getOwner());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                project.setId(rs.getInt(1));
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return project;
    }

    public Project getProjectById(Project project) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        try{
            String select = "SELECT * FROM Project WHERE id = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,project.getId());
            rs = stmt.executeQuery();

            if (rs.next()){
                project.setId(rs.getInt("Id"));
                project.setName(rs.getString("Name"));
                project.setDescription(rs.getString("Description"));
                project.setOwner(rs.getInt("Owner_id"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return project;
    }

    public Project updateProject(Project project) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        try {
            String update = "UPDATE Project SET Name = ?, Description = ?, Owner_id = ?";
            stmt = super.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,project.getName());
            stmt.setString(2,project.getDescription());
            stmt.setInt(3,project.getOwner());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                project.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return project;
    }

    public void deleteProject(Project project) throws Exception{
        PreparedStatement stmt;

        try {
            String delete = "DELETE FROM Project WHERE id = ?";
            stmt = super.connection.prepareStatement(delete);
            stmt.setInt(1, project.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ArrayList<Project> getAllProjects() throws Exception {
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Project> projectList = new ArrayList<>();
        try{
            String select = "SELECT * FROM Project";
            stmt = super.connection.prepareStatement(select);
            rs = stmt.executeQuery();

            while (rs.next()){
                Project project = new Project();

                project.setId(rs.getInt("Id"));
                project.setName(rs.getString("Name"));
                project.setDescription(rs.getString("Description"));
                project.setOwner(rs.getInt("Owner_id"));

                projectList.add(project);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return projectList;
    }
}
