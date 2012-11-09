<%-- 
    Document   : workarea
    Created on : 28 Oct, 2012, 7:28:30 AM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="/WEB-INF/tlds/taglibrary.tld" %>
<!DOCTYPE html>
<html>
    <head>
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
            #inline{
                display: none; width: 600px;height: 150px;
            }

            #send { 
                margin-top: 10px;
                color: #dee5f0;
                display: block;
                cursor: pointer;
                padding: 5px 11px;
                font-size: 1.2em;
                border: solid 1px #224983;
                border-radius: 5px;
                background: #1e4c99; 
                background: -webkit-gradient(linear, left top, left bottom, from(#2f52b7), to(#0e3a7d)); 
                background: -moz-linear-gradient(top, #2f52b7, #0e3a7d); 
                background: -webkit-linear-gradient(top, #2f52b7, #0e3a7d);
                background: -o-linear-gradient(top, #2f52b7, #0e3a7d);
                background: -ms-linear-gradient(top, #2f52b7, #0e3a7d);
                background: linear-gradient(top, #2f52b7, #0e3a7d);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#2f52b7', endColorstr='#0e3a7d'); 
            }
            #send:hover {
                background: #183d80; 
                background: -webkit-gradient(linear, left top, left bottom, from(#284f9d), to(#0c2b6b)); 
                background: -moz-linear-gradient(top,  #284f9d, #0c2b6b); 
                background: -webkit-linear-gradient(top, #284f9d, #0c2b6b);
                background: -o-linear-gradient(top, #284f9d, #0c2b6b);
                background: -ms-linear-gradient(top, #284f9d, #0c2b6b);
                background: linear-gradient(top, #284f9d, #0c2b6b);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#284f9d', endColorstr='#0c2b6b');
            }
            #send:active {
                color: #8c9dc0; 
                background: -webkit-gradient(linear, left top, left bottom, from(#0e387d), to(#2f55b7)); 
                background: -moz-linear-gradient(top,  #0e387d,  #2f55b7);
                background: -webkit-linear-gradient(top, #0e387d, #2f55b7);
                background: -o-linear-gradient(top, #0e387d, #2f55b7);
                background: -ms-linear-gradient(top, #0e387d, #2f55b7);
                background: linear-gradient(top, #0e387d, #2f55b7);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0e387d', endColorstr='#2f55b7');
            }

            .txt { 
                display: inline-block; 
                color: #676767;
                width: 420px; 
                font-family: Arial, Tahoma, sans-serif; 
                margin-bottom: 10px; 
                border: 1px dotted #ccc; 
                padding: 5px 9px;
                font-size: 1.2em;
                line-height: 1.4em;
            }

            .txtarea { 
                display: block; 
                resize: none;
                color: #676767;
                font-family: Arial, Tahoma, sans-serif; 
                margin-bottom: 10px; 
                width: 500px; 
                height: 150px;
                border: 1px dotted #ccc;
                padding: 5px 9px; 
                font-size: 1.2em;
                line-height: 1.4em;
            }

            .txt:focus, .txtarea:focus { border-style: solid; border-color: #bababa; color: #444; }

            input.error, textarea.error { border-color: #973d3d; border-style: solid; background: #f0bebe; color: #a35959; }
            input.error:focus, textarea.error:focus { border-color: #973d3d; color: #a35959; }

            label { margin-right: 12px; margin-bottom: 9px; font-family: Georgia, serif; color: #646464; font-size: 1.2em; }

        </style>

        <link rel="stylesheet" type="text/css" media="all" href="../css/popup/jquery.fancybox.css">
        <script type="text/javascript" src="../js/jquery.fancybox.js?v=2.0.6"></script>
        <script type="text/javascript" src="../js/jquery.tokeninput.js"></script>

        <link rel="stylesheet" href="../css/client/token-input.css" type="text/css" />
        <link rel="stylesheet" href="../css/client/token-input-facebook.css" type="text/css" />

    </head>
    <body>
        <!-- hidden inline form -->
        <div id="inline">
            <h2>Suggest A Friend</h2>

            <form id="suggestionform" name="suggestionform" action="#" method="post">
                <label for="demo-input-local">Enter Friend Names</label>
                <textarea id="demo-input-local" name="demo-input-local" cols="100" rows="6" /></textarea>
                <br>
                <button id="send">Send Suggestions</button>
            </form>
        </div>

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
                        <span><input id="suggest" type="submit" class="modalbox" href="#inline" data="{{articleid}}" value="suggest to friend"/></span>
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
                
                
                $(".modalbox").fancybox();
                $("#suggestionform").submit(function() { return false; });

                $("#demo-input-local").tokenInput(
            <c:PrintFriends/>,{theme:"facebook"}
                );
                
                    $("#send").on("click", function(){
                        var msgval  = $("#demo-input-local").val();
                        var msglen    = msgval.length;			
			
                        alert(msgval);
                        
                        if(msglen <= 0) {
                            $("#demo-input-local").addClass("error");
                        }
                        else
                        {
                            $("#demo-input-local").removeClass("error");        
                        }
                            
			
                        if(msglen >= 4) {
                            // if both validate we attempt to send the e-mail
                            // first we hide the submit btn so the user doesnt click twice
                            $("#send").replaceWith("<em>sending...</em>");
				
                            $.ajax({
                                type: 'POST',
                                url: 'ArticleController?suggest',
                                data: $("#contact").serialize(),
                                success: function(data) {
                                    if(data == "true") {
                                        $("#contact").fadeOut("fast", function(){
                                            $(this).before("<p><strong>Success! Your feedback has been sent, thanks :)</strong></p>");
                                            setTimeout("$.fancybox.close()", 1000);
                                        });
                                    }
                                }
                            });
                        }
                    });
                
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
                
                    $("#send").click(function () {
                        alert("Would submit:");
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
                            
                                console.log($(".meta-data").find("input"));
                            
                                $(".meta-data").find("input").on("click",function(e){
            
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
