<%-- 
    Document   : today_present
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<style>
/* width */
* {
	box-sizing: border-box;
}

.timeline1 {
	position: relative;
	margin: auto;
}
/* The actual timeline (the vertical ruler) */
.timeline1::after {
	content: '';
	position: absolute;
	width: 3px;
	background-color: black;
	top: 0;
	bottom: 0;
	left: 50%;
	margin-left: -3px;
}

/* Container around content */
.container1 {
	padding: 10px 8px;
	position: relative;
	background-color: #f5f5f5;
	width: 50%;
}

/* The circles on the timeline */
.container1::after {
	content: '';
	position: absolute;
	width: 15px;
	height: 15px;
	right: -6px;
	background-color: black;
	border: 3px solid #FF9F55;
	top: 20px;
	border-radius: 50%;
	z-index: 6;
}

/* Place the container to the left */
.left1 {
	left: 0;
}

/* Place the container to the right */
.right1 {
	left: 50%;
}

/* Fix the circle for containers on the right side */
.right1::after {
	left: -10px;
}

/* The actual content */
.content1 {
	padding: 1px 10px;
	background-color: #ddd;
	position: relative;
	border-radius: 8px;
}

.p1 {
	line-height: 25px;
	padding-top: 8px;
}

.modal-body {
	max-height: calc(100vh - 210px);
	overflow-y: auto;
}
</style>
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
															<b>Today Present Data</b>
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
																<th data-field="Email" data-editable="true">Email</th>
																<th data-field="Mobile" data-editable="true">Mobile
																	No.</th>
																<th data-field="Status" data-editable="true">Action</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${employeePresent}" var="employee1"
																varStatus="theCount">
																<tr>
																	<td>${theCount.count}</td>
																	<td>${employee1.employee.firstName}&nbsp;${employee1.employee.lastName}</td>
																	<td>${employee1.employee.email}</td>
																	<td>${employee1.employee.mobile}</td>
																	<td><a style="color: blue"
																		href="todayPresentStatus?id=${employee1.employee.objId}"><u>Status</u></a></td>
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

	<c:if test="${showStatus==1}">
		<script type="text/javascript">
			$(window).load(function() {
				$('#myModal').modal('show');
			});
		</script>
	</c:if>

	<div id="myModal" role="dialog" tabindex="-1" aria-hidden="true"
		class="modal" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">

					<h3 style="width: 100%">
						<center>Status</center>
					</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="panel-body ">
						<div class="timeline1">
							<c:forEach items="${employeePresentStatus}" var="employee"
								varStatus="theCount">
								<c:choose>
									<c:when test="${theCount.count % 2 != 0 }">
										<div class="container1 left1">
											<div class="content1">
												<p class="p1" style="color: green">
													Punch In <br> <i class="fa fa-clock-o"></i><b>
														${employee.punchTimestamp}</b>
												</p>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="container1 right1">
											<div class="content1">
												<p class="p1" style="color: red">
													Punch Out <br> <i class="fa fa-clock-o"></i><b>
														${employee.punchTimestamp}</b>
												</p>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!--Modal End-->

	<script src="js/data-table/bootstrap-table.js"></script>
	<script src="js/data-table/data-table-active.js"></script>
	<script src="js/data-table/bootstrap-table-resizable.js"></script>
	<script src="js/data-table/colResizable-1.5.source.js"></script>

</body>
</html>