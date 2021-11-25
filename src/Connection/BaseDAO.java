package Connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class BaseDAO implements Closeable {

    private static final String URL = "jdbc:mysql://vulcano.cw8ps4zhk8op.sa-east-1.rds.amazonaws.com:3306/db_Vulcano?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT&useSSL=false";
    private static final String USER = "admin";
    private static final String PASSWORD = "Vu1c4n0c0FF3";

    protected Connection connection;

    public BaseDAO() throws Exception{
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
