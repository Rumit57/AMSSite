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
		<%@include file="header1.jsp"%>
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<div id="mySidebar" class="sidebar">
					<ul class="nav" id="main-menu">
						<li><a class="active-menu" href="index"><i
								class="fa fa-dashboard"></i> Dashboard</a></li>
						<li><a href="manageHR"><i class="fa fa-edit"></i> HR </a></li>
						<li><a href="adminEmployee"><i class="fa fa-edit"></i>Employee
						</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">
					Dashboard <small>Welcome : ${session} </small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Dashboard</li>
				</ol>
				<center>
					<div class="col-md-12">
						<div class="col-md-2"></div>
						<a href="manageHR"><div class="col-md-4">
								<div class="board">
									<div class="panel panel-primary">
										<div class="number">
											<h3></h3>
											<h2>${nofHR}</h2>
											<small>HR</small>
										</div>
										<div class="icon">
											<i class="fa fa-users fa-5x red"></i>
										</div>
									</div>
								</div>
							</div></a> <a href="adminEmployee">
							<div class="col-md-4">
								<div class="board">
									<div class="panel panel-primary">
										<div class="number">
											<h3></h3>
											<h2>${nofEmp}</h2>
											<small style="text-transform: capitalize;">Employees</small>
										</div>
										<div class="icon">
											<i class="fa fa-users fa-5x red"></i>
										</div>
									</div>
								</div>
							</div>
						</a>
						<div class="col-md-2"></div>
					</div>
				</center>
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
