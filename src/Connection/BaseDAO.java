package Connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class BaseDAO implements Closeable {

    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    protected Connection connection;

    public BaseDAO() throws Exception{
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM X.X");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new IOException("Database closing failure", e);
        }
    }
}
