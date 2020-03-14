package Demat;


import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*; 
import java.sql.PreparedStatement; 
  
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
  
// Import Database Connection Class file 
  
// Servlet Name 

public class DematController extends HttpServlet { 
  
    protected void doPost(HttpServletRequest request,  
HttpServletResponse response) 
        throws ServletException, IOException 
    { 
        try { 
  
            // Initialize the database 
            Connection con = SqlConnect.initializeDatabase(); 
  
            // Create a SQL query to insert data into demo table 
            // demo table consists of two columns, so two '?' is used 
            PreparedStatement st = con 
                   .prepareStatement("insert into User(Username) values(?)"); 
  
            // For the first parameter, 
            // get the data using request object 
            // sets the data to st pointer 
  
            // Same for second parameter 
            st.setString(1, request.getParameter("Username")); 
  
            // Execute the insert command using executeUpdate() 
            // to make changes in database 
            st.executeUpdate(); 
  
            // Close all the connections 
            st.close(); 
            con.close(); 
  
            // Get a writer pointer  
            // to display the successful result 
            PrintWriter out = response.getWriter(); 
            out.println("<html><body><b>Successfully Inserted"
                        + "</b></body></html>"); 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 


class DematControllerFetch extends HttpServlet { 
  
    protected void doPost(HttpServletRequest request,  
HttpServletResponse response) 
        throws ServletException, IOException 
    { 
        try { 
  
            // Initialize the database 
              ResultSet resultSet = null;
            Connection conection1 = SqlConnect.initializeDatabase(); 
  
            // Create a SQL query to insert data into demo table 
            // demo table consists of two columns, so two '?' is used 
           
  
            // For the first parameter, 
            // get the data using request object 
            // sets the data to st pointer 
  
            // Same for second parameter 
            int number =  Integer.valueOf(request.getParameter("accountnumber")); 
            Statement stmt=conection1.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from emp IN ("+number+")");  
while(rs.next())  
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
            // Execute the insert command using executeUpdate() 
            // to make changes in database 
            
            // Close all the connections 
        
            conection1.close();
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 