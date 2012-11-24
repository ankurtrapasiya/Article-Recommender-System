<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Surprise Me</title>
    <link media="all" rel="stylesheet" type="text/css" href="../css/client/style.css" />
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript">window.jQuery || document.write('<script type="text/javascript" src="../js/jquery-1.8.2.min.js"><\/script>');</script>
    <script type="text/javascript" src="../js/jquery.main.js"></script>
    <script type="text/javascript" src="../js/handlebars.js"></script>


    <!--[if lt IE 9]><link rel="stylesheet" type="text/css" href="css/ie.css" /><![endif]-->

    <script type="text/javascript">
        $(document).ready(function()
        {
            $("#main").load("workarea.jsp");
            
            $("#home").click(function(){                
                $.get("workarea.jsp",function(data){                   
                    $("#main").html(data);
                },"html");
            });
            
            $("#profile").click(function(){                
                $.get("UserProfileController",function(data){
                    $("#main").html(data);
                },"html");
            });       

            $("#interest").click(function(){                
                $.get("interests.jsp",function(data){
                    $("#main").html(data);
                },"html");
            });
            
            $("#favourite").click(function(){                
                $.get("favourite.jsp",function(data){
                    alert("hey");
                    $("#main").html(data);
                },"html");
            });
            
            $("#history").click(function(){                
                $.get("UserHistoryDisp",function(data){
                    $("#main").html(data);
                },"html");
            });                           
                        
            $("#usergraph").click(function(){                
                $.get("usergraph.jsp",function(data){
                    $("#main").html(data);
                },"html");
            });    
                   
            $("#search").click(function(){                
                $.get("./Search",function(data){
                    alert("hello");
                    $("#main").html(data);
                },"html");
            }); 
            
            $("#btnSearch").click(function(){                
                $.get("./Search",function(data){
                    $("#main").html(data);
                },"html");
            });
                   
        });
    </script>

</head>
<body>
    <div id="wrapper">
        <div id="content">
            <div class="c1">
                <div class="controls">
                    <nav class="links">
                        <ul>

                            <!-- -->
