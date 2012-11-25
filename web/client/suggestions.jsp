<%-- 
    Document   : suggestions
    Created on : 27 Oct, 2012, 7:06:33 AM
    Author     : ankur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suggestions</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
        <script src="../js/jquery-1.8.2.min.js"></script>
        <script src="../js/jquery-ui.js"></script>


        <script type="text/javascript" src="../js/handlebars.js"></script>
        
        <link rel="stylesheet" href="../css/client/suggesstion.css" type="text/css"/>
        <script type="text/javascript">
            $.fx.speeds._default = 1000;
            $(function() {
                  $( "#dialog" ).dialog({
            position:"top",

            autoOpen: false,
            show: "blind",
            
            modal:true,
            width:"600px",
            height:"150px"
            
        });
 
      /*  $( "#opener" ).click(function() {
            $( "#dialog" ).dialog( "open" );
            return false;
        });*/
                
                
                $('a[name=popup]').live("click",function(){
                    //$(".dialog")
                    
                    $(".dialog").html();
                    $(".dialog" ).dialog();
                    
                    var articleid=$(this).attr("id");
                    
                    $.ajax({
                        type: 'POST',
                        dataType: 'json',
                        url: './suggestionsController?articleid='+articleid,
                        success: function(data) {
                            
                            var temp=Handlebars.compile($("#article-template").html());                        
                            $(".dialog").html(temp(data.content));
                             $( "#dialog" ).dialog( "open" );
                       
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log(textStatus, errorThrown);
                        }
                    });
                    return false;
                });
                    
                    
                
            });    
            
        </script>      





    </head>
    <body>


        <script id="article-template" type="text/x-handlebars-template">                
            {{#each this}}    

            <div class="title">
                Title:{{title}}
                Body:{{body}}
            </div>
            {{/each}}
        </script> 

        <table>

            <c:if test="${requestScope.status eq 'true'}">    
                <div class="dialog">



                </div>
                <c:forEach var="links" items="${requestScope.user}"> 
                    <tr class="even">
                        <td>
                            <c:out value="${links.user}"></c:out> has suggested you an article at: 
                            <a id=<c:out value="${links.articleid}"></c:out> name="popup" href=<c:out value="${links.url}"></c:out>> <c:out value="${links.title}"> </c:out></a>                                                            


                        </td>
                    </tr>
                </c:forEach>
            </c:if>

        </table>



    </body>
</html>