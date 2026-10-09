package main.java.com.jgunzalesindustries.control.recursos.humanos.config;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseConnection {
    private static Connection conn;
    
    public static Connection getDataBaseConnection() throws SQLException{
    if (conn == null || conn.isClosed()){
    conn = DriverManager.getConnection(Credentials.URL_DB,
            Credentials.USER_DB,
            Credentials.PASS_DB);
   
    } return conn;
    
    
    }
}
