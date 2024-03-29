<%-- 
    Document   : InsertUpdateTag
    Created on : 29 Oct, 2012, 2:27:28 PM
    Author     : Nomaan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/datatable.css" type="text/css" media="screen" />
        <script src="../js/jquery-1.8.2.min.js" type="text/javascript"></script>
        <script src="../js/hideshow.js" type="text/javascript"></script>
        <script src="../js/jquery.tablesorter.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/jquery.equalHeight.js"></script>
        <script type="text/javascript" src="../js/jquery.dataTables.nightly.js"></script>
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
            function frmSubmit(t){
             
                if(t == "ins"){
                    var k=document.getElementById("frmup");
                    k.method="post";
                    k.enctype="multipart/form-data";
                    k.action='InserImage';                 
                    k.submit();
                }
                else{
                    
                    var n=document.getElementById("frm");
                    n.method="post";
                    n.enctype="multipart/form-data";
                    n.action='InsertImage';                
                    n.submit();
                }
           
            } 
         
            //Sidebar clicks
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

        <section id="main" class="column">

            <article class="module width_full">    
                <header><center><h3><c:if test="${entity != null}">Update Tag</c:if>
                            <c:if test="${entity eq null}">Insert Tag</c:if></h3> </center>

                </header>
                <c:if test="${entity != null}">
                    <form method="post" action="InsertUpdateTagServlet" id="frmup">
                        <table cellpadding="10" cellspacing="">

                            <tr>
                                <td>Name :</td>
                                <td><input type="text" value="<c:out value="${entity.name}"/>" name="txtName">
                                </td>
                            </tr>

                            <tr>
                                <td>Description :</td>
                                <td><input type="text" multiple="true" name="txtDes" value="<c:out value="${entity.description}"/>"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input type="hidden" name="tagid" value="<c:out value="${entity.tagid}"/>" id="tagid">
                                    <input type="hidden" name="icn" value="<c:out value="${entity.icon}"/>" id="icn">
                                    <input type="submit" value="Update"></td>
                            </tr>

                        </table>
                    </form>

                </c:if>
                <c:if test="${entity eq null}">

                    <form  id="frm" name="frm" method="post" action="InsertUpdateTagServlet">


                        <table cellspacing="2" cellpadding="2">

                            <tr>
                                <td>Name :</td>
                                <td><input type="text" name="txtName2" id="txtName2" value=""></td>

                            </tr>


                            <tr>
                                <td>Description :</td>
                                <td><input type="text" multiple="true" name="txtDes2"></td>

                            </tr>

                            <tr>
                                <td colspan="2" align="center">
                                    <input type="hidden" name="icn2" value="icn_tags.png" id="icn2">
                                    <input type="submit" value="Insert"/></td>
                            </tr>

                        </table>


                    </form>
                </c:if>

            </article><!-- end of article -->

        </section>

    </body>
</html>