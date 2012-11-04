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
        <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="../js/handlebars.js"></script>
        <script type="text/javascript" src="../js/moment.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

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
                    <a href="#" rel="{{articleid}}" id="addtofavourites">Add to favourites</a>
                    <a href="#" rel="{{articleid}}" id="readitlater">Read it later</a>
                </div>
                <div id="info">
                    <span id="dateinfo">posted on {{publicationdate}}</span>
                    <span id="totalviews"> <b>Total views {{viewed}} </b></span>
                </div>
                <div id="votes">                               

                    <div id="votes_content">
                        <a href="#" id="up" rel="{{articleid}}"><img src="images/up.png"></img></a>
                        <br/>
                        <div id="upvotes">
                            {{upvote}}
                        </div>
                        <div id="downvotes">
                            {{downvote}}
                        </div>
                        <a href="#" id="down" rel="{{articleid}}"><img src="images/down.png"></img></a>                        
                    </div>

                </div>                                        
                <div id="content">                    
                    content : {{body}}
                </div>                                
            </div>
            <br/>
            {{/each}}            

        </script>        
        <div id="contentheader" class="at_article_header">
            <h2>Article Suggestions</h2>
        </div>
        <div id="container">
            <div id="contentarea" class="at_article_container">

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
                            
                            
                            
                            $("#rows").find("a").on("click",function(e){
                            
                                e.preventDefault();
                                var val=$(this).attr("rel");  
                                
                                var id=$(this).attr("id");
                                
                                var upvoteDiv=$(this).parent().find("#upvotes");   
                                var downvoteDiv=$(this).parent().find("#downvotes");
                                var anchor=$(this);
                                
                                console.log(anchor);
                                                                                                
                                if(id==="addtofavourites")
                                {
                                    var url="ArticleController?action=addtofavourites&articleid=";
                                    var url1=url.concat(val);
                                    
                                    $.post(url1,function(dt){
                                        
                                        console.log(dt.content);
                                        anchor.html(dt.content.status);
                                        
                                    },"json");
                                    
                                }
                                else if(id==="readitlater")
                                {
                                    var url="ArticleController?action=readitlater&articleid=";
                                    var url1=url.concat(val);
                                    
                                    $.post(url1,function(dt){
                                        
                                        console.log(dt.content);
                                        anchor.html(dt.content.status);
                                        
                                    },"json");
                                }
                                else if(id==="down")
                                {
                                    var url="ArticleController?action=downvote&articleid=";
                                    var url1=url.concat(val);
                                
                                    $.post(url1,function(dt)
                                    {
                                        downvoteDiv.html("<label>" + dt.content[0].downvote + "</label>");   
                                        upvoteDiv.html("<label>" + dt.content[1].upvote + "</label>");
                                    },"json");
                                }
                                else
                                {
                                        
                                    var url="ArticleController?action=upvote&articleid=";
                                    var url1=url.concat(val);
                                
                                    $.post(url1,function(dt)
                                    {
                                        
                                        upvoteDiv.html("<label>" + dt.content[0].upvote + "</label>");                                        
                                        downvoteDiv.html("<label>" + dt.content[1].downvote + "</label>");   
                                    
                                    },"json");
                                }                                
                            });
                            
                        },"json");
                        
                    });                           
                });                                                               
            })();
        </script>        
    </body>
</html>
