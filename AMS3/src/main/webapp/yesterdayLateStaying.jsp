<%-- 
    Document   : yesterday_late_staying
    Created on : 20 Jan, 2020, 2:30:59 PM
    Author     : rumit
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
<title><%=session.getAttribute("MY_SESSION_COMPANY_NAME")%></title>
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/data-table/bootstrap-table.css" />
<link rel="stylesheet" href="css/data-table/bootstrap-editable.css" />
<link href="assets/css/custom-styles.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

<style>
.modal-body {
	max-height: calc(100vh - 210px);
	overflow-y: auto;
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
						<div class="data-table-area mg-tb-15">
							<div class="container-fluid">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="sparkline13-list">
											<div class="sparkline13-hd">
												<center>
													<div class="main-sparkline13-hd">
														<h1>
															<b>Yesterday Late Staying Data</b>
														</h1>
														<br>
													</div>
												</center>
											</div>
											<div class="sparkline13-graph">
												<div
													class="datatable-dashv1-list custom-datatable-overright">
													<table id="table" data-toggle="table"
														data-pagination="true" data-search="true"
														data-show-columns="true"
														data-show-pagination-switch="true" data-key-events="true"
														data-show-toggle="true" data-resizable="true"
														data-cookie="true" data-cookie-id-table="saveId"
														data-click-to-select="true" data-toolbar="#toolbar">
														<thead>
															<tr>
																<th data-field="id">NO</th>
																<th data-field="name" data-editable="true">Name</th>
																<th data-field="email" data-editable="true">Email</th>
																<th data-field="phone_no" data-editable="true">Phone
																	No.</th>
																<th data-field="pinchout" data-editable="true">PunchOut
																	Time</th>
																<th data-field="late_staying_time" data-editable="true">Late
																	staying Time</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${employeePresent}" var="employee1"
																varStatus="theCount">
																<tr>
																	<td>${theCount.count}</td>
																	<td>${employee1.firstName}&nbsp;${employee1.lastName}</td>
																	<td>${employee1.email}</td>
																	<td>${employee1.mobile}</td>
																	<td>${employee1.punchTimestamp}</td>
																	<td>${employee1.timeDifference}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
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
	<script src="js/data-table/bootstrap-table.js"></script>
	<script src="js/data-table/data-table-active.js"></script>
	<script src="js/data-table/bootstrap-table-resizable.js"></script>
	<script src="js/data-table/colResizable-1.5.source.js"></script>

</body>
</html>