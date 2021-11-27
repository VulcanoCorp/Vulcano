package Controller;

import DAO.UserDAO;
import Model.User;

public class SessionController {

    public User login(String user, String password) throws Exception{

        try(UserDAO userDAO = new UserDAO()) {

            User userToLogin = new User();
            userToLogin.setUserName(user);

            User loggedUser = userDAO.getUserByName(userToLogin);

            if(loggedUser == null) {
                throw new Exception(" User not found! ");
            } else if(loggedUser.getPassword().equals(password)){
                return loggedUser;
            }else {
                throw new Exception("Password wrong !");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
