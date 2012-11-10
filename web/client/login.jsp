<%-- 
Document   : login
Created on : 4 Nov, 2012, 4:25:10 PM
Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <html lang="en">
        <head>


            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

            <!--STYLESHEETS-->
            <link href="css/client/login.css" rel="stylesheet" type="text/css" />
            <link href="css/client/nivo-slider.css" rel="stylesheet" type="text/css" />

            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
            <script type="text/javascript">window.jQuery || document.write('<script type="text/javascript" src="./js/jquery-1.8.2.min.js"><\/script>');</script>


            <!--Slider-in icons-->
            <script type="text/javascript">
                $(document).ready(function() {
                    
                    $("#username").focus();
                    
                    $(".username").focus(function() {
                        $(".user-icon").css("left","-48px");
                    });
                    $(".username").blur(function() {
                        $(".user-icon").css("left","0px");
                    });

                    $(".password").focus(function() {
                        $(".pass-icon").css("left","-48px");
                    });
                    $(".password").blur(function() {
                        $(".pass-icon").css("left","0px");
                    });
                    
                    
                    $("#btnLogin").click(function(){
                        $("#loginform").validate();
                    });

                });
            </script>

        </head>
        <body>

            <div id="image">
                <img src="./images/surprise.jpg"/>
            </div>

            <!--WRAPPER-->
            <div id="wrapper">

                <!--SLIDE-IN ICONS-->
                <div class="user-icon"></div>
                <div class="pass-icon"></div>
                <!--END SLIDE-IN ICONS-->

                <!--LOGIN FORM-->
                <form name="loginform" id="loginform" class="login-form" method="post"  action="client/LoginController">

                    <!--HEADER-->
                    <div class="header">
                        <!--TITLE--><h1>Login</h1><!--END TITLE-->
                    </div>
                    <!--END HEADER-->

                    <!--CONTENT-->
                    <div class="content">
                        <!--USERNAME--><input name="username" id="username" required="true" type="text" class="input username" value="Username" onfocus="this.value=''" placeholder="Username...." required="require"/><!--END USERNAME-->
                        <!--PASSWORD--><input name="password" required="true" type="password" class="input password" value="Password" onfocus="this.value=''" placeholder="Password...." required="require"/><!--END PASSWORD--><br/><br/>
                        <!--forgot password--><a href="#">Forgot password</a>
                    </div>
                    <!--END CONTENT-->

                    <!--FOOTER-->
                    <div class="footer">
                        <!--LOGIN BUTTON--><input type="submit" name="btnLogin" id="btnLogin" value="Login" class="button" /><!--END LOGIN BUTTON-->
                        <!--REGISTER BUTTON--><input type="submit" name="btnRegister" value="Register" class="register" /><!--END REGISTER BUTTON-->
                    </div>
                    <!--END FOOTER-->

                </form>
                <!--END LOGIN FORM-->

            </div>
            <!--END WRAPPER-->

            <!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

        </body>
    </html>
