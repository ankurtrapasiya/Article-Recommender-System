
<!doctype html>



<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>jQuery UI Datepicker - Default functionality</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
        <script src="js/jquery-1.8.2.min.js"></script>
        <script src="js/jquery-ui.js"></script>
        <link href="css/client/profile.css" rel="stylesheet" type="text/javascript"/>
        <link rel="stylesheet" type="text/css" href="/code_examples/tutorial.css">
        <script type="text/javascript" src="js/newjavascript.js"></script>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <script>
            $(function() {
                $( "#datepicker" ).datepicker();
            });
            $(function() {
                $( "#tabs" ).tabs();
            });

        </script>
        <script type="text/javascript">
            
            $(document).ready(function(e)
            {
                 
                $("#myform").submit(function(e) 
                {   
                    var btn=$("#submit").val(); 
                    var dt=$("#datepicker").val();
                    console.log(dt);
                    var un=$("#Uname").val();
                    var fn=$("#firstname").val();
                    var ln=$("#lastname").val();
                    var em=$("#email").val();
                    $.post("/client/UserProfileController",{"id":btn,"Name":un,"Fname":fn,"Lname":ln,"Email":em,"Dob":dt},function(data){
                        alert("Updation done Successfully");
                    });
                    e.preventDefault();
                
                });  
            });
            
            
        </script>
        <script type="text/javascript">
            
            $(document).ready(function(e)
            {
                 
                $("#changepass").submit(function(e) 
                {   
                    var btn=$("#submit").val(); 
                    
                   
                    var oldpass=$("#current").val();
                    var pass1=$("#newpass").val();
                    var pass2=$("#confirmpass").val();
                    $.post("ChangePasswordServlet",{"id":btn,"Oldpass":oldpass,"pass1":pass1,"pass2":pass2},function(data){
                        alert("Password changed Successfully");
                    });
                    e.preventDefault();
                
                });  
            });
            
            
        </script>
    </head>
    <body>




        <div id="tabs">
            <ul>

                <li><a href="#tabs-1" id="chg">Profile</a></li>
                <li><a href="#tabs-2" id="chg">Change Password</a></li>

            </ul>
            <div id="tabs-1">
                <form id="myform" name="myform">
                    <c:if test="${requestScope.status eq 'true'}">   
                        <h3>Username</h3><input type="text" required="required" id="username" name="Uname" value="${user.username}"/>
                        <h3>First Name</h3><input type="text" required="required" id="firstname" name="firstname" value="${user.firstname}"/>
                        <h3>Last Name</h3><input type="text" required="required" id="lastname" name="lastname" value="${user.lastname}"/>
                        <h3>Email</h3><input type="email" required="required" id="email" name="email" value="${user.email}"/>
                        <h3>Add Image to Profile</h3>
                        <input type="file" id="upload" name="upload"/>
                        <h3>Date of Birth</h3>
                        <input type="text" id="datepicker" name="dob" value="${user.dob}" /><br/>
                        <div>  <input type="submit" value='Edit' name="btnEdit" /></div>
                        </c:if>
                </form>
            </div>
            <div id="tabs-2">
                <form id="changepass">
                    <h3>Current Password</h3><input type="password" name="oldpass" required="required" id="current"/>
                    <h3>New Password</h3><input type="password" required="required" name="pass1" id="newpass"/>
                    <h3>Confirm Passwordd</h3><input type="password" required="required" name="pass2" onkeyup="checkPass(); return false;" id="confirmpass"/><br/>
                    <input type="submit" id="submit" name="submit" value="submit" class="bton"/>
                    <span id="confirmMessage" class="confirmMessage"></span>
                </form>
            </div>

        </div>
    </body>
</html>