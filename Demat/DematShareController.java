/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author polkumar
 */

public class DematShareController extends HttpServlet {
  
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
         
            Statement stmt=conection1.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from Shares");  
SharePojo sp = new SharePojo();
        while(rs.next()){
            // Execute the insert command using executeUpdate() 
            // to make changes in database 
            sp.setShareid(rs.getInt(1));
            sp.setSharename(rs.getString(2));
            sp.setSharecost(rs.getInt(3));
            // Close all the connections   
}
 
            
            conection1.close();
            
             request.setAttribute("shareprice",sp);
             
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
       request.getRequestDispatcher("BuyShares.jsp").forward(request, response);
          
    
    } 


}    

