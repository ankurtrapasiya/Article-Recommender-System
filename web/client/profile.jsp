<%-- 
    Document   : profile
    Created on : 27 Oct, 2012, 7:07:47 AM
    Author     : rinku
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>jQuery UI Datepicker - Default functionality</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
        <script src="../js/jquery-1.8.2.min.js"></script>
        <script src="../js/jquery-ui.js"></script>
        <link href="css/profile.css" rel="stylesheet" type="text/css"/>
        <script>
            $(function() {
                $("#datepicker").datepicker();
            });
            $(function() {
                $( "#tabs" ).tabs();
            });

        </script>



    </head>
    <body class='default'>
        <div id="tabs">
            <ul>
                <li><a href="#tabs-1">Profile</a></li>
                <li><a href="#tabs-2">Change Password</a></li>

            </ul>
            <div id="tabs-1">
                <form id="myform">
                    <h3>Username</h3><input type="text" required="required" id="username"/>
                    <h3>First Name</h3><input type="text" required="required" id="firstname"/>
                    <h3>Last Name</h3><input type="text" required="required" id="lastname"/>
                    <h3>Email</h3><input type="email" required="required" id="email"/>
                    <h3>Add Image to Profile</h3>
                    <input type="file" id="upload" name="upload"/>
                    <h3>Date of Birth</h3>
                    <input type="text" id="datepicker" /><br/>
                    
                    <c:import url="../xml/country.xml" var="document"/>
                    <x:parse doc="${document}" var="myXml"></x:parse>
                    <h3>Country</h3>
                    <select id="country">
                        <option value="0">--Select--</option>
                        <x:forEach select="$myXml/world/country" var="var">
                            <option value="<x:out select="@id"></x:out>"> <x:out select="@name"></x:out></option>
                        </x:forEach>
                    </select>
                    
                    <c:import url="../xml/state.xml" var="document"/>
                    <x:parse doc="${document}" var="myXml"></x:parse>
                    <h3>State</h3>
                    <select id="state">
                        <option value="0">--Select--</option>
                        <x:forEach select="$myXml/country/state" var="var">
                            <option value="<x:out select="@id"></x:out>"> <x:out select="@name"></x:out></option>
                        </x:forEach>
                    </select>
                    
                    <c:import url="../xml/city.xml" var="document"/>
                    <x:parse doc="${document}" var="myXml"></x:parse>
                    <h3>City</h3>
                    <select id="city">
                        <option value="0">--Select--</option>
                        <x:forEach select="$myXml/state/city" var="var">
                            <option value="<x:out select="@id"></x:out>"> <x:out select="@name"></x:out></option>
                        </x:forEach>
                    </select>
                    <button type="submit" id="submit" name="submit" value="submit" class="bton">Submit</input>
                </form>
            </div>
            <div id="tabs-2">
                <form>
                    <h3>Current Password</h3><input type="password" required="required" id="current"/>
                    <h3>New Password</h3><input type="password" required="required" id="newpass"/>
                    <h3>Confirm Passwordd</h3><input type="password" required="required" id="confirmpass"/><br/>
                    <button type="submit" id="submit" name="submit" value="submit" class="bton">Save</input>
                </form>
            </div>

        </div>
    </body>
</body>
</html>







