<%-- 
    Document   : yesterday_missed_punch
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
<link
	href="plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.min.css" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="js/chosen.jquery.js"></script>
<link href="css/chosen.css" rel="stylesheet" />
<style>
.modal-body {
	max-height: calc(100vh - 210px);
	overflow-y: auto;
}

textarea {
	resize: vertical;
}
/* width */
* {
	box-sizing: border-box;
}

/* The actual timeline (the vertical ruler) */
.timeline1 {
	position: relative;
	/*  max-width: 500px;*/
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
						<li><a class="active-menu" href="#"><i class="fa fa-edit"></i>
								Missed Punches </a></li>
						<li><a href="reports"><i
								class="glyphicon glyphicon-list-alt"></i> Reports </a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">Missed Punch Data</h1>
				<div class="row">
					<div class="col-md-12">
						<div class="panel-body ">
							<form action="#" method="post">
								<div class="row">
									<div class="col-md-12">
										<h3><u>Search Employee Status</u></h3>
									</div>
									<div class="col-md-6 col-lg-3">
										<div class="card">
											<label>Employee Name</label> <br> <select
												style="width: 100%" data-placeholder="Search"
												class="chosen-select" name="employeeId">
												<option value="0">--select--</option>
												<c:forEach items="${employees}" var="employee"
													varStatus="theCount">
													<option value="${employee.objId}">${employee.firstName}&nbsp;${employee.lastName}</option>
												</c:forEach>
											</select>
											<script>
												$(".chosen-select")
														.chosen(
																{
																	no_results_text : "Oops, nothing found!"
																});
											</script>
										</div>
									</div>
									<div id="StartEndDropdown" style="display: block;">
										<div class="col-md-6 col-lg-3">
											<div class="card">
												<label>Date</label> <i class="fa fa-calendar"></i>&nbsp; <i
													class="fa fa-caret-down"></i>
												<div class="form-group">
													<div class="input-group date" id="datePicker">
														<input type="text" class="form-control" name="date"
															value=""> <span class="input-group-addon">
															<i class="glyphicon glyphicon-calendar"></i>
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-6 col-lg-3">
										<div class="card">
											<br> <input type="submit" formaction="searchStatus"
												class="btn btn-primary" value="Search" />
										</div>
									</div>
									<c:if test="${error1==1}">
										<div class="col-md-12">
											<h6 style="color: red">${error}</h6>
										</div>
									</c:if>
								</div>
							</form>
						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="sparkline13-list">
							<div class="sparkline13-graph">
								<div class="datatable-dashv1-list custom-datatable-overright">
									<table id="table" data-toggle="table" data-pagination="true"
										data-search="true" data-show-columns="true"
										data-show-pagination-switch="true" data-key-events="true"
										data-show-toggle="true" data-resizable="true"
										data-cookie="true" data-cookie-id-table="saveId"
										data-click-to-select="true" data-toolbar="#toolbar">
										<thead>
											<tr>
												<th data-field="id">NO</th>
												<th data-field="name" data-editable="true">Name</th>
												<th data-field="email" data-editable="true">Email</th>
												<th data-field="phone" data-editable="true">Phone No.</th>
												<th data-field="date" data-editable="true">Last Punch</th>
												<th data-field="fix" data-editable="true">Action</th>
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
													<td>${employee1.punchTimestamp}</td>
													<td><a
														href="missedPunchFix?id=${employee1.employee.objId}&date=${employee1.punchDate}"><button
																style="width: 80%" class="btn btn-success ">Fix</button></a></td>
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
			<div id="page-inner">
				<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
	<c:if test="${fix==1}">
		<script type="text/javascript">
			$(window).load(function() {
				$('#myModal').modal('show');
			});
		</script>
	</c:if>
	<!--modal-->
	<div id="myModal" role="dialog" tabindex="-1" aria-hidden="true"
		class="modal" data-backdrop="static">
		<div role="document" class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 style="width: 100%">
						<center>Missed Punch Details</center>
					</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form action="missedPunchFixSubmit">
					<div class="modal-body">
						<input type="hidden" name="employee" value="${eid}" /> <input
							type="hidden" name="eDate" value="${eDate}" />
						<div class="form-group">
							<label>Punch Time</label>
							<div class="input-group date">
								<input class="form-control" id="timepicker" name="punchTime"
									placeholder="Select time" /> <span class="input-group-addon">
									<span class="glyphicon glyphicon-time"></span>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label>Reason</label>
							<textarea class="form-control" rows="4" cols="50" name="reason"
								required placeholder="Write a Reason.."></textarea>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal"
							class="btn btn-secondary">Close</button>
						<input type="submit" class="btn btn-primary" value="Submit" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--Modal End-->

	<c:if test="${showStatus==1}">
		<script type="text/javascript">
			$(window).load(function() {
				$('#showStatus').modal('show');
			});
		</script>
	</c:if>

	<!--modal-->
	<div id="showStatus" role="dialog" tabindex="-1" aria-hidden="true"
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

	<script type="text/javascript">
		var date = new Date();
		var today = new Date(date.getFullYear(), date.getMonth(), date
				.getDate());

		var optComponent = {
			format : 'yyyy-mm-dd',
			container : '#datePicker',
			orientation : 'auto bottom',
			todayHighlight : true,
			autoclose : true
		};

		// COMPONENT
		$('#datePicker').datepicker(optComponent);
		$('#datePicker').datepicker('setDate', today);
	</script>
	<script src="js/data-table/bootstrap-table.js"></script>
	<script src="js/data-table/data-table-active.js"></script>
	<script src="js/data-table/bootstrap-table-resizable.js"></script>
	<script src="js/data-table/colResizable-1.5.source.js"></script>
	<script src="plugins/moment/moment.js"></script>
	<script
		src="plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>
	<script src="plugins/form-pickers-init.js"></script>
</body>
</html>