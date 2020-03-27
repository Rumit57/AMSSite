<%-- 
    Document   : changepasswordHR
    Created on : 28 Jan, 2020, 2:17:30 PM
    Author     : karan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta content="" name="description" />
<meta content="webthemez" name="author" />
<title>Change Password</title>
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link href="assets/css/custom-styles.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

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
	<link href="css/style_1.css" rel="stylesheet" />
	<script src="js/common.min.js"></script>
	<script src="js/custom.min.js"></script>
	<div id="preloader">
		<div class="loader">
			<svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none"
					stroke-width="3" stroke-miterlimit="10" />
            </svg>
		</div>
	</div>
	<div id="wrapper">
		<%@include file="header2.jsp"%>
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<div id="mySidebar" class="sidebar">
					<ul class="nav" id="main-menu">
						<li><a href="hrDashboard"><i class="fa fa-dashboard"></i>
								Dashboard</a></li>
						<li><a href="manageEmployee"><i class="fa fa-edit"></i>
								Employee</a></li>

						<li><a href="liveActivity"><i class="fa fa-table"></i>
								Live Activity </a></li>
						<li><a href="missedPunchHR"><i class="fa fa-edit"></i>
								Missed Punches </a></li>
						<li><a href="reports"><i
								class="glyphicon glyphicon-list-alt"></i> Reports </a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="header">
				<div class="row">
					<div class="col-md-12">
						<br> <br>
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<div class="sparkline13-list">
								<h1>Change Password</h1>
								<br> <br>
								<form action="changePasswordSubmitHR" method="post">

									<div class="form-group">
										<label>Old Password</label> <input type="password"
											name="oldPass" value="${oldPass}"
											placeholder="Enter Old Password..." class="form-control" />
									</div>
									<div class="form-group">
										<label>New Password</label> <input type="password"
											name="newPass" value="${newPass}"
											placeholder="Enter New Password..." class="form-control" />
									</div>
									<div class="form-group">
										<label>Confirm Password</label> <input type="password"
											name="newCPass" value="${newCPass}"
											placeholder="Enter Confirm Password..." class="form-control" />
									</div>
									<c:if test="${success==1}">
										<div class="alert alert-success" role="alert">Password
											Successfully Change.</div>
									</c:if>
									<br>
									<center>
										<span style="color: red">${error}</span>
									</center>
									<div class="modal-footer">
										<input type="submit" class="btn btn-primary" value="Submit" />
									</div>
								</form>
							</div>
						</div>
						<div class="col-md-2"></div>
					</div>
				</div>
			</div>
			<div id="page-inner">
				<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
	<script src="assets/js/jquery-1.10.2.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

</body>
</html>
