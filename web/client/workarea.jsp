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
            <div id="content_votes">
                <div>
                    <a href="#" id="up" rel="{{articleid}}"><img src="../images/images/arrow-up.png"></img></a>
                </div>                    
                <div id="upvotes">
                    {{upvote}}
                </div>                                        
                <div id="downvotes">
                    {{downvote}}
                </div>                                        
                <div><a href="#" id="down" rel="{{articleid}}"><img src="../images/images/arrow-down.png"></img></a>
                </div>                    
            </div>


            <div id="content_title">             
                <span id="title">{{title}}</span>
                <div id="misc" >
                    <a href="#" rel="{{articleid}}" id="addtofavourites"><img src="../images/fav.png"></img></a>
                    <a href="#" rel="{{articleid}}" id="readitlater"><img src="../images/readlater.png" ></img></a>
                </div>
            </div>
            <div id="content_info">
                <div id="content_upload_date">
                    <h4>Upload Date: {{publicationdate}} </h4>
                </div>
                <div id="content_links">
                </div>
            </div>


            <div id="content_body">                    
                content : {{body}}
            </div>   

            {{/each}}            

        </script>    


        <div id="header">
            <h2>Article Suggestions</h2>
        </div>
        <div id="maincontainer">
            <div id="maincontent">
                <div id="row">                                 
                </div>
            </div>
            <div id="interestbar">
                <h3>Interests</h3>
                <div id="links">                
                </div>
            </div>
        </div>	



        <script type="text/javascript">
            
            $(document).ready(function(){
                $('a[name=slide]').live("click",function(){
               
               
                    if($(this).parent().parent().parent().next().is(":hidden"))
                    {
                    
                        $(this).text("View Less");
               
                
                    }
                    else
                    {
                        $(this).text("View More");
                    }
                    $(this).parent().parent().parent().next().slideToggle();
                    //$("articleid").append(id);
                                   

                });


            });
            
            (function(){
                $.getJSON("ArticleController", function(dt){
                   
                    var temp=Handlebars.compile($("#interest-template").html());                        
                    $("#interestbar div").html(temp(dt.content));
                   
                    $("#links").find("a").on("click",function(e){
                        e.preventDefault();
                        var linktype = $(this).attr("rel");    
                        
                        var url="ArticleController?interestid=";
                        var url1=url.concat(linktype);
                        
                        $.get(url1, function(dt){

                            var temp=Handlebars.compile($("#article-template").html());                        
                            $("#maincontent #row").html(temp(dt.content));
                            
                            
                            
                            $("#row").find("a").on("click",function(e){
                            
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
                                     
                                        console.log(dt.content[0].upvote);
                                        console.log(dt.content[1].downvote);
                                        
                                        console.log(upvoteDiv);
                                        console.log(downvoteDiv);
                                        
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
