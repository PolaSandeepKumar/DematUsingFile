

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <FORM METHOD=POST ACTION="name"></FORM>
    </head>
    <body bgcolor="green">
        <h1 align = "center">Welcome to Demat Account ${student.getUsername()} </h1>
    </body>
    
    <font color="white"><b>Your Account Balance : ${student.getAmount()}</b></font></br>
    <font color="white"><b>Your Account ID : ${student.getAccountnumber()}</b></font>

    <b><a href="BuyShares.jsp">Buy Shares</a></b>
    
</html>
