import View.View;
import DAO.UserDAO;
import Model.User;

public class Main {

    public static void main(String[] args) {
        try {
            new View();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}