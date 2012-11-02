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

            {{#each this}}                
            <div id="at_main">
                <div id="header">             
                    title : {{title}}
                </div>
                <div id="info">
                    info
                </div>
                <div id="votes">                               
                    
                    <div id="votes_content">
                        <img src="images/up.png"></img>
                        <br/>
                        <img src="images/down.png"></img>
                    </div>
                    
                </div>                                        
                <div id="content">                    
                    content : {{body}}
                </div>                                
            </div>
            <br/>
            {{/each}}            

        </script>        

        <div id="container">
            <div id="contentarea" class="at_article_container">
                <div id="contentheader" class="at_article_header">
                    <h2>Article Suggestions</h2>
                </div>
                <div id="rows" class="at_article_row">

                </div>
            </div>    
            <div id="at_sidebar" class="at_article_sidebar">                       
                <h2>Interests</h2>
                <div id="links">                
                </div>
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
                            $("#contentarea #rows").html(temp(dt.content));
                            
                        },"json");
                        
                    });                            
                });                                                               
            })();
        </script>        
    </body>
</html>
