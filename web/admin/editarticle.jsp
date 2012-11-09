

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.surpriseme.controllers.admin.*;" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/articleandtagmanagement.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article Management</title>
        <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="../js/handlebars.js"></script>

        <script type="text/javascript">
            var art;
            $(document).ready(function(){
                
                $.getJSON("../ListArticles",function(dt){
                    console.log(dt);
                    var temp=Handlebars.compile($("#script1").html());
                    $("#test2").html(temp(dt.article));
                    $("alltags").html("");
                    $("articletag").html("");
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
                            
                    /* while(i<size)
                            {
                                if($left[0].selectedIndex==i)
                                    {
                                        alert($left[0].value);
                                        $right.append(new Option($("#left:..000")));
                                    }
                                    i++;
                            }*/
                    // $left[0].selectedIndex=2;
                    //if($left[0].selectedIndex!=2)
                    //alert($left[0].selectedIndex); 
                            
                });
                        
                    
                    
                    
                /*
                    var temp=Handlebars.compile($("#script3").html());
                    $("#test2").html("");
                    var x=dt.tags[0].alltags;
                    console.log(x);
                    
                    var alltags=temp(dt.tags[0].alltags);
                    alert(alltags);
                    var articletag=temp(dt.tags[1].articletags);
                   // $("#alltags").jqxListBox({allowDrop:true,allowDrag:true,source:x,width:200,height:200});
                   // $("#jqxWidget").jqxListBox({ source: x, displayMember: "title", valueMember: "desc", width: 200, height: 250});
                    $("#alltags").html(alltags);
                    $("#articletag").html(articletag);
                    $("#buttons").show();
                    
                        
                });*/
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
        {{#each this}}
        <div id="main">
            <div id="title">
                {{title}}
            </div><br/>
            <div id="body">
                {{body}}
            </div><br/>
            <div id="link">
                <a href="#" id='edit"{{id}}"' onclick='edittags("{{id}}")'>Edit</a>

                <a href="#" name="view" id="{{id}}" class="{{id}}"  >View More</a>
            </div><br/>
            <div style="display:none" id="inner"></div>
        </div>
        {{/each}}

    </script>
    <!--<script id="script3" type="text/x-handlebars-template" >
        
        {{#each this}}
        <option>{{title}}</option>

        {{/each}}
        



    </script>-->
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

    </body>
</html>