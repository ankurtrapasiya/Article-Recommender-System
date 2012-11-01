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

        <script id="article-template" type="text/x-handlebars-template">                            
            <div id="main">
                {{#each this}}                
                <div id="header">             
                    {{title}}
                </div>
                <div id="votes">           
                    <img class="img" src="images/up.png" ></img>
                    <br/>
                    <img class="img" src="images/down.png" /></img>
                </div>                
                <div id="footer">                    
                    row
                </div>         
                <div class="content">                    
                    content
                </div>                
                </br>
                {{/each}}            
            </div>
        </script>        

        <div id="contentarea" class="at_article_container">
            <div id="rows">

            </div>
        </div>

        <div id="at_sidebar" class="at_article_sidebar" style="float:right;border: #3c78ba;border-style: solid; ">                       
            <h2>Interests</h2>
            <div id="links">                
            </div>
        </div>




        <script type="text/javascript">
            (function(){
                $.getJSON("ArticleController", function(dt){
                   
                    var temp=Handlebars.compile($("#interest-template").html());                        
                    $("#at_sidebar div").html(temp(dt.content));
                   
                    $("#links").find("a").on("click",function(e){
                        e.preventDefault();
                        var linktype = $(this).attr("rel");    
                        
                        var url="ArticleController?interestid=";
                        var url1=url.concat(linktype);
                        
                        $.get(url1, function(dt){

                            var temp=Handlebars.compile($("#article-template").html());                        
                            $("#contentarea div").html(temp(dt.content));
                            
                        },"json");
                        
                    });                            
                });                                                               
            })();
        </script>        
    </body>
</html>
