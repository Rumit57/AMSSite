<%-- 
    Document   : index
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
						<li><a class="active-menu" href="#"><i
								class="glyphicon glyphicon-list-alt"></i> Reports </a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">
					<center>Reports</center>
				</h1>

				<div class="col-md-12">
					<div class="col-md-3"></div>
					<a href="report1">
						<div class="col-md-6">
							<div class="board">
								<div class="panel panel-primary">
									<div class="number">
										<h3></h3>
										<small style="color: black;">All Employee Working Hour
											Report</small>
									</div>
									<div class="icon">
										<i class="fa fa-users fa-5x red"></i>
									</div>
								</div>
							</div>
						</div>
					</a>
					<div class="col-md-3"></div>
				</div>
				<div class="col-md-12">
					<div class="col-md-3"></div>
					<a href="report2">
						<div class="col-md-6">
							<div class="board">
								<div class="panel panel-primary">
									<div class="number">
										<h3></h3>
										<small style="color: black;">Late Coming Report</small>
									</div>
									<div class="icon">
										<i class="fa fa-users fa-5x red"></i>
									</div>
								</div>
							</div>
						</div>
					</a>
					<div class="col-md-3"></div>
				</div>

				<div class="col-md-12">
					<div class="col-md-3"></div>
					<a href="report3">
						<div class="col-md-6">
							<div class="board">
								<div class="panel panel-primary">
									<div class="number">
										<h3></h3>
										<small style="color: black;">Late Staying Report</small>
									</div>
									<div class="icon">
										<i class="fa fa-users fa-5x red"></i>
									</div>
								</div>
							</div>
						</div>
					</a>
					<div class="col-md-3"></div>
				</div>

				<div class="col-md-12">
					<div class="col-md-3"></div>
					<a href="report4">
						<div class="col-md-6">
							<div class="board">
								<div class="panel panel-primary">
									<div class="number">
										<h3></h3>
										<small style="color: black;">Fixed Miss Punch Report</small>
									</div>
									<div class="icon">
										<i class="fa fa-users fa-5x red"></i>
									</div>
								</div>
							</div>
						</div>
					</a>
					<div class="col-md-3"></div>
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