<!--                            <li>

                                <form method="get" action="Search">
                                    <input type="text" style="height: 20px;" name="txtKeyword"/>
                                    <input id="btnSearch" type="submit" style="height: 20px;" value="Search"/>
                                </form>
                            </li>-->
                            <!-- -->

                            <li><a href="#" class="ico1">Suggestions <span class="num">${sessionScope.suggestions}</span></a></li>
                            <li><a href="NotificationsServlet" class="ico2">Notifications <span class="num">${sessionScope.notifications}</span></a></li>
                            <li><a id="favourite" href="#tab-1" class="ico3">Favorites</a></li>
                            <li><a href="#" class="ico3">Popular</a></li>
                        </ul>
                    </nav>
                    <div class="profile-box">
                        <span class="profile">
                            <a href="#" class="section">
                                <img class="image" src="../images/user.png" alt="image description" width="26" height="26" />
                                <span class="text-box">
                                    Welcome
                                    <strong class="name">${sessionScope.user.firstname}</strong>
                                </span>
                            </a>                            
                        </span>
                        <a href="../LoginController?signout=true" id="btnSignOut" class="btn-on">Sign Out</a>
                    </div>
                </div>                
                <div id="at_workarea">
                    <div id="main" class="main">	                                                
                    </div>
                </div>
            </div>           
        </div>
        <aside id="sidebar">
            <strong class="logo"><a href="#">lg</a></strong>
            <ul class="tabset buttons">
                <li class="active">
                    <a href="#tab-1" class="ico1" id="home"><span>Home</span><em></em></a>
                    <span class="tooltip"><span>Home</span></span>
                </li>
                <li>
                    <a href="#tab-1" class="ico2" id="profile"><span>Profile</span><em></em></a>
                    <span class="tooltip"><span>Profile</span></span>
                </li>
                <li>
                    <a href="#tab-1" class="ico3" id="interest"><span>Interests</span><em></em></a>
                    <span class="tooltip"><span>Interests</span></span>
                </li>
                <li>
                    <a href="#tab-1" class="ico4" id="usergraph"><span>My graph</span><em></em></a>
                    <span class="tooltip"><span>My graph</span></span>
                </li>
                <!--                <li>
                                    <a href="#tab-1" class="ico4" id="favourite"><span>Favourites</span><em></em></a>
                                    <span class="tooltip"><span>Favourites</span></span>
                                </li>-->
                <li>
                    <a href="#tab-1" class="ico5" id="history"><span>History</span><em></em></a>
                    <span class="tooltip"><span>History</span></span>
                </li>               
            </ul>
            <span class="shadow"></span>
        </aside>
        <!--
                <div id="maincontainer">
                    <div id="maincontent">
                        <div id="row">
                            <div id="content_votes">
                                <a href="#" id="up"><img src="images/arrow-up.png"></a>
                                <a href="#" id="down"><img src="images/arrow-down.png"></a>
        
        
                            </div>
                            <div id="content_title">
                                <h3> This is the demo Title</h3>
                            </div>
                            <div id="content_info">
                                <div id="content_upload_date">
                                    <h3>Upload Date: 12/12/12 </h3>
                                </div>
                                <div id="content_links">
                                </div>
                            </div>
                            <div id="content_body">
                                On the web, it?s impossible to maintain the fiction that you can gather a single public together in one place. There?s always going to be one link further that you never explored, or one site that is totally different from you. And I think one of the things that the web does to journalism is that it gives lie to the notion that journalism can ever represent ?the public.?
                                <div id="viewmore">
                                    <a href="#" name="slide">View More</a>
                                </div>
                            </div>
        
                        </div>
                        <div  id="slide">Java is a set of several computer software products and specifications from Sun Microsystems (which has since merged with Oracle Corporation), that together provide a system for developing application software and deploying it in a cross-platform computing environment. Java is used in a wide variety of computing platforms from embedded devices and mobile phones on the low end, to enterprise servers and supercomputers on the high end. While less common on desktop computers, Java applets are sometimes used to provide improved and secure functions while browsing the World Wide Web.</div>			
        
                        <div id="row">
                            <div id="content_votes">
                                <a href="#" id="up"><img src="images/arrow-up.png"></a>
                                <a href="#" id="down"><img src="images/arrow-down.png"></a>
        
                            </div>
                            <div id="content_title">
                                <h3> This is the demo Title</h3>
                            </div>
                            <div id="content_info">
                                <div id="content_upload_date">
                                    <h3>Upload Date: 12/12/12 </h3>
                                </div>
                                <div id="content_links">
                                </div>
                            </div>
                            <div id="content_body">
                                On the web, it?s impossible to maintain the fiction that you can gather a single public together in one place. There?s always going to be one link further that you never explored, or one site that is totally different from you. And I think one of the things that the web does to journalism is that it gives lie to the notion that journalism can ever represent ?the public.?
                                <div id="viewmore">
                                    <a href="#" name="slide">View More</a>
                                </div>
        
                            </div>
                        </div>
        
                        <div  id="slide">Java is a set of several computer software products and specifications from Sun Microsystems (which has since merged with Oracle Corporation), that together provide a system for developing application software and deploying it in a cross-platform computing environment. Java is used in a wide variety of computing platforms from embedded devices and mobile phones on the low end, to enterprise servers and supercomputers on the high end. While less common on desktop computers, Java applets are sometimes used to provide improved and secure functions while browsing the World Wide Web.</div>
                        <div id="row">
                            <div id="content_votes">
                                <a href="#" id="up"><img src="images/arrow-up.png"></a>
                                <a href="#" id="down"><img src="images/arrow-down.png"></a>
        
                            </div>
                            <div id="content_title">
                                <h3> This is the demo Title</h3>
                            </div>
                            <div id="content_info">
                                <div id="content_upload_date">
                                    <h3>Upload Date: 12/12/12 </h3>
                                </div>
                                <div id="content_links">
                                </div>
                            </div>
                            <div id="content_body">
                                On the web, it?s impossible to maintain the fiction that you can gather a single public together in one place. There?s always going to be one link further that you never explored, or one site that is totally different from you. And I think one of the things that the web does to journalism is that it gives lie to the notion that journalism can ever represent ?the public.?
                                <div id="viewmore">
                                    <a href="#" name="slide">View More</a>
                                </div>
                            </div>
                        </div>
        
                        <div  id="slide">Java is a set of several computer software products and specifications from Sun Microsystems (which has since merged with Oracle Corporation), that together provide a system for developing application software and deploying it in a cross-platform computing environment. Java is used in a wide variety of computing platforms from embedded devices and mobile phones on the low end, to enterprise servers and supercomputers on the high end. While less common on desktop computers, Java applets are sometimes used to provide improved and secure functions while browsing the World Wide Web.</div>
                    </div>
                    <div id="interestbar">
                        <h3>Interests</h3>
                        <a href="#"><img src="images/Play.png"/><br/></a>
                        <a href="#"><img src="images/edit1.png"/><br/></a>
                        <a href="#"><img src="images/Play.png"/><br/></a>
                        <a href="#"><img src="images/edit1.png"/><br/></a>
                        <a href="#"><img src="images/Play.png"/><br/></a>
                        <a href="#"><img src="images/edit1.png"/><br/></a>
        
                    </div>
                </div>	-->

    </div>
</div>
</body>
</html>
