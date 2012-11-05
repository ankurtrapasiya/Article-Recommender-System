<%-- 
    Document   : InsertImage
    Created on : 4 Nov, 2012, 8:05:50 PM
    Author     : peace
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="InsertImage" method="post" enctype="multipart/form-data">
            <table align="center" cellspacing="2" cellpadding="2">     
                <tr>
                    <td>Icon :</td>
                    <td>
                <tr>
                    <td rowspan="2">


                        <img src="images/Tag/<c:out value="${name}"/>"width="45" height="45">


                    </td>
                    <td><input type="file" name="file" size="50" id="file" /> </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Upload File"/> <a href="<c:out value="${fileurl}"></c:out>"><input type="button" value="Skip Upload" /></a></td>
                </tr>
                <tr>
                    <td colspan="2" style="color: red"><c:out value="${message}"></c:out></td>
                </tr>

                </td>
                </tr>
            </table>
        </form>
    </body>
</html>