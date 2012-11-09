<%-- 
    Document   : searchresults
    Created on : Nov 9, 2012, 10:57:34 AM
    Author     : Harmish
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script src="js/jquery-1.8.2.min.js"></script>

        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <script>
          
            
            
            function ajaxcall(articleid) {
              
                $.get("PrintArticle?articleid=", { article: articleid },
                function(data){
                    alert("Data Loaded: " + data);
                });
            }
            
        </script>


        <script>
            
            
            
            function getXMLObject()  //XML OBJECT
            {
                var xmlHttp = false;
                try {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
                }
                catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
                    }
                    catch (e2) {
                        xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
                    }
                }
                if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
                    xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
                }
                return xmlHttp;  // Mandatory Statement returning the ajax object created
            }
            
            var xmlhttp = new getXMLObject();	//xmlhttp holds the ajax object
            
            
            
            
            function fetchArticle(articleid) {
             
                // alert(articleid);
                console.log(articleid);
                if(xmlhttp) {
                    xmlhttp.open("GET","PrintArticle?articleid=" + articleid,true);
                    xmlhttp.onreadystatechange  = handleServerResponse;
                    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xmlhttp.send(null);
                }
            }
            
            
            
            function handleServerResponse() {
                if (xmlhttp.readyState == 4) {
                    if(xmlhttp.status == 200) {
                         
                        var x = xmlhttp.responseText;
                        alert(x);
                        console.log(x);

                        var element=document.getElementById("articlebody");
                        element.innerHTML=x;
                        //  $("#articlebody").append(x);
                        //  $("#articlebody").html(x);
                    } else {
                            
                    }
                }
                else {
                    alert("Error during AJAX call. Please try again");
                }
                
            }
            
            
            
        </script>
    </head>
    <body>


        Following results are found
        <div style="width: 889px; height: 274px; overflow-y:scroll">

            <span>

                <table>
                    <%
                    %>
                    <c:forEach var="article" items="${articles}">

                        <tr>
                            <td>
                                <a href="" onclick="ajaxcall(${article.articleid})"><c:out value="${article.title}" /></a>
                            </td>
                            
                            <td><c:out value="${article.upvote}"/></td>
                            <td><c:out value="${article.downvote}"/></td>
                            <td><c:out value="${article.viewed}"/></td>
                            
                        </tr>
                        <tr>
                            <td colspan="4">
                                <c:out value="${article.body}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>	
            </span>
        </div>
        <br/>
        <br/>
        <div id="viewpane" style="width: 889px; height: 317px; overflow-y:scroll">
            <div id="articletitle">

            </div>
            <div id="articlebody">

            </div>
        </div>

    </body>
</html>