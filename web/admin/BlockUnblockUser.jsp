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
        <link rel="stylesheet" href="css/datatable.css" type="text/css" media="screen" />
        <script src="../js/jquery-1.8.2.min.js" type="text/javascript"></script>
        <script src="../js/hideshow.js" type="text/javascript"></script>
        <script src="../js/jquery.dataTables.nightly.js" type="text/javascript"></script>
        <script src="../js/jquery.tablesorter.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/jquery.equalHeight.js"></script>
        <script src="../js/jquery.tablesorter.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/jquery.equalHeight.js"></script>
        <script type="text/javascript">
            $(document).ready(function() 
            { 
                $(".tablesorter").tablesorter();
                
                $('#request').dataTable({});
                $('#block').dataTable({});
	
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
                          //Sidebar clicks
                 $("#edit_article").click(function(){                
                    $.get("editarticle.jsp",function(data){
                        $("#main").html(data);
                    });
                });
                 $("#manage_crawling").click(function(){                
                    $.get("managecrawling.jsp",function(data){
                        $("#main").html(data);
                    });
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
                <h1 class="site_title"><a href="index.jsp">Surpriseme</a></h1>
                <h2 class="section_title">Dashboard</h2>
            </hgroup>
        </header> <!-- end of header bar -->

        <section id="secondary_bar">
            <div class="user">
                <p>${sessionScope.user.firstname}</p>
                <!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
            </div>
            <div class="breadcrumbs_container">
                <article class="breadcrumbs"><a href="index.jsp">Website Admin</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
            </div>
        </section><!-- end of secondary bar -->

        <aside id="sidebar" class="column">
            <h3>Users</h3>
            <ul class="toggle">
                <li class="icn_add_user"><a id="blockuser" href="BlockUser">Block User</a></li>
            </ul>
            <h3>Article</h3>
            <ul class="toggle">
                <li class="icn_edit_article"><a id="edit_article" href="#">Edit Articles</a></li>
                <li class="icn_tags"><a id="manage_tags" href="tagAddUpdateDeleteServlet">Manage Tags</a></li>
            </ul>
            <h3>Interests</h3>
            <ul class="toggle">
                <li class="icn_new_article"><a href="InterestController">Manage Interest</a></li>
            </ul>
            <h3>Sources</h3>
            <ul class="toggle">
                <li class="icn_folder"><a href="SourceController">Manage Sources</a></li>
                </ul>
            <h3>Schedule</h3>
            <ul class="toggle">
                <li class="icn_settings"><a id="manage_crawling" href="#">Manage Crawling</a></li>
            </ul>
            <ul>
                <li class="icn_jump_back"><a href="LogoutServlet">Logout</a></li>
            </ul>

            <footer>
                <hr />
                <p><strong>Copyright &copy; 2012 Website Admin</strong></p>
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
                        <table class="tablesorter" cellspacing="0" id="request"> 
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
                                            <td><form action="BlockUnblockUserServlet" method="post">
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
                        <table class="tablesorter" cellspacing="0" id="block"> 
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
                                            <td><form action="BlockUnblockUserServlet" method="post">
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
