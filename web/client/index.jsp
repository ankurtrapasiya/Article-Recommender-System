<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>AdminPanel</title>
    <link media="all" rel="stylesheet" type="text/css" href="../client/css/style.css" />
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript">window.jQuery || document.write('<script type="text/javascript" src="../js/jquery-1.8.2.min.js"><\/script>');</script>
    <script type="text/javascript" src="../js/jquery.main.js"></script>
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
                $.get("client/profile.jsp",function(data){
                    $("#main").html(data);
                },"html");
            });       

            $("#interest").click(function(){                
                $.get("client/interest.jsp",function(data){
                    $("#main").html(data);
                },"html");
            });
            
            $("#favourite").click(function(){                
                $.get("client/favourite.jsp",function(data){
                    $("#main").html(data);
                },"html");
            });
            
            $("#history").click(function(){                
                $.get("client/history.jsp",function(data){
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
                            <li><a href="#" class="ico1">Suggestions <span class="num">26</span></a></li>
                            <li><a href="NotificationsServlet" class="ico2">Notifications <span class="num">5</span></a></li>
                            <li><a href="#" class="ico3">Popular <span class="num">3</span></a></li>
                        </ul>
                    </nav>
                    <div class="profile-box">
                        <span class="profile">
                            <a href="#" class="section">
                                <img class="image" src="../images/user.png" alt="image description" width="26" height="26" />
                                <span class="text-box">
                                    Welcome
                                    <strong class="name">Ankur</strong>
                                </span>
                            </a>
                            <a href="#" class="opener">opener</a>
                        </span>
                        <a href="LoginController?signout=true" id="btnSignOut" class="btn-on">Sign Out</a>
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
                    <a href="#tab-1" class="ico4" id="favourite"><span>Favourites</span><em></em></a>
                    <span class="tooltip"><span>Favourites</span></span>
                </li>
                <li>
                    <a href="#tab-1" class="ico5" id="history"><span>History</span><em></em></a>
                    <span class="tooltip"><span>History</span></span>
                </li>               
            </ul>
            <span class="shadow"></span>
        </aside>
    </div>
</body>
</html>