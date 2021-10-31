package by.parakhnevich.keddit.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/keddit";
    private static final String USER = "root";
    private static final String PASSWORD = "dendil";

    public void connect() {
        try (Connection connection = DriverManager.getConnection(
                URL , USER, PASSWORD)){
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
