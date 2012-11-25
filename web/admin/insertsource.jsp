<%-- 
    Document   : SecondSourceForm
    Created on : Oct 31, 2012, 12:35:17 PM
    Author     : Priyank
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Panel | Dashboard | Surpriseme</title>

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
            
            $(document).ready( function () {
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
        <section id="main" class="column" >
            <article class="module width_full">
                <header><h3>SOURCES</h3>     <input type="button" value="Insert" style="float:  right"></header>

                <div class="module_content">
                    <form action="SourceInsert" method="post" id="frm">
                        Name:           <input type="text" name="txtname"><br>
                        Url    :           <input type="text" name="txturl"><br>
                        FeedUrl:      <input type="text" name="txtfeedurl"><br>

                        <input type="hidden" value="icn_source.png" name="Icon" /> <br><br>
                        <h4>IsActive</h4>
                        <input type="radio" name="rbactive" value="True">True<br>
                        <input type="radio" name="rbactive" value="False">False<br>  


                        <br>  <input type="submit" value="Submit" name="btnsubmit" >
                    </form>
                </div>
            </article><!-- end of styles article -->
            <div class="spacer"></div>
        </section>
    </body>
</html>