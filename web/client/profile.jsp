<%-- 
    Document   : profile
    Created on : 27 Oct, 2012, 7:07:47 AM
    Author     : rinku
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head
        <meta charset="utf-8">
        <title>surprise.me</title>
        <link media="all" rel="stylesheet" type="text/css" href="client/css/style.css" />        
        <script src="js/jquery.main.js" type="text/javascript"></script>
        <link rel="shortcut icon" href="images/webico.ico">

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/jquery-1.8.2.min.js"><\/script>')</script>
        <script type="text/javascript">
        $(document).ready(function(e)
        {
         $("#btnEdit").submit(function(e) 
         {       var btn=$("#btnEdit").val();  
                $.post("../UserProfileServlet",function(data){
                      alert("Updation done Successfully");
                });
                
         });  
          e.preventDefault();
        });  
        </script>

    </head>
    <body>
        <form name="form" method="post">
        <div id="wrapper">
            <div id="content">
                <div class="c1">
                    <div class="controls">
                        <nav class="links">
                            <ul>
                                <li><a href="client/suggestions.jsp" class="ico1">Suggestions <span class="num">26</span></a></li>
                                <li><a href="client/notifications.jsp" class="ico2">Notifications <span class="num">5</span></a></li>
                                <li><a href="client/popular.jsp" class="ico3">Popular <span class="num">3</span></a></li>
                            </ul>
                        </nav>
                        <div class="profile-box">
                            <span class="profile">
                                <a href="Logout" class="section">
                                    <img class="image" src="images/user.png" width="26" height="26" />
                                    <span class="text-box">
                                        Welcome
                                        <strong class="name">Rinku</strong>
                                    </span>
                                </a>
                                <a href="#" class="opener">opener</a>
                            </span>
                            <a href="#" class="btn-on">On</a>
                        </div>
                    </div>
                    <div class="tabs">
                        <div id="tab-1" class="tab">
                            <article>
                                <div class="text-section">
                                    <h1>Profile Page<h1>
                                            <p>     
                                               <c:if test="${requestScope.status eq 'true'}"> 
                                                <div>  User Id  </div> <div>  <input type=text name=id id="id" value=${user.userid}></div>
	                                        <div>  First name</div> <div>  <input type="text" name="Fname" value=${user.firstname}></div>
                                                <div>  Last name</div> <div>   <input type="text" name="Lname" value=${user.lastname}></div>
	                                        <div>  Email ID </div> <div>   <input type="text" name="Email" value=${user.email}></div>
	                                        <div>  DOB    </div> <div>     <input type="text" name="Dob" value=${user.dob}></div>
	                                        <div>  City    </div> <div>    <input type="text" name="City" value=${user.city}></div>
	                                        <div>  State   </div> <div>    <input type="text" name="State" value=${user.state}></div>
	                                        <div>  Country </div> <div>    <input type="text" name="Country" value=${user.country}></div>
                                                <div>  <input type="submit" value='Edit' name="btnEdit" /></div>
                                               </c:if>
                                            </p> 
                                  </div>
                                <ul class="states">
                                    <li class="error">Error : This is an error placed text message.</li>
                                    <li class="warning">Warning: This is a warning placed text message.</li>
                                    <li class="succes">Succes : This is a succes placed text message.</li>
                                </ul>
                            </article>
                        </div>                   
                    </div>
                </div>
            </div>
            <aside id="sidebar">
                <strong class="logo"><a href="#">lg</a></strong>
                <ul class="tabset buttons">
                    <li class="active">
                        <a href="client/index.jsp" class="ico1"><span>Home</span><em></em></a>
                        <span class="tooltip"><span>Dashboard</span></span>
                    </li>
                    <li>
                        <a href="/UserProfileServlet.java" class="ico2"><span>Profile</span><em></em></a>
                        <span class="tooltip"><span>Profile</span></span>
                    </li>                
                    <li>
                        <a href="client/interests.jsp" class="ico3"><span>Interests</span><em></em></a>
                        <span class="tooltip"><span>Interests</span></span>
                    </li>   
                    <li>
                        <a href="client/favourites.jsp" class="ico4"><span>Favourites</span><em></em></a>
                        <span class="tooltip"><span>Favourites</span></span>
                    </li>  
                    <li>
                        <a href="client/history.jsp" class="ico5"><span>History</span><em></em></a>
                        <span class="tooltip"><span>History</span></span>
                    </li>  
                </ul>
                <span class="shadow"></span>
            </aside>
        </div>
        </form>
    </body>
</html>







