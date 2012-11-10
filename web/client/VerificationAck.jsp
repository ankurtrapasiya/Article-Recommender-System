<%-- 
    Document   : VerificationAck
    Created on : Nov 10, 2012, 9:56:55 AM
    Author     : Harmish
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String status = request.getParameter("status");
            if(status.equalsIgnoreCase("1")) {
        %>
        Congratulations!
        <br/><br/>
        You have successfully verified your email id.
        
        
        <%
                
            } else {
        %>
        Sorry, email verification failed.
        <%
            }
        %>
        <br>
        <a href="/surpriseme">Click me to log into Surprise.Me</a>
    </body>
</html>
