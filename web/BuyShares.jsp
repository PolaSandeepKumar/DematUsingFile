<%-- 
    Document   : BuyShares.jsp
    Created on : Mar 14, 2020, 11:23:58 PM
    Author     : polkumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h1>Hello World!</h1>
        
        <form action="SharePojo" method="post">
            
            
            <input type="submit" value="Buy Shares">
 
            
        </form>
        
        
      <font color="black"><b>Share Name : ${shareprice.getSharename()}</b></font></br>
    <font color="black"><b>Share Price : ${shareprice.getSharecost()}</b></font>
    
    </body>
</html>
