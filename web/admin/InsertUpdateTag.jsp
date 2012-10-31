<%-- 
    Document   : InsertUpdateTag
    Created on : 29 Oct, 2012, 2:27:28 PM
    Author     : Nomaan
--%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                n.action='InserImage';                
                n.submit();
                }
           
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
                <li class="icn_tags"><a href="tagAddUpdateDeleteServlet">Manage Tags</a></li>
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
                            <td>Icon :</td>
                           <td><table align="center" cellspacing="2" cellpadding="2"> 
                                        <tr>
                                            <td rowspan="2">
                                                <c:if test="${name eq null}">
                                                    <img src="images/Tag/<c:out value="${entity.icon}"/>" width="45" height="45">
                                                    <input type="hidden" value="<c:out value="${entity.icon}"/>" name="icn" id="icn" >
                                                </c:if>
                                                <c:if test="${name != null}">
                                                    <img src="images/Tag/<c:out value="${name}"/>" width="45" height="45">
                                                    <input type="hidden" value="<c:out value="${name}"/>" name="icn" id="icn" >
                                                    <input type="hidden" value="<c:out value="${entity.icon}"/>" name="prev" id="prev" >
                                                </c:if>
                                            </td>
                                            <td><input type="file" name="file" size="50" id="file" /> </td>
                                        </tr>
                                        <tr>
                                            <td><input type="button" value="Upload File" onClick="frmSubmit('ins');"/> </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="color: red"><c:out value="${message}"></c:out></td>
                                        </tr>
                                    </table>
                                </td>
                        </tr>
                        <tr>
                            <td>Description :</td>
                            <td><input type="text" multiple="true" name="txtDes" value="<c:out value="${entity.description}"/>"></td>
                        <tr>
                        <tr>
                            <td colspan="2" align="center"><input type="hidden" name="tagid" value="<c:out value="${entity.tagid}"/>" id="tagid">
                                <input type="submit" value="Update"></td>
                        </tr>
                    
                    </table>
                    </form>
                            <c:set scope="session" value="${entity}" var="entity"></c:set>
                </c:if>
                <c:if test="${entity eq null}">
                 
                    <form  id="frm" name="frm" method="post" action="InsertUpdateTagServlet">
                  
                     
                        <table cellspacing="2" cellpadding="2">
                           
                            <tr>
                                <td>Name :</td>
                                <td><input type="text" name="txtName2" id="txtName2" value=""></td>
                                
                            </tr>
                           
                            <tr>
                                <td>Icon :</td>
                                <td><table align="center" cellspacing="2" cellpadding="2"> 
                                        <tr>
                                            <td rowspan="2">
                                                <c:if test="${name eq null}">
                                                    <img src="images/Tag/icn_tags.png" width="45" height="45">
                                                    <input type="hidden" value="icn_tags.png" name="icn2" id="icn2" >
                                                </c:if>
                                                <c:if test="${name != null}">
                                                    <img src="images/Tag/<c:out value="${name}"/>" width="45" height="45">
                                                    <input type="hidden" value="<c:out value="${name}"/>" name="icn2" id="icn2" >
                                                </c:if>
                                            </td>
                                            <td><input type="file" name="file" size="50" id="file" /> </td>
                                        </tr>
                                        <tr>
                                            <td><input type="button" value="Upload File" onClick="frmSubmit('up');"/> </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="color: red"><c:out value="${message}"></c:out></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>Description :</td>
                                <td><input type="text" multiple="true" name="txtDes2"></td>
                                
                            </tr>
                            
                            <tr>
                                <td colspan="2" align="center"><input type="submit" value="Insert"/></td>
                            </tr>
                       
                        </table>

                                        
                    </form>
                </c:if>
            
            </article><!-- end of article -->
                                             
                                             <c:set scope="session" var="validate">
                                                 
                                                 <json:object>
                                                 <json:property name="page" value="InsertUpdateTag"/>
                                                 <json:object name="frm">            
                                                <json:property name="txtName2" value="name"/>
                                                <json:property name="txtDes2" value="sentance"/>
                                                </json:object>
                                                
                                            </json:object> 
                                             </c:set>
        </section>
        
    </body>
</html>
