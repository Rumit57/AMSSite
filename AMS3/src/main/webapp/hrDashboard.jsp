<%-- 
    Document   : HRdeshboard
    Created on : 17 Dec, 2019, 9:59:25 AM
    Author     : rumit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta content="" name="description" />
        <meta content="webthemez" name="author" />
        <title><%=session.getAttribute("MY_SESSION_COMPANY_NAME")%></title>
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <link href="assets/css/custom-styles.css" rel="stylesheet" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <style>

            .alert {
                width:15%;


            }
        </style>
    </head>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	if (session.getAttribute("MY_SESSION_ROLE") == null)
		response.sendRedirect("logout");
%>


    <body>
        <!--preloader-->
        <link href="css/style_2.css" rel="stylesheet">
            <script src="js/common.min.js"></script>
            <script src="js/custom.min.js"></script>
            <div id="preloader">
                <div class="loader">
                    <svg class="circular" viewBox="25 25 50 50">
                        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
                    </svg>
                </div>
            </div>
            <div id="wrapper">
                <%@include file="header2.jsp" %>

                <nav class="navbar-default navbar-side" role="navigation">
                    <div class="sidebar-collapse">
                        <div id="mySidebar" class="sidebar">
                            <ul class="nav" id="main-menu">

                                <li>
                                    <a class="active-menu" href="#"><i class="fa fa-dashboard"></i> Dashboard</a>
                                </li>
                                <li>
                                    <a href="manageEmployee"><i class="fa fa-edit"></i>  Employee</a>

                                </li> 

                                <li>
                                    <a href="liveActivity"><i class="fa fa-table"></i> Live Activity  </a>

                                </li> 		 
                                <li>
                                    <a href="reports"><i class="glyphicon glyphicon-list-alt"></i> Reports </a>

                                </li> 	
                            </ul>   
                        </div>
                    </div>
                </nav>


                <div id="page-wrapper" >
                    <div class="header"> 
                        <h1 class="page-header">
                            Dashboard <small>Welcome HR ${session} </small>
                        </h1>

                        <div class="row">
                            <div class="col-12">

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Today Summary
                                    </div>
                                    <div class="panel-body ">
                                        <div class="row">
                                            <div class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/todayPresent">
                                                    <div class="card">
                                                        <div style="background-color: #dff0d8" class="card-body">
                                                            <h5 class="card-title">Present</h5>
                                                            <p class="card-text">${todayPresent} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div  class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/todayAbsent">
                                                    <div class="card">
                                                        <div style="background-color: #f2dede" class="card-body">
                                                            <h5 class="card-title">Absent</h5>
                                                            <p class="card-text">${todayAbsent} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/todayLateComing">
                                                    <div class="card">
                                                        <div style="background-color: #d9edf7" class="card-body" >
                                                            <h5 class="card-title">Late Coming</h5>
                                                            <p class="card-text">${todayLateComing} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/todayEarlyLeaving">
                                                    <div class="card">
                                                        <div style="background-color: #d9edf7" class="card-body" >
                                                            <h5 class="card-title">Early Leaving</h5>
                                                            <p class="card-text">${todayEarlyLeaving} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>  
                        <div class="row">
                            <div class="col-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Yesterday Summary
                                    </div>
                                    <div class="panel-body ">
                                        <div class="row">
                                            <div class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/yesterdayPresent">
                                                    <div class="card">
                                                        <div style="background-color: #dff0d8" class="card-body">
                                                            <h5 class="card-title">Present</h5>
                                                            <p class="card-text">${yesterdayPresent} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div  class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/yesterdayAbsent">
                                                    <div class="card">
                                                        <div style="background-color: #f2dede" class="card-body">
                                                            <h5 class="card-title">Absent</h5>
                                                            <p class="card-text">${yesterdayAbsent} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/yesterdayLateComing">
                                                    <div class="card">
                                                        <div style="background-color: #d9edf7" class="card-body" >
                                                            <h5 class="card-title">Late Coming</h5>
                                                            <p class="card-text">${yesterdayLateComing} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/yesterdayLateStaying">
                                                    <div class="card">
                                                        <div style="background-color: #d9edf7" class="card-body" >
                                                            <h5 class="card-title">Late Staying</h5>
                                                            <p class="card-text">${yesterdayLateStaying} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/yesterdayMissedPunch">
                                                    <div class="card">
                                                        <div style="background-color: #f2dede" class="card-body" >
                                                            <h5 class="card-title">Missed Punch</h5>
                                                            <p class="card-text">${yesterdayMissedPunch}</p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div class="col-md-6 col-lg-3">
                                                <a style="color: black;text-decoration: none;" href="/yesterdayEarlyLeaving">
                                                    <div class="card">
                                                        <div style="background-color: #d9edf7" class="card-body" >
                                                            <h5 class="card-title">Early Leaving</h5>
                                                            <p class="card-text">${yesterdayEarlyLeaving} </p>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>			
                    <div id="page-inner">
                        <%@include file="footer.jsp" %>
                    </div>
                </div>
            </div>
            <script src="assets/js/jquery-1.10.2.js"></script>
            <script src="assets/js/bootstrap.min.js"></script>

    </body>
</html>