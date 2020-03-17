/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demat;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author polkumar
 */
public class DematloginController extends HttpServlet {

  
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
        ResultSet rs=stmt.executeQuery("select * from User where Accountnumber = " + number + " ");  
CreatePojo cp = new CreatePojo();
        while(rs.next()){
            // Execute the insert command using executeUpdate() 
            // to make changes in database 
            cp.setAccountnumber(rs.getInt(1));
            cp.setUsername(rs.getString(2));
            cp.setAmount(rs.getInt(3));
            // Close all the connections   
}
 
            
            conection1.close();
            
             request.setAttribute("student",cp);
             
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
       request.getRequestDispatcher("UserDetails.jsp").forward(request, response);
          
    
    } 


}







    

