<%-- 
    Document   : notifications
    Created on : 27 Oct, 2012, 7:06:49 AM
    Author     : ankur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notifications</title>
    </head>
    <body>

        <h1>Hello World!</h1>

        <c:if test="${requestScope.status eq 'true'}"> 

            <h3>Notifications :</h3>
            <table border=2>

                <c:forEach var="user" items="${requestScope.user}">
                    <tr>  

                        <td> <c:out value="${user.username}"></c:out> has added to your graph</td>

                        </tr>

                </c:forEach>	
            </table>
        </c:if>
    </body>

</html>
