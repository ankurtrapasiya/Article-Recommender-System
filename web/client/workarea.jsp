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
        <style type="text/css">

            #article-contents{

                width:80%; 
                float: left;
            }
            .sidebar{

                width: 18%;
                margin-left: 15px;
                float: left;
            }
            .sidebar ul{

                padding-left: 20px;
            }
            .sidebar ul li{
                margin-bottom: 10px;
                color: #4878b3;
                list-style: none;
                background: url(../images/1_bullet_triangle_blue.png) scroll 0 4px no-repeat;
                padding-left: 15px;
            }

            .article-heading,.sidebar-title{
                padding:5px 10px;
                border:1px solid #c9ebff;
                margin-bottom:2px;
            }
            .article-heading h3,.sidebar-title h3{
                margin:0;
                color:#6a84bf;
            }	
            .description,.sidebar-links{
                border:1px solid #c9ebff;
                background-color: #DFECEE;
            }	
            .article{

                margin-bottom:10px;
            }
            .like-dislike{
                text-align:center;
                width:110px;
                float:left;
                padding:10px 0;
            }	
            .like-dislike img{
                display:block;
                margin:0 auto;
            } 
            .counter{
                color:#778bb7;
            }	
            .views{
                color:#778bb7;
                margin-top:10px;
                display:inline-block;
            }			
            .meta-data{
                color:#6e6e6e;
                padding:10px 10px;
            }
            .tags{
                color:#1541a0;
                margin-bottom:10px;
                margin-top: 0px;
            }	
            a.source,.sidebar a,a.tag-link{
                color:#4878b3;
                text-decoration:none;
            }	
            a.source:hover,.sidebar a:hover,a.tag-link:hover{
                text-decoration:underline;
            }

            .clearfix:after{clear:both;content:' ';display:block;font-size:0;line-height:0;visibility:hidden;width:0;height:0}* html .clearfix,*:first-child+html .clearfix{zoom:1}

            .alignright, img.alignright {
                display: inline;
                float: right;
                margin-left: 1.5em;
            }

            a.more-link{
                text-decoration:none;
                font-style:italic;
                color:#4878b3!important;
                display:inline-block;
                font-size:11px;
            }
            a.more-link:hover{
                text-decoration:underline;
            }


            input[type="submit"]{
                background-color:#FFF;
                border:1px solid #cacaca;
                color:#3ca7c1;
                padding:2px 5px;
                cursor:pointer;
            }

            .meta-links{
                margin-bottom: 10px;
            }
            .float-left{

                float: left;
            }
            .counter-up{
                color:#778bb7;
            }
            .counter-down{
                color:#778bb7;
            }         

        </style>
    </head>
    <body>

        <script id="article-template" type="text/x-handlebars-template">    
            {{#each this}}    
            
                <div class="article-heading">
                    <h3>{{title}}</h3>
                </div>
                <div class="description">
                    <div class="like-dislike">
                        <a href="#" id="up" rel="{{articleid}}"><img src="../images/thumbs-up-1.png" alt="" /></a>
                        <label class="counter-up">{{upvote}}</label>
                        <br />
                        <label class="counter-down">{{downvote}}</label>
                        <a href="#" id="down" rel="{{articleid}}"><img src="../images/thumbs-down.png" alt="" /></a>
                        <label class="views">{{viewed}} Views</label>
                    </div>

                    <div class="meta-data">

                        <p class="tags">
                            Tags : 
                            {{#tags}}

                            <a href="#" class="tag-link">{{name}}</a>

                            {{/tags}}
                        </p>
                        <div  class="meta-links">
                            <span>Source : <a href="#" class="source">{{#sources}}{{source}}{{/sources}}</a></span>
                            <span class="alignright">Posted On : {{publicationdate}}</span>
                        </div>
                        <div class="meta-links">
                            <span><input id="addtofav" type="submit" data="{{articleid}}" value="Add to Favorite" /></span>
                            <span class="alignright"><input id="readlater" data="{{articleid}}" type="submit" value="Read Later" /></span>
                        </div>
                        <div id="article_content">
                            {{body}}
                        </div>
                    </div>

                </div>                
            {{/each}}            
        </script>    

        <script id="interest-template" type="text/x-handlebars-template">                
            <ul>
                {{#each this}}                    
                <li><a href="#" rel="{{interestid}}">{{name}}</a></li>                        
                {{/each}}
            </ul>                
        </script> 

        <div id="article-contents" class="float-left">
            
            <div class="article"></div>
        </div>

        <div>
            <div class="sidebar">
                <div class="sidebar-title">
                    <h3>
                        Interest
                    </h3>
                </div>

                <div class="sidebar-links"> 
                    <ul>

                    </ul>
                </div>    
            </div>

        </div>
        <div class="clearfix"></div>


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
                $.getJSON("ArticleController?freq=true", function(dt){
                   
                    var temp=Handlebars.compile($("#article-template").html());                        
                    $("#article-contents").html(temp(dt.content));
                });
                
                $.getJSON("ArticleController", function(dt){
                   
                    var temp=Handlebars.compile($("#interest-template").html());                        
                    $(".sidebar .sidebar-links").html(temp(dt.content));
                   
                    $(".sidebar-links").find("a").on("click",function(e){
                        e.preventDefault();
                        var linktype = $(this).attr("rel");    
                        
                        var url="ArticleController?interestid=";
                        var url1=url.concat(linktype);
                        
                        $.get(url1, function(dt){

                            var temp=Handlebars.compile($("#article-template").html());                        
                            $("#article-contents").html(temp(dt.content));
                            
                            
                            console.log(dt.content);
                            
                            
                            
                            $(".meta-data ").find("input").on("click",function(e){
            
                                var x=$(this);
                                var id=$(this).attr("id");
                                var val=$(this).attr("data");
                                
                                console.log(id);
                                console.log(val);
                                
                                if(id==="addtofav")
                                {
                                    var url="ArticleController?action=addtofavourites&articleid=";
                                    var url1=url.concat(val);
                                    
                                    $.post(url1,function(dt){
                                        
                                        x.attr('disabled','disabled');
                                        x.val('in favourites');
                                        
                                    },"json");
                                    
                                }
                                else if(id==="readlater")
                                {
                                    var url="ArticleController?action=readitlater&articleid=";
                                    var url1=url.concat(val);
                                    
                                    $.post(url1,function(dt){
                                        
                                        x.attr('disabled','disabled');
                                        x.val('in readlater');
                                        
                                    },"json");
                                }
                                
                            });
                            
                            
                            $(".like-dislike").find("a").on("click",function(e){
                            
                                e.preventDefault();
                                var val=$(this).attr("rel");  
                                
                                var id=$(this).attr("id");
                                
                                var upvote=$(this).parent().find(".counter-up");   
                                var downvote=$(this).parent().find(".counter-down");
                                                                                                                               
                                if(id==="down")
                                {
                                    var url="ArticleController?action=downvote&articleid=";
                                    var url1=url.concat(val);
                                
                                    $.post(url1,function(dt)
                                    {
                                        downvote.html("<label>" + dt.content[0].downvote + "</label>");   
                                        upvote.html("<label>" + dt.content[1].upvote + "</label>");
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
                                        
                                        console.log(upvote);
                                        console.log(downvote);
                                        
                                        upvote.html("<label>" + dt.content[0].upvote + "</label>");                                        
                                        downvote.html("<label>" + dt.content[1].downvote + "</label>");   
                                    
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
