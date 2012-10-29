<%-- 
    Document   : tagAddUpdateDelete
    Created on : 29 Oct, 2012, 12:11:50 AM
    Author     : Nomaan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tag Manage</title>
         <link rel="stylesheet" href="admin/css/layout.css" type="text/css" media="screen" />
         <link rel="stylesheet" href="admin/css/datatable.css" type="text/css" media="screen" />
        <script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
        <script src="js/hideshow.js" type="text/javascript"></script>
        <script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/jquery.equalHeight.js"></script>
         <script type="text/javascript" src="js/jquery.dataTables.nightly.js"></script>
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
            $(document).ready( function () {
           $('#tags').dataTable({});
	} ); 
        </script>
        <script type="text/javascript">
            $(function(){
                $('.column').equalHeight();
            });
        </script>
        <script type="text/javascript">
           function tagSubmit(t,r){
                
                var f=document.getElementById("from");
                var x=document.getElementById("clicked");
                var a=document.getElementById("tagid");
                a.value=r;
                x.value=t;
                f.method="post";
                f.action='tagAddUpdateDeleteServlet';                
                f.submit();
        } 
        </script>
    </head>
    <body>
     
       <header id="header">
            <hgroup>
                <h1 class="site_title"><a href="index.html">Surpriseme</a></h1>
                <h2 class="section_title">Dashboard</h2>
            </hgroup>
        </header> <!-- end of header bar -->

        <section id="secondary_bar">
            <div class="user">
                <p>Udit (<a href="#">3 Messages</a>)</p>
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
            <h3>Users</h3>
            <ul class="toggle">
                <li class="icn_add_user"><a href="#">Block User</a></li>
            </ul>
            <h3>Article</h3>
            <ul class="toggle">
                <li class="icn_edit_article"><a href="#">Edit Articles</a></li>
                <li class="icn_tags"><a href="../tagAddUpdateDeleteServlet">Manage Tags</a></li>
            </ul>
            <h3>Interests</h3>
            <ul class="toggle">
                <li class="icn_new_article"><a href="#">Manage Interest</a></li>
            </ul>
            <h3>Sources</h3>
            <ul class="toggle">
                <li class="icn_folder"><a href="#">Manage Sources</a></li>
                </ul>
            <h3>Schedule</h3>
            <ul class="toggle">
                <li class="icn_settings"><a href="#">Manage Crawling</a></li>
            </ul>
            <ul>
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
                <header><center><h3> Tags</h3> </center>
                    
                </header>
                <table class="tablesorter" cellspacing="0" id="tags"> 
                            <thead> 
                                <tr> 
                                   
                                    <th>Tag Name</th> 
                                    <th>Icon</th> 
                                    <th>Description</th>
                                    <th>Action</th>
                                </tr> 
                            </thead> 
                            <tbody> 
                                <c:forEach var="tg" items="${requestScope.data}">
                                  
                                <tr> 
                                   
                                    <td><c:out value="${tg.name}"/></td> 
                                    <td><c:out value="${tg.icon}"/> </td> 
                                   <td><c:out value="${tg.description}"/></td> 
                                    <td><form id="from">
                                            <input type="hidden" id="clicked" value="" name="clicked">
                                            <input type="hidden" name="tagid" value="<c:out value="${tg.tagid}"/>" id="tagid">
                                            <img src="images/add.jpg" height="15" width="15" onclick="tagSubmit('add',<c:out value="${tg.tagid}"/>);"/>
                                            <img src="images/edit.jpg" height="15" width="15" onclick="tagSubmit('up',<c:out value="${tg.tagid}"/>);"/>
                                            <img src="images/delete.jpg" height="15" width="15" onclick="tagSubmit('del',<c:out value="${tg.tagid}"/>);"/>
                                        </form>
                                    </td> 
                                
                                   </tr> 
                                   </c:forEach>
                            </tbody> 
                        </table>
              
            
            </article><!-- end of article -->
           
        </section>
        
    </body>
</html>
