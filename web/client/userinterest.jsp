<%-- 
    Document   : userinterest
    Created on : 4 Nov, 2012, 6:21:46 PM
    Author     : ankur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="style.css" rel="stylesheet" type="text/css" />

        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


        <style>
            .buttonstyle {
                float: center;
                padding: 11px 25px;
                font-family: 'Bree Serif', serif;
                font-weight: bold;
                font-size: 18px;
                color: #fff;
                text-shadow: 0px 1px 0 rgba(0,0,0,0.25);
                background: #56c2e1;
                border: 1px solid #46b3d3;
                border-radius: 5px;
                cursor: pointer;
                box-shadow: inset 0 0 2px rgba(256,256,256,0.75);
                -moz-box-shadow: inset 0 0 2px rgba(256,256,256,0.75);
                -webkit-box-shadow: inset 0 0 2px rgba(256,256,256,0.75);
            }
        </style>
        <script>
            
            function validate() {
                
                count = 0;
                var inputElems = document.getElementsByTagName("input");
                
                for (var i=0; i<inputElems.length; i++) {
                    if (inputElems[i].type === "checkbox" && inputElems[i].checked === true) {
                        count++;
                    }
            
                }
                if(count == 0) {
                    alert("Please select atleast one interest");
                    return false;
                } else {
                    return true;
                }
            }
            
        </script>
    </head>
    <body style=" background:url('../images/bg.png')">


        <div id="wrapper" style="left: 0px; top: 0px; width: 800px; margin-left: 250px; margin-top: 70px" align="center" >
            <div style="background-color: #F3F3F3" style="margin-left: 30%">
                <h1>Choose Your Interests</h1>
                <p>
                    Please specify atleast one interest.
                    <br>
                    <br>
                </p>


                <form method="post" name="interestform" action="RegistrationInterestController" onsubmit="return validate()"/>
                <table cellspacing ="15" cellpading ="10">
                    <c:forEach var="interests" items="${requestScope.interests}">
                        <tr>
                            <td rowspan="2">
                                <!--<img src="${interests.iconpath}" height="100" width="100"/>-->
                                <img src="interesticon.jpg" height="50" width="50"/>
                            </td>
                            <td>
                                ${interests.name}
                            </td>
                            <td>
                                <input type="checkbox" name="interest" value="${interests.interestid}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                ${interests.description}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                
                
                <input type="hidden" name="hdnUserId" value="${requestScope.userid}"/>
                
                <input type="submit" value="Finish" onclick="javascript:validate();" class="buttonstyle" />
                <input type="reset" class="buttonstyle"/>
                </form>
            </div>

        </div>
    </body>
</html>