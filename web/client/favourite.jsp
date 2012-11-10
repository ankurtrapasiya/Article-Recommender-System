<%-- 
    Document   : Favourites
    Created on : 27 Oct, 2012, 7:10:15 AM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>jQuery UI Dialog - Animation</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>

        <style type="text/css">

            #workarea{
                width:100%;
                height:100px;

            }
            #row{
                width:100%;
            }
            a{
                width:80%;
                margin:5px;
                padding:5px;
                float:left;
            }
            .even{
                background-color: #F5F5F5;                               
            }

            .even a
            {
                text-align: center;
            }

            .odd
            {
                background-color: #FFFFFF;

            }
            #row #details{
                width:60%;
                float:left;
                word-wrap:break-word;
            }
            #row #title{
                width:20%;
                float:left;
                word-wrap:break-word;
            }
            #row #view{
                float:left;
                width:10%;
                word-wrap:break-word;
            }
            #row #delete{
                float:left;
                width:10%;
                word-wrap:break-word;

            }


        </style>
    </head>
    <body>

        <script id="favourites-template" type="text/x-handlebars-template">                

            {{#each this}}                    
            <div id="row">
                <div id="details" class="even">
                    <a id="opener" href="#" >{{title}}</a>
                </div>                     
                <div id="delete" class="even">
                    <a id="opener" href="#"  rel="{{articleid}}"><img src="../images/ico-delete.gif"/></a>
                </div>        
            </div>
            {{/each}}

        </script> 



        <div id="workarea">

        </div>
        <script type="text/javascript">
            (function(){
                $.getJSON("FavouritesController", function(dt){

                    var temp=Handlebars.compile($("#favourites-template").html());                        
                    $("#workarea").html(temp(dt.content));
                    
                    $("#workarea").find("a").on("click",function(e){
                        e.preventDefault();
                        
                        var articleid=$(this).attr("rel");
                        
                        alert(articleid);
                        
                        $.ajax({
                            type: 'POST',
                            url: 'FavouritesController',
                            data: {                                
                                'articleid':articleid
                            },
                            success: function(data) {
                                
                                var temp=Handlebars.compile($("#favourites-template").html());                        
                                $("#workarea").html(temp(dt.content));
                                
                            }
                        });
                    });  
                })
            })();
        </script>
    </body>
</html>
