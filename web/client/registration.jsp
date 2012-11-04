<%-- 
    Document   : registration
    Created on : 4 Nov, 2012, 6:06:23 PM
    Author     : ankur
--%>

<%@page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : registration
    Created on : Nov 1, 2012, 4:40:20 AM
    Author     : Harmish
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style>


            .btn {
                float:right;
                padding: 11px 25px;

                font-family: 'Bree Serif', serif;
                font-weight: 300;
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



            .harmish {

                width: 188px;
                padding: 15px 25px;
                height: 21px
                    font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
                font-weight: 400;
                font-size: 18px;
                color: #9d9e9e;
                text-shadow: 1px 1px 0 rgba(256,256,256,1.0);

                background: #fff;
                border: 1px solid #fff;
                border-radius: 5px;

                box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
                -moz-box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
                -webkit-box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
            }
            .style1 {
                font-family: "Bree Serif", serif;
            }
            .style2 {
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
            .style3 {
                width: 188px;
                padding: 15px 25px;
                height: 21px font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
                font-weight: bold;
                font-size: 18px;
                color: #9d9e9e;
                text-shadow: 1px 1px 0 rgba(256,256,256,1.0);
                background: #fff;
                border: 1px solid #fff;
                border-radius: 5px;
                box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
                -moz-box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
                -webkit-box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
            }
        </style>


        <!-- Css for page -->
        <link href="style.css" rel="stylesheet" type="text/css" />

        <!--  JQuery, Css for calendar control -->
        <link rel="stylesheet" href="css/jquery-ui.css" />
        <script src="../js/jquery-1.8.2.min.js"></script>
        <script src="../js/jquery-ui.js"></script>
        <link rel="stylesheet" href="css/hs_style.css" />



        <script>
     
  
  
            function validate() {
                var txtFirstName = document.signupform.txtFirstName.value;
                var txtLastName = document.signupform.txtLastName.value;
                var txtEmail = document.signupform.txtEmail.value;
                var username = document.signupform.username.value;
                var txtPassword = document.signupform.txtPassword.value;
                var txtConfirmPassword = document.signupform.txtPassword.value;
                var txtDob = document.signupform.txtDob.value;
                var txtCountry = document.signupform.txtCountry.value;
                    
                var errormessage = "";
                if(txtFirstName == "" || txtFirstName == "First name") {
                    errormessage = errormessage + "Please enter your first name.<br>";
                }
                if(txtLastName == "" || txtLastName == "Last name") {
                    errormessage = errormessage + "Please enter your last name.<br>";
                }
                if(txtEmail == "" || txtEmail == "Email") {
                    errormessage = errormessage + "Please enter your email.<br>";
                }
                if(username == "" || username == "Username") {
                    errormessage = errormessage + "Please choose a username.<br>";
                }
                if(txtPassword == "" || txtConfirmPassword == "Username") {
                    errormessage = errormessage + "Please fill both the password fields.<br>";
                }
                if(txtDob == "" || txtDob == "Date of Birth") {
                    errormessage = errormessage + "Please enter your date of birth.<br>";
                }
                if(txtCountry == "none") {
                    errormessage = errormessage + "Please select your country..<br>";
                }
                    
                if(errormessage == "") {
                    return true;
                }
                else {
                    $("#errormessage").html(errormessage);
                    return false;
                }
                    
            }
  
  
            function passwordmatch() {
                
                var pass = document.signupform.txtPassword.value;
                var cpass = document.signupform.txtConfirmPassword.value;
                if(pass != cpass) {
                    $("#passwordmessage").html("Password doesn't match");
                    document.signupform.txtPassword.value = "";
                    signupform.txtConfirmPassword.value=""
                } else {
                    $("#passwordmessage").html("");
                    
                }
            }
  
  
  
            function getXMLObject()  //XML OBJECT
            {
                var xmlHttp = false;
                try {
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
                }
                catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
                    }
                    catch (e2) {
                        xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
                    }
                }
                if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
                    xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
                }
                return xmlHttp;  // Mandatory Statement returning the ajax object created
            }
            
            var xmlhttp = new getXMLObject();	//xmlhttp holds the ajax object
            
            
            function checkUsernameAvailability(username) {
             
                
                if(xmlhttp) {
                    xmlhttp.open("GET","../UsernameCheckerController?un=" + username,true);
                    xmlhttp.onreadystatechange  = handleServerResponse2;
                    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xmlhttp.send(null);
                }
            }
            
            
            function handleServerResponse2() {
                if (xmlhttp.readyState == 4) {
                    if(xmlhttp.status == 200) {
                        //                        document.signupform.txtEmail.value=xmlhttp.responseText; //Update the HTML Form element 
                        var x = xmlhttp.responseText;
                        if(x == 1) {
                            $("#usernamemessage").html("Available");
                        } else {
                            document.signupform.username.value = "";
                            $("#usernamemessage").html("Not available. Try a different username.");
                            
                        }
                    }
                    else {
                        alert("Error during AJAX call. Please try again");
                    }
                }
            }
            
            
            function checkEmailAvailability(email) {
             
                
                if(xmlhttp) {
                
                    xmlhttp.open("POST","../EmailCheckerController?email=" + email,true); //gettime will be the servlet name
                    xmlhttp.onreadystatechange  = handleServerResponse;
                    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xmlhttp.send(null);
                }
            }
            
            function handleServerResponse() {
                if (xmlhttp.readyState == 4) {
                    if(xmlhttp.status == 200) {
                        //                        document.signupform.txtEmail.value=xmlhttp.responseText; //Update the HTML Form element 
                        var x = xmlhttp.responseText;
                        if(x == 0) {
                            document.signupform.txtEmail.value = "";
                            $("#emailmessage").html("Not available. Email id already exists.");
                        } else {
                            $("#emailmessage").html("Available");
                        }
                    
                        // $("#emailmessage").html(xmlhttp.responseText);
                    }
                    else {
                        alert("Error during AJAX call. Please try again");
                    }
                }
            }
            
           
  
            $(function() {
                $( "#datepicker" ).datepicker({
                    changeMonth: true,
                    changeYear: true
                });
            });
        </script>








    </head>
    <body>

        <b>

            <!--WRAPPER-->
            <div id="wrapper" style="left: 0px; top: 0px; width: 1212px">



                <!--LOGIN FORM-->
                <div style="background-color: #F3F3F3">

                    &nbsp;<form method="get" action="../Client/SignUpController" name="signupform" style="left: -246px; top: -10px" onsubmit="return validate()">
                        &nbsp;<table cellpadding="5" style="font-family: serif; font-size: 20px; color: #414848; margin-left: 40px;">
                            <div class="content">
                                <h1 style="color: #414848; font-size: 28px; width: 789px; margin-left: 50px;" class="style1">
                                    &nbsp;Sign Up</h1>
                                <br/>
                                <br/>
                                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All fields are mandatory.</p>
                                <p id="errormessage">

                                </p>
                                <tr>
                                    <td colspan="3" style="height: 32px">Name</b></td>
                                </tr>
                                <tr>
                                    <td style="width: 355px">
                                        <input type="text" id ="txtFirstName" name="txtFirstName" size ="50" class="style3" value="First name" onfocus="this.value=''"  style="height: 20px" /><b>
                                        </b>
                                    </td>
                                    <td>
                                        <input type="text" id="txtLastName" name="txtLastName" size ="50" class="style3" value="Last name" onfocus="this.value=''" style="height: 20px"/><b>
                                    </td>
                                    <td>
                                        <div id="namemessage">

                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="3">Email</b></td>

                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input type="text" id="txtEmail" name="txtEmail" size ="100" class="style3" value="Email" onfocus="this.value=''" onblur="javascript:checkEmailAvailability(this.value.trim());" style="height: 20px"/></td>

                                    <td>
                                        <div id="emailmessage" name="emailmessage">

                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="3">
                                        <b>Username
                                        </b>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <input type="text" name="username" id="username" size ="50" class="style3" value="Username" onfocus="this.value=''" onblur="javascript:checkUsernameAvailability(this.value.trim());" style="height: 20px"/><b>
                                    </td>

                                    <td>
                                        <div id="usernamemessage">

                                        </div>
                                    </td>
                                </tr>



                                <tr>
                                    <td colspan="3">Password</b></td>
                                </tr>
                                <tr>
                                    <td style="width: 355px">
                                        <input type="password" id="txtPassword" name="txtPassword" size ="50" class="style3"  style="height: 20px"/><b>
                                        </b>
                                    </td>
                                    <td>
                                        <input type="password" id="txtConfirmPassword" name="txtConfirmPassword" onblur="javascript:passwordmatch();" size ="50" class="style3"  style="height: 20px"/><b>
                                    </td>
                                    <td>
                                        <div id="passwordmessage">

                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan ="3">Date of Birth</b></td>

                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input type="text" name="txtDob" id="datepicker" class="style3" value="Date of Birth" onfocus="this.value=''" size =" 50"/><b>
                                    </td>

                                </tr>
                                <tr>
                                    <td style="width: 355px">
                                        <div id="datemessage">

                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan ="3">Country</b></td>
                                </tr>
                                <tr>
                                    <td colspan ="2">
                                        <select name="txtCountry" Style="width:315px" class="style3"/>
                                <option value="none" selected="selected">--Select 
                                    Country--</option>
                                <option value="India">India</option>
                                <option value="China">China</option>
                                <option value="United States of America">United States 
                                    of America</option>
                                <option value="Korea">Korea</option>
                                <option value="United Arab Emirates">United Arab 
                                    Emirates</option>
                                <option value="United Kingdom">United Kingdom</option>
                                </select><b>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            Enter captcha characters
                                </b>
                                </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <%
                                            ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LdpJNgSAAAAAOT4AYaltm4mj47giAx6YYnMZPF0", "6LdpJNgSAAAAACVXRbfQR1kb-LMx0tiuZbu337jG", false);
                                            out.print(c.createRecaptchaHtml(null, null));
                                        %>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="center">
                                        <input type="submit" name="submit" value="Signup" class="style2" /><b>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </b>
                                        <input type="reset" name="reset" value="Reset" class="style2" /><b>
                                    </td>
                                </tr>

                            </div>
                        </table>

                    </form>
                </div>

                <!--END LOGIN FORM-->

            </div>
            <!--END WRAPPER-->

            <!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

        </b>

    </body></html>