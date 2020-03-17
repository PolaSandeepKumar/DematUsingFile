package Demat;

    
import java.sql.*;  
    class SqlConnect{  
       
public static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException  {
    
        String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = "jdbc:mysql://gccdatabase.co5ivwyp2xvt.us-west-2.rds.amazonaws.com:3306/"; 
        // Database name to access 
        String dbName = "AtlasDemat"; 
        String dbUsername = "root"; 
        String dbPassword = "ashwinchitransh"; 
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL + dbName, 
                                                     dbUsername,  
                                                     dbPassword); 
        System.out.println("Connected to databse successfully");
        return con; 
        
}      
        }
