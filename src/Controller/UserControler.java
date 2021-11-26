package Controller;


import java.util.ArrayList;

import DAO.UserDAO;
import Model.User;

public class UserControler {
    public User getUserById(int id) throws Exception{

        try(UserDAO userDAO = new UserDAO()) {

            User userData= userDAO.getUserById(id);
            return userData;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ArrayList<User> listAllUser() throws Exception{

        try(UserDAO userDAO = new UserDAO()) {

            ArrayList<User> users= userDAO.getAllUsers();
            return users;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public User saveUserChanges(User user) throws Exception{

        try(UserDAO userDAO = new UserDAO()) {
            User userData= userDAO.updateUser(user);
            return userData;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void registerUser(User user) throws Exception{

        try(UserDAO userDAO = new UserDAO()) {

            userDAO.createUser(user);
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
