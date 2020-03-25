<%-- 
    Document   : Report1
    Created on : 29 Jan, 2020, 4:52:50 PM
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
<link href="assets/css/custom-styles.css" rel="stylesheet" />
<link rel="stylesheet" href="css/data-table/bootstrap-table.css" />
<link rel="stylesheet" href="css/data-table/bootstrap-editable.css" />
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />


<script src="js/chosen.jquery.js"></script>
<link href="css/chosen.css" rel="stylesheet" />
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
	<link href="css/style_1.css" rel="stylesheet">
	<!--<script src="js/common.min.js"></script>-->
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
						<li><a href="reports"><i
								class="glyphicon glyphicon-list-alt"></i> Reports </a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="header">
				<h3 class="page-header">Late Coming Report</h3>
				<div class="row">
					<div class="col-md-12">
						<div class="panel-body ">
							<form action="#" method="post">
								<div class="row">
									<div class="col-md-6 col-lg-3">
										<div class="card">
											<label>Employee Name</label> <br> <select
												style="width: 100%" data-placeholder="Search" multiple
												class="chosen-select" name="employeeId">
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
													class="fa fa-caret-down"></i><input id="reportrange1"
													name="dateRange"
													style="cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%" />
											</div>
										</div>
									</div>

									<div class="col-md-6 col-lg-2">
										<div class="card">
											<label>Filter Option</label> <select id="filter"
												class="form-control" style="width: 100%">
												<option value="filter">--Select--</option>
												<option value="yearly">Yearly</option>
											</select>
										</div>
									</div>

									<div class="col-md-6 col-lg-2">
										<div class="card">
											<div id="hideDropdown1" style="display: none;">
												<label>Select Year</label> <select id="year" name="year"
													style="width: 100%" class="form-control" disabled>
													<option value="2020">2020</option>
													<option value="2019">2019</option>
													<option value="2018">2018</option>
													<option value="2017">2017</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-2">
										<div class="card">
											<div id="hideDropdown" style="display: none;">
												<label>Select Month</label> <select id="month" name="month1"
													style="width: 100%;" class="form-control" disabled>
													<option value="0">--Select--</option>
													<option value="01">Jan</option>
													<option value="02">Feb</option>
													<option value="03">Mar</option>
													<option value="04">Apr</option>
													<option value="05">May</option>
													<option value="06">Jun</option>
													<option value="07">Jul</option>
													<option value="08">Aug</option>
													<option value="09">Sep</option>
													<option value="10">Oct</option>
													<option value="11">Nov</option>
													<option value="12">Dec</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-12 ">
										<br>
										<center>
											<input type="submit" formaction="filter2"
												class="btn btn-primary" value="Filter" />
										</center>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<br>
				<c:if test="${filter==1}">
					<div class="alert alert-success alert-dismissible" role="alert">
						<strong>Filter :</strong> ${message} <a href="report2"
							class="close" aria-label="Close"> <span aria-hidden="true">&times;</span>
						</a>
					</div>
				</c:if>
				<div class="row">
					<div class="col-md-12">
						<div class="panel-body ">
							<div class="data-table-area mg-tb-15">
								<div class="container-fluid">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="datatable-dashv1-list custom-datatable-overright">
												<div>
													<a onclick="pdf1()" class="btn btn-primary pull-left"
														role="button" href="pdf/9.pdf" download="9.pdf">
														Download PDF </a>
													<script>
														var request = new XMLHttpRequest();
														function pdf1() {

															var url = "ITextTest.jsp";
															try {
																request.open(
																		"GET",
																		url,
																		true);
																request.send();
															} catch (e) {
																alert("Unable to connect to server");
															}
														}
													</script>
												</div>
												<table id="table" data-toggle="table" data-pagination="true"
													data-search="true" data-show-columns="true"
													data-show-pagination-switch="true" data-key-events="true"
													data-show-toggle="true" data-resizable="true"
													data-cookie="true" data-cookie-id-table="saveId"
													data-show-export="true" data-click-to-select="true"
													data-toolbar="#toolbar">
													<thead>
														<tr>
															<th data-field="id">NO</th>
															<th data-field="name" data-editable="true">Name</th>
															<th data-field="email" data-editable="true">Email</th>
															<th data-field="phone_no" data-editable="true">Phone
																No.</th>
															<th data-field="pinchIn" data-editable="true">PunchIn
																Time</th>
															<th data-field="late_coming_time" data-editable="true">Late
																Coming Time</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${reportData}" var="employee1"
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
			<div id="page-inner">
				<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function edit() {
			if (document.getElementById("edit").value == 'Year wise Filter') {
				document.getElementById("hideDropdown1").style.display = "block";
				document.getElementById("edit").value = "X";
			} else {

				document.getElementById("hideDropdown").style.display = "block";
				document.getElementById("edit").value = "Year wise Filter";
			}

		}
	</script>
	<script type="text/javascript">
		var start1 = moment().subtract(1, 'days');
		var end1 = moment().subtract(1, 'days');

		function cb1(start1, end1) {
			$('#reportrange1').html(
					start1.format('MMMM D, YYYY') + ' - '
							+ end1.format('MMMM D, YYYY'));
		}

		$('#reportrange1').daterangepicker(
				{
					startDate : start1,
					endDate : end1,
					ranges : {

						'Today' : [ moment(), moment() ],
						'Yesterday' : [ moment().subtract(1, 'days'),
								moment().subtract(1, 'days') ],
						'Last 7 Days' : [ moment().subtract(6, 'days'),
								moment() ],
						'Last 30 Days' : [ moment().subtract(29, 'days'),
								moment() ],
						'This Month' : [ moment().startOf('month'),
								moment().endOf('month') ],
						'Last Month' : [
								moment().subtract(1, 'month').startOf('month'),
								moment().subtract(1, 'month').endOf('month') ]
					}
				}, cb1);

		cb1(start1, end1);
	</script>

	<script>
		function chainSelect(current, target, target1) {
			var value1 = $(current)
					.on(
							'change',
							function() {

								if ($(this).find(':selected').val() != 'yearly') {
									document.getElementById("hideDropdown").style.display = "none";
									$(target).removeAttr('disabled');
									document.getElementById("hideDropdown1").style.display = "none";
									$(target1).prop('disabled', 'disabled')
											.val(null);
									var value = $(this).find(':selected')
											.text();
									document.getElementById("reportrange1").disabled = false;
									
									$(document).ready(()=>{ 
							            $("#month").val('00'); 
							        });
								} else {
									$("#year").val(
											$("#year option:first").val());
									document.getElementById("hideDropdown1").style.display = "block";
									$(target1).removeAttr('disabled');
									$("#month").val(
											$("#month option:first").val());
									document.getElementById("hideDropdown").style.display = "block";
									$(target).removeAttr('disabled');
									document.getElementById("reportrange1").disabled = true;
								}
								return value;
							});
			return value1;
		}
		month = chainSelect('select#filter', '#month', '#year');
	</script>


	<script src="assets/js/bootstrap.min.js"></script>


	<script src="js/data-table/tableExport.js"></script>
	<script src="js/data-table/bootstrap-table.js"></script>
	<script src="js/data-table/data-table-active.js"></script>
	<script src="js/data-table/bootstrap-table-resizable.js"></script>
	<script src="js/data-table/colResizable-1.5.source.js"></script>
	<script src="js/data-table/bootstrap-table-export.js"></script>

</body>