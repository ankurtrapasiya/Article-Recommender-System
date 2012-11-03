<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interests</title>
        
         <script src="js/jquery.main.js" type="text/javascript"></script>
         <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        
         <script type="text/javascript">
            
             $(document).ready(function(e)
             {
                  $("#btnDelete").onclick(function(e) 
                  {           
                      $.post("../UserInterestServlet",function(data){
                      alert("Updation done Successfully");
                  }); });
            
          
                  $("#btnAdd").onclick(function(e) 
                  {        
                     
                      $.post("../UserInterestServlet", {"btnAdd":"do"},function(data){
                      alert("Addition done Successfully");
                  });});
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
  <form name=form method="post">

    <h1> Interest List page here </h1>

 
<c:if test="${requestScope.status eq 'true'}"> 

    <h3>Interest List :</h3>
  <table border=2>
      <th>Status</th><th>Interest Name</th><th>Interest Description</th>
      <c:forEach var="Interest" items="${requestScope.interest}">
         <tr>  
         <td><c:out value="${Interest.interestid}"> </c:out></td> 
         <td> <c:out value="${Interest.name}"></c:out></td>
         <td><input type="text" name="Descriptionname" value=${Interest.description}></td>
         <td><input type=checkbox value="Remove" onclick="setValue('${Interest.interestid}')" id="${Interest.interestid}"></td>
         
         </tr>
         <input type="hidden" id="hdnInterestId" value="" name="hdnInterestId"/>
     </c:forEach>	
  </table>
  
     
     <input type="submit" name="btnDelete" value="Remove" >
     <br><br>
     
     <h3>Not Interest List :</h3>
  <table border=2>
    
     <th>Status</th><th>Interest Name</th><th>Interest Description</th>
     <c:forEach var="Ninterest" items="${requestScope.Ninterests}">  
     <tr> 
         <td><c:out value="${Ninterest.interestid}"> </c:out></td> 
         <td> <c:out value="${Ninterest.name}"></c:out> </td>
         <td><input type="text" name="Descriptionname" value="${Ninterest.description}"></td>
         <td><input type=checkbox value="Add" onclick="setValue('${Ninterest.interestid}')" id="${Ninterest.interestid}"></td>
     </tr>    
     </c:forEach>
  </table>
     
   <input type="hidden" id="hdnInterestId" value="" name="hdnInterestId"/>
   <input type="submit" name="btnAdd" value="Add Selected" > 
   
</c:if>

<c:if test="${requestScope.status eq 'Costrain'}">
    <h1>  at least one interest sould be there </h1>
</c:if>

<c:if test="${requestScope.status eq 'false'}">
    <h1> no data found  </h1>
</c:if>

  </form>
 </body>
</html>
