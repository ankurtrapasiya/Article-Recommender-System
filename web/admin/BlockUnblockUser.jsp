<%-- 
    Document   : AddBlockUser
    Created on : 25 Oct, 2012, 4:03:44 PM
    Author     : Nomaan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.surpriseme.entities.BlockedUsers"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Block Unblock User</title>
         <link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />

        <script src="../js/jquery-1.8.2.min.js" type="text/javascript"></script>
        <script src="../js/hideshow.js" type="text/javascript"></script>
        <script src="../js/jquery.tablesorter.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/jquery.equalHeight.js"></script>
        <script type="text/javascript">
            $(document).ready(function() 
            { 
                $(".tablesorter").tablesorter(); 
            } 
        );
            $(document).ready(function() {

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
        </script>
        <script type="text/javascript">
            $(function(){
                $('.column').equalHeight();
            });
        </script>
    </head>
    <body>
     
         <header id="header">
            <hgroup>
                <h1 class="site_title"><a href="index.html">Website Admin</a></h1>
                <h2 class="section_title">Dashboard</h2><div class="btn_view_site"><a href="http://www.medialoot.com">View Site</a></div>
            </hgroup>
        </header> <!-- end of header bar -->

        <section id="secondary_bar">
            <div class="user">
                <p>John Doe (<a href="#">3 Messages</a>)</p>
                <!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
            </div>
            <div class="breadcrumbs_container">
                <article class="breadcrumbs"><a href="index.html">Website Admin</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
            </div>
        </section><!-- end of secondary bar -->

        <aside id="sidebar" class="column">
            <form class="quick_search">
                <input type="text" value="Quick Search" onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
            </form>
            <hr/>
            <h3>Content</h3>
            <ul class="toggle">
                <li class="icn_new_article"><a href="#">New Article</a></li>
                <li class="icn_edit_article"><a href="#">Edit Articles</a></li>
                <li class="icn_categories"><a href="#">Categories</a></li>
                <li class="icn_tags"><a href="#">Tags</a></li>
            </ul>
            <h3>Users</h3>
            <ul class="toggle">
                <li class="icn_add_user"><a href="#">Add New User</a></li>
              <li class="icn_block_user"><a href="../BlockUnblockUserServlet">Block Users</a></li>
                <li class="icn_view_users"><a href="#">View Users</a></li>
                <li class="icn_profile"><a href="#">Your Profile</a></li>
            </ul>
            <h3>Media</h3>
            <ul class="toggle">
                <li class="icn_folder"><a href="#">File Manager</a></li>
                <li class="icn_photo"><a href="#">Gallery</a></li>
                <li class="icn_audio"><a href="#">Audio</a></li>
                <li class="icn_video"><a href="#">Video</a></li>
            </ul>
            <h3>Admin</h3>
            <ul class="toggle">
                <li class="icn_settings"><a href="#">Options</a></li>
                <li class="icn_security"><a href="#">Security</a></li>
                <li class="icn_jump_back"><a href="#">Logout</a></li>
            </ul>

            <footer>
                <hr />
                <p><strong>Copyright &copy; 2011 Website Admin</strong></p>
                <p>Theme by <a href="http://www.medialoot.com">MediaLoot</a></p>
            </footer>
        </aside><!-- end of sidebar -->

        <section id="main" class="column">

            <article class="module width_full">    
              <header><h3 class="tabs_involved">Block \ Unblock Users</h3>
                    <ul class="tabs">
                        <li><a href="#tab1">Request</a></li>
                        <li><a href="#tab2">Blocked</a></li>
                    </ul>
                </header>
                
                
                <div class="tab_container">
                   
                    <div id="tab1" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                            <thead> 
                                <tr> 
                                    
                                    <th>User Name</th> 
                                    <th>Blocker Name</th> 
                                    <th>Requested On</th> 
                                    <th>Reason</th>
                                    <th>Action</th>
                                </tr> 
                            </thead> 
                            
                            <tbody>
                                 <c:forEach var="bloc" items="${sessionScope.data}">
                                     <c:if test="${bloc.bu.isActive}">
                                <tr> 
                                   
                                    <td><c:out value="${bloc.user}"/></td> 
                                    <td><c:out value="${bloc.blocker}"/> </td> 
                                   <td><c:out value="${bloc.bu.timestamp}"/></td> 
                                    <td><c:out value="${bloc.bu.reason}"/> </td>
                                    <td><form action="../BlockUnblockUserServlet" method="post">
                                            <input type="hidden" name="userid" value="<c:out value="${bloc.bu.userid}"/>">
                                            <input type="hidden" name="blockerid" value="<c:out value="${bloc.bu.blockerid}"/>">
                                            <input type="submit" title="Block" name="toggle" value="Block"></form></td> 
                                
                                   </tr> 
                                   </c:if>
                                 </c:forEach>
                                
                            </tbody> 
                        </table>
                    </div><!-- end of #tab1 -->

                    <div id="tab2" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                            <thead> 
                                <tr> 
                                     <th>User Name</th> 
                                    <th>Blocker Name</th> 
                                    <th>Blocked On</th> 
                                    <th>Reason</th>
                                    <th>Action</th>
                                </tr> 
                            </thead> 
                            <tbody> 
                                 <c:forEach var="bloc" items="${sessionScope.data}">
                                    <c:if test="${!bloc.bu.isActive}">
                                <tr> 
                                   
                                    <td><c:out value="${bloc.user}"/></td> 
                                    <td><c:out value="${bloc.blocker}"/> </td> 
                                   <td><c:out value="${bloc.bu.timestamp}"/></td> 
                                    <td><c:out value="${bloc.bu.reason}"/> </td>
                                    <td><form action="../BlockUnblockUserServlet" method="post">
                                            <input type="hidden" name="userid" value="<c:out value="${bloc.bu.userid}"/>">
                                            <input type="hidden" name="blockerid" value="<c:out value="${bloc.bu.blockerid}"/>">
                                            <input type="submit" title="UnBlock" name="toggle" value="Unblock"></form></td> 
                                
                                   </tr> 
                                   </c:if>
                                 </c:forEach>
                            </tbody> 
                        </table>

                    </div><!-- end of #tab2 -->
                   
                </div><!-- end of .tab_container -->
            
            </article><!-- end of article -->
           
        </section>


        
        
    </body>
</html>
