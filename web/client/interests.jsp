<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interests</title>
        <script src="../js/jquery-1.8.2.min.js"></script>
        <script src="../js/jquery-ui.js"></script>
        <link href="../css/client/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
        <link href="../css/client/interest.css" rel="stylesheet" type="text/css"/>

        <script>
            (function($){
                $(window).load(function(){
                    $("#content_1").mCustomScrollbar({
                        horizontalScroll:true,
                        scrollButtons:{
                            enable:true,
                            scrollType:"pixels",
                            scrollAmount:116
                        }
                    });
                    $("#content_2").mCustomScrollbar({
                        horizontalScroll:true,
                        scrollButtons:{
                            enable:true,
                            scrollType:"pixels",
                            scrollAmount:116
                        },
                        callbacks:{
                            onScroll:function(){
                                snapScrollbar();
                            }
                        }
                    });
                    /* toggle buttons scroll type */
                    $("a[rel='toggle-buttons-scroll-type']").click(function(e){
                        e.preventDefault();
                        var $this=$(this);
                        var cont=$("#content_2");
                        var scrollType;
                        if(cont.data("scrollButtons-scrollType")==="pixels"){
                            scrollType="continuous";
                        }else{
                            scrollType="pixels";
                        }
                        cont.data({"scrollButtons-scrollType":scrollType}).mCustomScrollbar("update");
                        $this.toggleClass("off");
                    });
                    /* snap scrollbar fn */
                    var snapTo=[];
                    $("#content_2 .images_container img").each(function(){
                        var $this=$(this);
                        var thisX=$this.position().left;
                        snapTo.push(thisX);
                    });
                    function snapScrollbar(){
                        if(!$(document).data("mCS-is-touch-device")){ //no snapping for touch devices
                            var posX=$("#content_2 .mCSB_container").position().left;
                            var closestX=findClosest(Math.abs(posX),snapTo);
                            if(closestX===0){
                                $("#content_2").mCustomScrollbar("scrollTo","left",{
                                    callback:false //scroll to is already a callback fn
                                });
                            }else{
                                $("#content_2").mCustomScrollbar("scrollTo",closestX,{
                                    callback:false //scroll to is already a callback fn
                                });
                            }
                        }
                    }
                    function findClosest(num,arr){
                        var curr=arr[0];
                        var diff=Math.abs(num-curr);
                        for(var val=0; val<arr.length; val++){
                            var newdiff=Math.abs(num-arr[val]);
                            if(newdiff<diff){
                                diff=newdiff;
                                curr=arr[val];
                            }
                        }
                        return curr;
                    }
                });
            })(jQuery);
        </script>

        <script type="text/javascript">
            /*  function respo(data)
             {
                 var rs=data;
               if(rs)
                  {
                      alert("Deletion done Successfully");
                  }             
                  else
                      
                      {
                           console.log(rs);
                           alert("At Least one interest should be there in you account ");
                      }
             }
             */
            $(document).ready(function(e)
            {
                $("#formInterest").submit(function(e) 
                {
                
                    var btn=$("#Delete").val(); 
                    var hdn=$("#hdnInterestId").val();
                    $.post("UserInterestController",{"btnDelete":btn,"hdnInterestId":hdn},function(data){
                        /*  respo(data.status);*/
                        alert("Done");
                        $.get("UserInterestController",function(data){
                            $("#main").html(data);
                        },"html");
                    });
                    e.preventDefault();
                }); 
            
                $("#formNInterest").submit(function(e) 
                {
                    
                    var btn=$("#Add").val();  
                    var hdn=$("#hdnInterestId").val();
                    $.post("UserInterestController", {"btnAdd":btn,"hdnInterestId":hdn},function(data){
                        alert("Addition done Successfully");
                        $.get("UserInterestController",function(data){
                            $("#main").html(data);
                        },"html");
                    });
                    e.preventDefault();
              
                });
                e.preventDefault();
              
            });
              
            var x;
            function setValue(dt)
            {   x=document.getElementById("hdnInterestId");
                x.value+=dt+",";
                 
            }  
        </script>

    </head>

    <body>
        <div class="wrapper">
            <h1>Interests</h1>
            <!-- content block -->
            <div id="content_1" class="content">
                <div class="images_container">

                    <form id=formInterest name="formInterest" method="post">
                        <c:if test="${requestScope.status eq 'true'}"> 
                            <h3>In Interest List :</h3>

                            <c:forEach var="Interest" items="${requestScope.interest}">
                                <div id="col">  
                                    <img src=${Interest.iconpath} /> <br><br>
                                    <input type=checkbox value="Remove" onclick="setValue('${Interest.interestid}')" > <c:out value="${Interest.name}"></c:out>
                                    <c:out value="${Interest.description}"></c:out> 
                                        <input type="hidden" id="hdnInterestId" value="" name="hdnInterestId"/>
                                    </div>
                            </c:forEach>
                        </c:if>   
                        <input type="submit" id="Delete" name="btnDelete" value="Remove" >          
                    </form>
                </div>
            </div>
            <div id="content_1" class="content">

                <div class="images_container">

                    <form id=formNInterest name="formNInterest" method="post">
                        <c:if test="${requestScope.status eq 'true'}"> 
                            <h3>Not in Interest List :</h3>

                            <c:forEach var="Ninterest" items="${requestScope.Ninterests}"> 
                                <div id="col">  
                                    <img  src=${Interest.iconpath} /> <br><br> 
                                    <input type=checkbox value="Add" onclick="setValue('${Ninterest.interestid}')"><c:out value="${Ninterest.name}"></c:out>
                                    <br><c:out value="${Ninterest.description}"></c:out>
                                        <input type="hidden" id="hdnInterestId" value="" name="hdnInterestId"/>    
                                    </div>   
                            </c:forEach>                                         
                        </c:if>
                        <input type="submit" id="Add" name="btnAdd" value="Add" > 
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>