
<!doctype html>



<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>jQuery UI Datepicker - Default functionality</title>
        <link rel="stylesheet" href="../css/client/jquery-ui.css" />
        <script src="../js/jquery-1.8.2.min.js"></script>
        <script src="../js/jquery-ui.js"></script>
        <link href="../css/client/profile.css" rel="stylesheet" type="text/css"/>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
        <script>
            $(function() {
                $( "#datepicker" ).datepicker({dateFormat: 'yy-mm-dd'});
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
                    var un=$("#username").val();
                    var fn=$("#firstname").val();
                    var ln=$("#lastname").val();
                    var em=$("#email").val();
                    var cn=$("#country").val();
                    var st=$("#state").val();
                    var ct=$("#city").val();
                    // var newimg=$("#upload").val();
                    $.post("../UserProfileController",{"id":btn,"Name":un,"Fname":fn,"Lname":ln,"Email":em,"Dob":dt,"City":ct,"State":st,"Country":cn},function(data){
                        alert("Updation done Successfully");
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
                <form id="myform" name="myform" method="post">
                    <c:if test="${requestScope.status eq 'true'}">   
                        <h3>Username</h3><input type="text" required="required" id="username" value="${user.username}"/>
                        <h3>First Name</h3><input type="text" required="required" id="firstname" value="${user.firstname}"/>
                        <h3>Last Name</h3><input type="text" required="required" id="lastname" value="${user.lastname}"/>
                        <h3>Email</h3><input type="email" required="required" id="email" value="${user.email}"/>
                        <h3>Date of Birth</h3>

                        <input type="text" id="datepicker" name="dob" value="${user.dob}" />
                        <br/>
                    </c:if>

                    <c:import url="../xml/city.xml" var="document"/>
                    <x:parse doc="${document}" var="myXml"></x:parse>
                    <h3>City</h3>
                    <select id="city" style=" margin:5px 0 10px 250px;">
                        <option value="${user.city}">${user.city}</option>
                        <x:forEach select="$myXml/state/city" var="var">
                            <option value="<x:out select="@name"></x:out>"> 
                            <x:out select="@name"></x:out></option>
                        </x:forEach>
                    </select>

                    <c:import url="../xml/state.xml" var="document"/>
                    <x:parse doc="${document}" var="myXml"></x:parse>
                    <h3>State</h3>
                    <select id="state" style=" margin:5px 0 10px 250px;">
                        <option value="${user.state}">${user.state}</option>
                        <x:forEach select="$myXml/country/state" var="var">
                            <option value="<x:out select="@name"></x:out>"> 
                            <x:out select="@name"></x:out></option>
                        </x:forEach>
                    </select> 

                    <c:import url="../xml/country.xml" var="document"/>
                    <x:parse doc="${document}" var="myXml"></x:parse>
                    <h3>Country</h3>
                    <select id="country" style=" margin:5px 0 10px 250px;">
                        <option value="${user.country}">${user.country}</option>
                        <x:forEach select="$myXml/world/country" var="var">
                            <option value="<x:out select="@name"></x:out>"> 
                            <x:out select="@name"></x:out></option>
                        </x:forEach>
                    </select> 

                    <div><input type="submit" id="submit" value='Edit' name="btnEdit" /></div>   
                </form>
            </div>
            <div id="tabs-2">
                <form>
                    <h3>Current Password</h3><input type="password" required="required" id="current"/>
                    <h3>New Password</h3><input type="password" required="required" id="newpass"/>
                    <h3>Confirm Password</h3><input type="password" required="required" id="confirmpass"/><br/>
                    <input type="submit" id="submit" name="submit" value="submit" class="bton"/>
                </form>
            </div>

        </div>
    </body>
</html>