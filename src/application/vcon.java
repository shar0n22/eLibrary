package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class vcon {
    public static Connection getConnection() throws SQLException {
    	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
    	return connection;
    }
}
