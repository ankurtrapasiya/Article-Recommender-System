<%-- 
    Document   : suggestions
    Created on : 27 Oct, 2012, 7:06:33 AM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suggestions</title>
    </head>
    <body>
    <c:if test="${requestScope.status eq 'true'}"> 

        <h3>Suggestions :</h3>
        <table>
            <tr>
                <td>
                    <table>   
                        <c:forEach var="user" items="${requestScope.user}">  
                            <tr>
                                <td> <c:out value="${user.username}"></c:out> has has suggested you an article </td>
                            <table>   
                                </tr>
                        </c:forEach>
                    </table>      


                </td>       
                <td>

                    <table>

                        <c:forEach var="links" items="${requestScope.links}">  
                            <tr>
                                <td> <a href="<c:out value="${links.articleurl}"></c:out>">click here</a></td>  
                            </tr>
                        </c:forEach>  
                    </table>
                </td>
            </tr>
        </table>
    </c:if>

</body>
</html>
