<%-- 
    Document   : changePassword
    Created on : 16 Jan, 2020, 9:56:47 AM
    Author     : rumit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Change Password</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/util.css"/>
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
    </head>
    <%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	%>
    <body>
        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                    <form action="changePasswordForm" method="post" class="login100-form validate-form">
                        <span class="login100-form-title p-b-10">
                            Change Password
                        </span>
                        <center><span class="login50-form-title p-b-40">
                                You can Change your password here.
                            </span></center>
                        <br>
                        <br>
                        <div class="wrap-input100 validate-input m-b-23">
                            <span class="label-input100">Email</span>
                            <input class="input100" type="text" name="email" value="${email}" readonly="readonly"/>
                            <span class="focus-input100" data-symbol="&#xf206;"></span>
                        </div>
                       <input type="hidden" name="code" value="${code}"/>
                        <center><span style="color:red">${error}</span></center>
                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <span class="label-input100">Password</span>
                            <input class="input100" type="password" name="pass" value="${pass}" placeholder="Type your password"/>
                            <span class="focus-input100" data-symbol="&#xf190;"></span>
                        </div>
                        <div>
                            <br>
                        </div> 
                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <span class="label-input100">Confirm Password</span>
                            <input class="input100" type="password" name="cpass" value="${cpass}" placeholder="Type your Confirm password"/>
                            <span class="focus-input100" data-symbol="&#xf190;"></span>
                        </div>
                        <div>
                            <br>
                        </div> 
                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn">
                                    Submit
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div id="dropDownSelect1"></div>


        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>