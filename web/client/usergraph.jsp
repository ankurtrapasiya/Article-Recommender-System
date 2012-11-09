<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;charset=utf-8" />
        <script src="../js/jquery-1.8.2.min.js" type="text/javascript"></script>
        <script src="../js/handlebars.js"></script>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css" />
        <link rel="stylesheet" href="../css/client/friend.css" />
        <style type="text/css">
            div.sc_menu {
                /* Set it so we could calculate the offsetLeft */
                background-color: #E0E0E3;
                position: relative;
                height:50%;
                width: 90%;
                overflow: auto;
            }
            .sc_menu textarea {
                display: block;
                height: 110px;
                /* max width here, for users without javascript */	
                width: 1500px;	
                padding: 5px 50px 0 15px; 
                /* removing default styling */
                margin: 0;
                list-style: none;
                margin-top: 3px;

                text-align: center;
                font-size: 12px;	
                color: #fff;

            }
            .sc_menu a:hover {
                display: block;
            }
            .sc_menu a:hover {
                filter:alpha(opacity=50);	
                opacity: 0.5;
            }
            /*
            .sc_menu img {
                border: 3px #fff solid;	
                -webkit-border-radius: 3px;
                -moz-border-radius: 3px;
            }
            .sc_menu a:hover img {
                filter:alpha(opacity=50);	
                opacity: 0.5;
            }*/
            .search{
                position: relative;
                height: 50%;
                width: 90%;
            }
            .searchclass textarea{
                position:relative;
                padding-left: 3px;
                -moz-border-radius: 25px;
                -webkit-border-radius: 25px;
                border-radius: 25px;    
            }
            .searchclass button{
                position:relative;
                -moz-border-radius: 25px;
                -webkit-border-radius: 25px;
                border-radius: 25px;    

            }

            .Result_List textarea{
                position:relative;
                padding-left: 3px;
                border: solid #4c95cd;
                height: 50%;
                width: 90%;
            }


            /* Here are styles for the back button, don't look at them */
            #back {
                display: block;
                width: 500px;
                text-align: center;
                color: #003469;
                font-size: 16px;
            }
        </style>


        <script>
    
            $(document).ready(function()
            {
                
               
                $.getJSON("../UserGraphController", function(dt){
                    var temp=Handlebars.compile($("#view_graph_template").html()); 
                    $("#workarea").html(temp(dt.content));
                
                
                    $("#blockuserdiv").live("click",function(e){
                        e.preventDefault();
                        var blck_id=$(this).attr("rel");
                        
                        var temp="userid="+blck_id;
                        $.post("../BlockClientUserController",temp,
                        function(){alert ("User Blocked");
                        });
                        alert("blocked");
                        if(!e.error) location.reload(true);
                                                    
                    }); 
                    $("#removeuserdiv").live("click",function(e){
                        
                        e.preventDefault();
                        
                        var remv_id=$(this).attr("rel");
                        
                        var temp="userid="+remv_id;
                        $.post("../RemoveFromGraphController",temp,
                        function(e){alert ("Removed from Graph");
                            if(!e.error) location.reload(true);
                        });
                    });
                });
               
                
                
                $("#search").click(function(){
                
               
                    var username = $("#searchuser").val();
  
                    $.trim(username);
                    alert("uu"+username+"uu");
                    if(username!=""){
                
                        $.getJSON("../SearchUserController?username="+username, function(dt){
                            var temp=Handlebars.compile($("#add_tograph_template").html());                        
                            $("#workarea").html(temp(dt.content)); 
                        });
                    }
              
                    // $("#workarea").find("button").on("click",function(e){
                    $("#adduserdiv").live("click",function(e){
                        e.preventDefault();
              
                        var add_id=$(this).attr("rel");
                        alert(add_id);
                        var temp="userid="+add_id;
                        $.post("../AddToGraphController",temp,function(e){
                            alert ("Added to graph");
                            if(!e.error) location.reload(true);
                        });
                          
                    });                                   
                }); 
                
                                  
            });
               
                    
        </script>
    </head>
    <body>
        <script id="view_graph_template" type="text/x-handlebars-template">

            <ul>
                {{#each this}} 
                <!--<li><div id="usernamediv" rel="{{userid}}">{{firstname}}{{lastname}}<div id="blockuserdiv" rel="{{userid}}"><button type="button" id="block_user" class="block_user">Block User</button></div><div id="removeuserdiv" rel="{{userid}}"><button type="button" id="removeuser" class="removeuser">Remove User</button></div></div></li>-->
                <div id="thumnails">
                    <img src="../images/{{image}}" width="150px" height="150px"/>
                    <div id="name">
                        {{firstname}}{{lastname}}
                        {{#if blocked}}
                        {{else}}
                        <div id="blockuserdiv" rel="{{userid}}"><button type="button" id="block_user" class="block_user">Block User</button></div>
                        {{/if}}
                        <div id="removeuserdiv" rel="{{userid}}"><button type="button" id="removeuser" class="removeuser">Remove User</button></div>
                    </div>
                </div>
                {{/each}}

            </ul>

        </script>

        <script id="add_tograph_template" type="text/x-handlebars-template">
            <ul>
                {{#each this}} 
                <!--<li><div id="addusertographdiv" rel="{{userid}}">{{firstname}}{{lastname}}<div id="adduserdiv" rel="{{userid}}"><button type="button" id="addtograph" class="addtograph">Add To Graph</button></div></div></li>-->
                <div id="thumnails">
                    <img src="../images/{{image}}" width="150px" height="150px"/>
                    <div id="name">
                        {{firstname}} {{lastname}}
                        {{#if isfriend}}
                        {{#if blocked}}
                        {{else}}
                        <div class="blockuserclass" id="blockuserdiv" rel="{{userid}}"><button type="button" id="block_user" class="block_user">Block User</button></div>
                        {{/if}}
                        <div id="removeuserdiv" rel="{{userid}}"><button type="button" id="removeuser" class="removeuser">Remove User</button></div>
                        {{else}}
                        <div id="adduserdiv" class="adduserdivclass" rel="{{userid}}"><button type="button" id="addtograph" class="addtograph">Add To Graph</button></div>
                        {{/if}}

                    </div>
                </div>
                {{/each}}
            </ul> 
        </script>
        <div class ="searchclass">
            <input type="text" id="searchuser"  > </input>
            <button type="button" name="search" id="search">Search </button>
        </div>
        <div id="workarea">

        </div>
    </body>
</html>