<%-- 
    Document   : workarea
    Created on : 28 Oct, 2012, 7:28:30 AM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">                       
        <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="js/handlebars.js"></script>

    </head>
    <body>


        <script id="interest-template" type="text/x-handlebars-template">                
            <ul>
                {{#each this}}                    
                <li><a href="ArticleController?interestid={{interestid}}">{{name}}</a></li>                        
                {{/each}}
            </ul>                
        </script>
        Hello i am here

        <div id="sidebar" style="float:right">                       
            <h2>Interests</h2>
            <div id="menu">                
            </div>
        </div>


        <script type="text/javascript">
            (function(){
                var data=$.getJSON("ArticleController", function(dt){
                    var temp=Handlebars.compile($("#interest-template").html());                        
                    $("#sidebar div").append(temp(dt.content));
                    console.log(temp(dt.content));
                });                
            })();
                
        </script>        
    </body>
</html>
