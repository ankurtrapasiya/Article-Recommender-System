<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Crawling</title>
        <script src="../js/jquery-1.8.2.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/handlebars.js"></script>
        <script type="text/javascript">
            var addedsources=false;
            if(addedsources==false){
                $.getJSON("../SchedularController?action=getall", function(data){
                    var temp=Handlebars.compile($("#source-template").html());                        
                    $("#selectsources").html(temp(data));
                    addedsources=true;
                });                
            }
            (function(){
                $("#sources").live("change",function(){
                    var sourcename=$('#sources').attr("value"); 
                    $.getJSON("../SchedularController?action=getfeedurls&sourcename="+sourcename,function(data){
                        var temp=Handlebars.compile($("#feedurltable-template").html());                        
                        $("#feedtable").html(temp(data));  
                    }); 
                });
            })();
            
            function startCrawling(form){
                var haserror=false;
                var crawltime=form.crawltime.value.trim();
                var pattern=form.pattern.value.trim();
                var feedurl=form.feedurlhidden.value.trim();
                if((crawltime=="")||(crawltime==0)){
                    alert("Crawltime cannot be blank or zero");
                    form.crawltime.value=""; 
                    haserror=true;
                }else if(pattern==""){
                    alert("Pattern cannot be blank or zero");
                    form.pattern.value=""; 
                    haserror=true;
                }
                alert(crawltime);
                alert(pattern);
                alert(feedurl);
                var datastring="action=startcrawling&feedurl="+feedurl+"&crawltime="+crawltime+"&pattern="+pattern;
                if(haserror==false){
                    $.post("../SchedularController",datastring,function(dt){
                        getSources();
                    
                    });
                }
            
            }
            function getSources(){
                $("#feedtable").html("");
                $.getJSON("../SchedularController?action=getall", function(data){
                    var temp=Handlebars.compile($("#source-template").html());                        
                    $("#selectsources").html(temp(data));
                    addedsources=true;
                });    
            }             
        </script>
    </head>
    <body>
        <script id="source-template" type="text/x-handlebars-template">                
            <ul>
                <select id="sources">
                    <option value="Select Source Name">Select Source Name</option>
                    {{#each this}}                    
                    <option value="{{name}}">{{name}}</option>                        
                    {{/each}}
                </select>
            </ul>                
        </script>

        <script id="feedurltable-template" type="text/x-handlebars-template">                
            <div id="tab1" class="tab_content" style="display: block; ">
                <table class="tablesorter" cellspacing="0">
                    <thead>
                        <tr>
                            <th class="header">Feed Url</th>
                            <th class="header">Crawl Duration</th>
                            <th class="header">Pattern</th>
                            <th class="header">Action</th>
                        </tr>
                    </thead>
                </table>
            </div>

            {{#each this}}                    

            <form id="feeddiv" class="feedurlform">
                <a href="{{feedurl}}" id="feedurl" target="blank" value="{{feedurl}}">{{feedurl}}</a>
                <input type="hidden" id="feedurlhidden" name="feedurlhidden" value="{{feedurl}}"/>
                <input type="text" id="crawltime" name="crawltime"/>
                <input type="text" id="pattern" name="pattern"/>
                <input type="button" id="start" name="start"  value="Start" onclick="startCrawling(this.form)"/>
            </form>

            {{/each}}


        </script>

        <article id="feedurltable" class=\"module width_full\">
                 <header><h2>Schedular</h2></header>
            <div id="selectsources"></div>
            <div id="feedtable"><ul>

                </ul>   
            </div>

        </article>
    </body>
</html>
