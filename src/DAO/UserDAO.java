package DAO;

import Connection.BaseDAO;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends BaseDAO {

    public UserDAO() throws Exception {super();}

    public void createUser(User user) throws Exception{
        PreparedStatement stmt;

        try {

            String insert = "INSERT INTO User VALUE (DEFAULT, ?, ?, ?, ?, ?)";

            stmt = super.connection.prepareStatement(insert);

            stmt.setString(1,user.getFirstName());
            stmt.setString(2,user.getUserName());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassword());
            stmt.setString(5,user.getContactNumber());
            stmt.executeUpdate();

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public User getUser(User user) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        User user1 = null;

        try{
            String select = "SELECT * FROM User WHERE id = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,user.getId());
            rs = stmt.executeQuery();

            if (rs.next()){
                user1 = new User();
                user1.setId(rs.getInt("Id"));
                user1.setFirstName(rs.getString("FirstName"));
                user1.setUserName(rs.getString("UserName"));
                user1.setEmail(rs.getString("Email"));
                user1.setPassword(rs.getString("Password"));
                user1.setContactNumber(rs.getString("ContactNumber"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return user1;
    }

    public void updateUser(User user) throws Exception{

    }

    public void deleteUser(User user) throws Exception {
        PreparedStatement stmt;

        try {
            String delete = "DELETE FROM User WHERE id = ?";
            stmt = super.connection.prepareStatement(delete);
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
