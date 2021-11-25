package DAO;

import Connection.BaseDAO;
import Model.Requirements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.time.Month;

public class RequirementDAO extends BaseDAO{
    public RequirementDAO() throws Exception {super();}

 
    public static String getMoment() {    
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now);  
    }  

    public static LocalDateTime convertDate(String input){
        LocalDateTime outputDate = null;
        int year = Integer.parseInt(input.substring(0,3));
        int day = Integer.parseInt(input.substring(8,9));
        Month month = Month.of(Integer.parseInt(input.substring(5,6)));
        int hour = Integer.parseInt(input.substring(11,12));
        int minute = Integer.parseInt(input.substring(14,15));
        int seconds = Integer.parseInt(input.substring(17,18));

        outputDate = LocalDateTime.of(year, month, day, hour, minute, seconds);

        return outputDate;
    }
    public static String  convertDate(LocalDateTime input){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        return dtf.format(input); 
    }

    public void createRequirement(Requirements requirement, int projectId) throws Exception{
        PreparedStatement stmt;
        String moment = getMoment();

        try {  

            String insert = "INSERT INTO Requirements VALUE (DEFAULT, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            stmt = super.connection.prepareStatement(insert);

            stmt.setString(1,requirement.getName());
            stmt.setString(2,requirement.getModule());
            stmt.setString(3,requirement.getFeature());
            stmt.setString(4,moment);
            stmt.setString(5,requirement.getAuthor());
            stmt.setString(6,moment);
            stmt.setString(7,requirement.getAuthor());
            stmt.setString(8,"0.0.01");
            stmt.setString(9,requirement.getPriority());
            stmt.setString(10,requirement.getComplexity());
            stmt.setString(11,Integer.toString(requirement.getEstimatedHours()));
            stmt.setString(12,requirement.getState());
            stmt.setString(13,requirement.getPhase());
            stmt.setString(14,requirement.getDescription());
            stmt.setString(15, Integer.toString(projectId));
            stmt.executeUpdate();

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Requirements getRequirement(int id) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        Requirements requirement = null;

        try{
            String select = "SELECT * FROM Requirements WHERE id = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            if (rs.next()){
                requirement = new Requirements();
                requirement.setId(rs.getInt("Id"));
                requirement.setName(rs.getString("Name"));
                requirement.setFeature(rs.getString("Feature"));
                requirement.setModule(rs.getString("Module"));
                requirement.setCreationDate(convertDate(rs.getString("CreationDate")));
                requirement.setAuthor(rs.getString("Author_id"));
                requirement.setLastChange(convertDate(rs.getString("LastChange")));
                requirement.setLastChangeAuthor(rs.getString("LastChangeAuthor_id"));
                requirement.setVersion(rs.getString("Version"));
                requirement.setPriority(rs.getString("Priority"));
                requirement.setComplexity(rs.getString("Complexity"));
                requirement.setEstimatedHours(Integer.parseInt(rs.getString("EstimatedHours")));
                requirement.setState(rs.getString("State"));
                requirement.setPhase(rs.getString("Phase"));
                requirement.setDescription(rs.getString("Description"));
                requirement.setProjectId(Integer.parseInt(rs.getString("Project_id")));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return requirement;
    }

    public Requirements updateRequirement(Requirements requirement, int projectId, int userId) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        String moment = getMoment();

        try {
            String update = "UPDATE Requirements SET Name = ?, Module = ?, Feature = ?, CreationDate = ?, Author_id = ?, LastChange = ?, LastChangeAuthor_id = ?, Version = ?, Priority = ?, Complexity = ?, EstimatedHours = ?, State = ?, Phase = ?, Description = ?, Project_id = ?";
            stmt = super.connection.prepareStatement(update);
            stmt.setString(1,requirement.getName());
            stmt.setString(2,requirement.getModule());
            stmt.setString(3,requirement.getFeature());
            stmt.setString(4,convertDate(requirement.getCreationDate()));
            stmt.setString(5,requirement.getAuthor());
            stmt.setString(6,moment);
            stmt.setString(7,Integer.toString(userId));
            stmt.setString(8,requirement.getVersion());
            stmt.setString(9,requirement.getPriority());
            stmt.setString(10,requirement.getComplexity());
            stmt.setString(11,Integer.toString(requirement.getEstimatedHours()));
            stmt.setString(12,requirement.getState());
            stmt.setString(13,requirement.getPhase());
            stmt.setString(14,requirement.getDescription());
            stmt.setString(15, Integer.toString(projectId));
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                requirement.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return requirement;
    }

    public void deleteRequirement(int requirementId) throws Exception {
        PreparedStatement stmt;

        try {
            String delete = "DELETE FROM Requirements WHERE id = ?";
            stmt = super.connection.prepareStatement(delete);
            stmt.setInt(1, requirementId);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
