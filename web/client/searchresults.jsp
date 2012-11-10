<%-- 
    Document   : demo
    Created on : Nov 10, 2012, 3:16:35 AM
    Author     : Harmish
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="../js/jquery-1.8.2.min.js"></script>
        <script src="../js/jquery-ui.js"></script>
        
        
        
        <style>
            #workarea{
                width:100%;
                height:100px;
            }
            
            #row{
                width:100%;
            }
            #a {
                width:80%;
                margin:5px;
                padding:5px;
                float:left;
            }
            .even{
                background-color: #F5F5F5;
            }
            .odd{
                background-color: #FFFFFF;
            }
            #row #details{
                width:60%;
                float:left;
                word-wrap:break-word;
            }

<!--
table{
    border-spacing: 0px;
    border-collapse: collapse;
    width: 250px;
}
th {
    text-align: center;
    font-weight: bold;
    padding: 2px;
    border: 2px solid #FFFFFF;
    background: #4a70aa;
    color: #FFFFFF;
}
td {
    text-align: center;
    padding: 2px;
    border: 2px solid #FFFFFF;
    background: #e3f0f7;
}
 
-->
           
        </style>
        
        
        
        <script>
            
            function fetchArticle(articleid) {

                $.get("../PrintArticle?articleid="+articleid,function(data){
                    $("#viewpane").html(data);
                });
            }
            
        </script>
        
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        
        <div id="articlelist" style="width: 889px; height: 274px; overflow-y:scroll">
            <table width="100%">
                <thead>
                    <tr>
                        <th style="width: 30%">
                            Title
                        </th>
                        <th>
                            Upvotes
                        </th>
                        <th>
                            Downvotes
                        </th>
                        <th>
                            Viewed
                        </th>
                    </tr>
                </thead>
                <% 
                    int i = 0;
                    String cssclass = (((++i) % 2) == 0)?".even":".odd";
                %>
                    <c:forEach var="article" items="${articles}" >

                        <tr onclick="javascript:fetchArticle(${article.articleid});" class="<%=(((++i) % 2) == 0)?".even":".odd" %>">
                            <td>
                                <c:out value="${article.title}" />
                            </td>

                            <td><c:out value="${article.upvote}"/></td>
                            <td><c:out value="${article.downvote}"/></td>
                            <td><c:out value="${article.viewed}"/></td>
                            <td style="width: 77px"></td>
                        </tr>
                        
                    </c:forEach>
                </table>
        </div>
        <br/>
        <br/>
        <div id="viewpane" style="width: 889px; height: 274px; overflow-y:scroll">
            
        </div>
    </body>
</html>
