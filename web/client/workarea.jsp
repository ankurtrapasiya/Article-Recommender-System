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
                <li><a href="#" rel="{{interestid}}">{{name}}</a></li>                        
                {{/each}}
            </ul>                
        </script>        

        <div id="sidebar" style="float:right;margin-right: 100px;">                       
            <h2>Interests</h2>
            <div id="links">                
            </div>
        </div>


        <div id="maincontent">

        </div>


        <script type="text/javascript">
            (function(){
                var data=$.getJSON("ArticleController", function(dt){
                    var temp=Handlebars.compile($("#interest-template").html());                        
                    $("#sidebar div").html(temp(dt.content));
                    $("#links").find("a").on("click",function(e){
                        e.preventDefault();
                        var linktype = $(this).attr("rel");    
                        
                        var url="ArticleController?interestid=";
                        var url1=url.concat(linktype);
                        console.log(url1);
                        var data1=$.getJSON(url1, function(dt){
                            console.log(dt.content)
                        });
                        
                    });          
                });                                                               
            })();
        </script>        
    </body>
</html>
