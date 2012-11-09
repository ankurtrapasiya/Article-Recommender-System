<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--         <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>-->
        <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="../js/jquery.tokeninput.js"></script>
        
        

        <link rel="stylesheet" href="../css/client/token-input.css" type="text/css" />
        <link rel="stylesheet" href="../css/client/token-input-facebook.css" type="text/css" />


        <script type="text/javascript">
            $(document).ready(function() {
                $("input[type=button]").click(function () {
                    alert("Would submit: " + $(this).siblings("input[type=text]").val());
                });
            });
        </script>

        <%@taglib uri="/WEB-INF/tlds/taglibrary.tld" prefix="c" %>
    </head>
    <body>


        <form method="get" action="../ShareController">
            <div>
                <textarea id="demo-input-local" name="friends" cols="100" rows="6" /></textarea>
                <!--<input type="text" id="demo-input-local" name="blah" />-->
                <input type="Submit" value="Share" />
                <script type="text/javascript">
                    $(document).ready(function() {
                        $("#demo-input-local").tokenInput(
                    <c:PrintFriends/>,{theme:"facebook"}
                        );
                        });
                </script>
            </div>        
        </form>




    </body>
</html>
