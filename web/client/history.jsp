<%-- 
    Document   : history
    Created on : 27 Oct, 2012, 7:11:33 AM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>

        <link rel="stylesheet" href="../css/client/tableCSS.css"> 
        <link rel="stylesheet" href="../css/client/datatable.css">

        <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script> 
        <script type="text/javascript" src="../js/jquery.dataTables.nightly.js" > </script>

        <script type="text/javascript" >
          
            $(document).ready(function() {
                $('#disp').dataTable();
                console.log("vikram")   ;
            } );
            
            function delhistory(articleid){
                $.get("UserHistoryDel", {'articleid':articleid}, function(){
                    
                    $.get("UserHistoryDisp",function(data){
                      
                        $("#table_div").html(data); 
                    });
                });
                
                
            }
            
        </script>


    </head>


    <body>

        <!--  <input type="button" value="Delete History" name="HistoryDel" >  -->

        <br><br><br>

        <div id="table_div">


            <table  border="2" id="disp" align="center">
                <thead align="center">
                <th></th>
                <th>Title</th>
                <th>Timestamp</th>
                <th>Upvote</th>
                <th>Downvote</th>
                </thead>
                <c:forEach var="ar" items="${requestScope.data}">  
                    <tr> 
                        <!-- <td> <input type="checkbox" name="selectRow"> </td> -->


                        <td><a href="javascript:void(0);" onclick="delhistory(<c:out value="${ar.articleid}"></c:out>)" >
                                    <img src="../images/delete.png" name="delImage" width="40" height="40" /> </a> 
                            </td> 


                            <td> <c:out value="${ar.title}"/> </td> 
                        <td> <c:out value="${ar.timestamp}"/> </td>     
                        <td> <c:out value="${ar.upvote}"/> </td> 
                        <td> <c:out value="${ar.downvote}"/> </td> 
                    </tr>   
                </c:forEach>  

            </table>

            <br><br>

            <!--    <input type="button" value="Delete History" name="HistoryDel">  -->

        </div>



    </body>
</html>