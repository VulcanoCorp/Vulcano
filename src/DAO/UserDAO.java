package DAO;

import Connection.BaseDAO;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO extends BaseDAO {

    public UserDAO() throws Exception {super();}

    public User createUser(User user) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        try {

            String insert = "INSERT INTO User VALUE (DEFAULT, ?, ?, ?, ?, ?)";

            stmt = super.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,user.getFirstName());
            stmt.setString(2,user.getUserName());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassword());
            stmt.setString(5,user.getContactNumber());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return user;
    }

    public User getUserByName(User user) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        try{
            String select = "SELECT * FROM User WHERE FirstName = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setString(1,user.getUserName());
            rs = stmt.executeQuery();

            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("Id"));
                user.setFirstName(rs.getString("FirstName"));
                user.setUserName(rs.getString("UserName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setContactNumber(rs.getString("ContactNumber"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return user;
    }

    public User getUserById(User user) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        try{
            String select = "SELECT * FROM User WHERE id = ?";
            stmt = super.connection.prepareStatement(select);
            stmt.setInt(1,user.getId());
            rs = stmt.executeQuery();

            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("Id"));
                user.setFirstName(rs.getString("FirstName"));
                user.setUserName(rs.getString("UserName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setContactNumber(rs.getString("ContactNumber"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return user;
    }

    public User updateUser(User user) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;

        try {
            String update = "UPDATE User SET FirstName = ?, UserName = ?, Email = ?, Password = ?, ContactNumber = ?";
            stmt = super.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,user.getFirstName());
            stmt.setString(2,user.getUserName());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassword());
            stmt.setString(5,user.getContactNumber());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return getUserById(user);
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

    public ArrayList<User> getAllUsers() throws Exception {
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<User> userList = new ArrayList<>();
        try{
            String select = "SELECT * FROM User";
            stmt = super.connection.prepareStatement(select);
            rs = stmt.executeQuery();

            while (rs.next()){
                User user = new User();

                user.setId(rs.getInt("Id"));
                user.setFirstName(rs.getString("FirstName"));
                user.setUserName(rs.getString("UserName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setContactNumber(rs.getString("ContactNumber"));

                userList.add(user);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        return userList;
    }
}
