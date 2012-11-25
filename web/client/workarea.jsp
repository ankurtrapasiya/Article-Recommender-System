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
        <script type="text/javascript">
            
            
            function setid(id)
            {
                document.getElementById("hdnArticleId").value=id;
            }
            
        </script>


        <link rel="stylesheet" type="text/css" media="all" href="../css/popup/jquery.fancybox.css">
        <script type="text/javascript" src="../js/jquery.fancybox.js?v=2.0.6"></script>
        <script type="text/javascript" src="../js/jquery.tokeninput.js"></script>

        <link rel="stylesheet" href="../css/client/token-input.css" type="text/css" />
        <link rel="stylesheet" href="../css/client/token-input-facebook.css" type="text/css" />
        <link rel="stylesheet" href="../css/client/artice-page-style.css" type="text/css" />

    </head>
    <body>
        <!-- hidden inline form -->

        <input type="hidden" id="hdnArticleId" value="0"/>
        <div id="inline">
            <h2>Suggest A Friend</h2>

            <form id="suggestionform" name="suggestionform" action="#" method="post">
                <label class="label-popup" for="demo-input-local">Enter Friend Names</label>
                <textarea id="demo-input-local" name="demo-input-local" cols="100" rows="1" /></textarea>
                <br>
                <div id="popup-message"></div>
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
                    <label class="views">{{viewed}} views</label>
                </div>

                <div class="meta-data">

                    <p class="tags">
                        Tags : 
                        {{#tags}}

                        <a href="#" class="tag-link">{{name}},</a>

                        {{/tags}}
                    </p>
                    <div  class="meta-links">
                        <span>Source : {{#sources}} <a href="{{source}}" target="_blank" class="tag-link">{{source}}</a> {{/sources}}</span>
                        <span class="alignright">Posted On : {{publicationdate}}</span>
                    </div>
                    <div class="meta-links">
                        <span><input id="addtofav" type="submit" data="{{articleid}}" value="Add to Favorite" /></span>                        
                        <span><a class="modalbox special" href="#inline" onclick="setid({{articleid}});" >suggest to a friend</a></span>
                        <span class="alignright"><input id="readlater" data="{{articleid}}" type="submit" value="Read Later" /></span>
                    </div>
                    <div id="article_content">
                        {{body}}
                        <div id="viewmore">
                            <a href="#" class="more-link" name="slide">View More</a>
                        </div>
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


        <script type="text/javascript" src="../js/articlescript.js"></script>

    </body>
</html>
