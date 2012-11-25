

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.surpriseme.controllers.admin.*;" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/articleandtagmanagement.css" type="text/css"/>
        <link rel="stylesheet" href="css/datatable.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article Management</title>
        <script  src="../js/jquery-1.8.2.min.js" type="text/javascript"></script>
        <script  src="../js/handlebars.js" type="text/javascript"></script>
        <script src="../js/jquery.dataTables.nightly.js" type="text/javascript"></script>
        <script src="../js/hideshow.js" type="text/javascript"></script>
        <script src="../js/jquery.tablesorter.min.js" type="text/javascript"></script>
        <script  src="../js/jquery.equalHeight.js" type="text/javascript"></script>


        <script type="text/javascript">
            var art;
            $(document).ready(function(){
                
               
                $.getJSON("../ListArticles",function(dt){
                    console.log(dt);
                    var temp=Handlebars.compile($("#script1").html());
                    $("#test2").html(temp(dt.article));
                    $("alltags").html("");
                    $("articletag").html("");
                    $('#articletable').dataTable({});
                    $('.column').equalHeight();
                    $(".tablesorter").tablesorter();
                });
                
                
                //When page loads...
                $(".tab_content").hide(); //Hide all content
                $("ul.tabs li:first").addClass("active").show(); //Activate first tab
                $(".tab_content:first").show(); //Show first tab content

                //On Click Event
                $("ul.tabs li").click(function() {

                    $("ul.tabs li").removeClass("active"); //Remove any "active" class
                    $(this).addClass("active"); //Add "active" class to selected tab
                    $(".tab_content").hide(); //Hide all tab content

                    var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
                    $(activeTab).fadeIn(); //Fade in the active ID content
                    return false;
                });
                
                
                
            });
            
            function edittags(articleid)
            {
                $.getJSON("../ManageTags", {"articleid":articleid},function(dt){
                    console.log(dt);
                    art=articleid;
                    $left=$("#left");
                    $left.empty();
				
                    for ( var index in dt.tags[0].alltags) {
                        $left.append(
                        new Option(dt.tags[0].alltags[index].title,dt.tags[0].alltags[index].id));
                                                     
                    }
                    $right=$("#right");
                    $right.empty();
                    for(var index in dt.tags[1].articletags){
                        $right.append(new Option(dt.tags[1].articletags[index].title,dt.tags[1].articletags[index].id));
                    }
                            
                });
                        
                    
             
                return false;
                
            }
            
            function lefttoright()
            {
            
                $left=$("#left");
                $right = $("#right");
                $("#left option:selected").each(function(){
                    $right.append(new Option(this.text,this.value));
                    $(this).remove();
                });
            }
            
            function righttoleft()
            {
                $left=$("#left");
                $right = $("#right");
                $("#right option:selected").each(function(){
                    $left.append(new Option(this.text,this.value));
                    $(this).remove();
                });
            }
            function shiftrightall()
            {
                $left=$("#left");
                $right = $("#right");
                $("#left option").each(function(){
                    $right.append(new Option(this.text,this.value));
                    $(this).remove();
                });
            }
            function shiftleftall()
            {
                
                $left=$("#left");
                $right = $("#right");
                $("#right option").each(function(){
                    $left.append(new Option(this.text,this.value));
                    $(this).remove();
                });
                
            }
            function applychanges()
            {
                
                var z=new Array();
                var x = new Array();    
                var i =0;
                $left=$("#left");
                $right = $("#right");
                $("#right option").each(function(){
                    x[i]=this.value;
                    z[i]=this.text;    
                             
                    i++;
                });
                var temp=x;
                $.getJSON("../SaveTags",{"id":art,"tags":temp.join(','),"tagid":z.join(',')},function(data){
               
                });
            }
            
                
            $('a[name=view]').live("click",function(){
               
               
                if($(this).parent().next().is(":hidden"))
                {
                    $th=$(this);
                    $.getJSON("../ArticleTag", {"id":$(this).attr("id")}, function(data){
                        console.log(data);
                        temp=Handlebars.compile($("#script2").html());
                        name=temp(data.tags);
                        $($th).parent().next().html("");
                        $($th).parent().next().html(name);//2601772869
                    });
     
 
                    $(this).text("View Less");
               
                
                }
                else
                {
                    $(this).text("View More");
                }
                $(this).parent().next().toggle(500);
                //$("articleid").append(id);
                                   
                    
               
                
            });
           
            
        </script>
    </head>
    <script id="script1" type="text/x-handlebars-template">

        <article class="module width_full">
            <div class="tab_container">

                <div id="tab1" class="tab_content">
                    <header><center><h3> Articles</h3> </center></header>

                    <table class="tablesorter" cellspacing="0" id="articletable"> 
                        <thead> 
                            <tr> 

                                <th>Title</th> 
                                <th>Edit</th> 
                                <th>View</th> 
                            </tr> 
                        </thead> 

                        <tbody>
                            {{#each this}}
                            <tr> 

                                <td>{{title}}</td> 
                                <td><a href="#" id='edit"{{id}}"' onclick='edittags("{{id}}")'>Edit</a> </td> 
                                <td> <a href="#" name="view" id="{{id}}" class="{{id}}"  >View More</a></td> 
                            </tr> 
                            {{/each}}

                        </tbody> 
                    </table>
                </div><!-- end of #tab1 -->

            </div>

        </script>
        <script id="script2" type="text/x-handlebars-template">


            {{#each this}}

            {{title}}
            <br/>
            <br/>




            {{/each}}

        </script>

        <body>
            <a href="#" id="articles"></a>

            <div id="test2">

            </div>
        <center><article class="module width_full">
                <div id="container">
                <div id="alltags"><select name="left" id="left" multiple="multiple" size="10" ></select></div>
                <div id="articletags"><select name="right" id="right" multiple="multiple" size="10"></select></div>
                <div id="buttons">

                    <input type="button" value=">>" onclick="shiftrightall()"></input>
                    <input type="button" value=">" onclick="lefttoright()"></input>
                    <input type="button" value="<" onclick="righttoleft()"></input>
                    <input type="button" value="<<" onclick="shiftleftall()"></input>
                    <input type="button" value="Save" onclick="applychanges()"/>        
                </div>
            </div>
            </article></center>
        </body>
</html>