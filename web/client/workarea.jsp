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
            a.source,.sidebar a{
                color:#4878b3;
                text-decoration:none;
            }	
            a.source:hover,.sidebar a:hover{
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
                    <img src="../images/thumbs-up-1.png" alt="" />
                    <label class="counter">{{upvote}}</label>
                    <br />
                    <label class="counter">{{downvote}}</label>
                    <img src="../images/thumbs-down.png" alt="" />
                    <label class="views">{{viewed}} Views</label>
                </div>

                <div class="meta-data">

                    <p class="tags">
                        Tags : tag1
                    </p>
                    <div  class="meta-links">
                        <span>Source : <a href="#" class="source">link1</a></span>
                        <span class="alignright">Posted On : {{publicationdate}}</span>
                    </div>
                    <div class="meta-links">
                        <span><input type="submit" value="Add to Favorite" /></span>
                        <span class="alignright"><input type="submit" value="Read Later" /></span>
                    </div>
                    <div id="article_content">
                        {{body}}
                    </div>
                </div>

            </div>
            {{/each}}            
        </script>    


        <div id="article-contents" class="float-left">
            <div class="article">
                <div class="article-heading">
                    <h3>Article heading will come over here babes..:D</h3>
                </div>

                <div class="description">
                    <div class="like-dislike">
                        <img src="../images/thumbs-up-1.png" alt="" />
                        <label class="counter">9</label>
                        <br />
                        <label class="counter">8</label>
                        <img src="../images/thumbs-down.png" alt="" />
                        <label class="views">100 Views</label>
                    </div>

                    <div class="meta-data">

                        <p class="tags">
                            Tags : output the tags over here. as much as u want
                        </p>
                        <div  class="meta-links">
                            <span>Source : <a href="#" class="source">Here comes the wtf link</a></span>
                            <span class="alignright">Posted On : 31-12-2101</span>
                        </div>
                        <div class="meta-links">
                            <span><input type="submit" value="Add to Favorite" /></span>
                            <span class="alignright"><input type="submit" value="Read Later" /></span>
                        </div>
                        <div id="article_content">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. In gravida, dolor eu imperdiet commodo, nisl lacus faucibus libero, nec luctus diam turpis ac augue. Phasellus vitae sollicitudin felis. <a href="#" class="more-link">View More</a>
                        </div>
                    </div>

                </div>
            </div>
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
                        <li>
                            <a href="#">Interest first</a>
                        </li>
                        <li>
                            <a href="#">Interest first</a>
                        </li>
                        <li>
                            I<a href="#">Interest first</a>
                        </li>
                        <li>
                            <a href="#">Interest first</a>
                        </li>
                    </ul>
                </div>    
            </div>

        </div>
        <div class="clearfix"></div>

        <!--
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
                </div>	-->



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
