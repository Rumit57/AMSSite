<%-- 
    Document   : manage_employee2
    Created on : 17 Dec, 2019, 9:59:25 AM
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
<link href="./plugins/sweetalert/css/sweetalert.css" rel="stylesheet" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
<
style>.alert {
	width: 55%;
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
						<li><a class="active-menu" href="#"><i class="fa fa-edit"></i>
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
				<h1 class="page-header">Employee Details</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">Employee Details</a></li>
					<li class="active">Data</li>
				</ol>
				${link}
				<div class="row">
					<div class="col-md-12">
						<div class="data-table-area mg-tb-15">
							<div class="container-fluid">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="sparkline13-list">
											<div class="sparkline13-graph">
												<div>

													<button type="button" data-toggle="modal"
														data-target="#myModal" class="btn btn-primary">+
														Add Employee</button>
													&nbsp; <input class="btn btn-success" onclick="edit()"
														type="button" id="edit" value="Import" />&nbsp; <a
														href="http://127.0.0.1:8887/CSV/format.csv"><i
														class="glyphicon glyphicon-download-alt"></i> CSV Format</a>
													<form class="form-inline" action="importData1"
														enctype="multipart/form-data" method="post">

														<div id="importShow" style="visibility: hidden;">

															<div class="form-group">
																<input type="file" name="file1" />
															</div>
															<div id="importShow" class="form-group">
																<input type="submit" class="btn btn-primary"
																	value="Upload" required />
															</div>
														</div>
													</form>
												</div>
												<div
													class="datatable-dashv1-list custom-datatable-overright">
													<div id="toolbar">
														<select class="form-control">
															<option value="">Export Basic</option>
															<option value="all">Export All</option>
															<option value="selected">Export Selected</option>
														</select>
													</div>

													<!-- Modal-->
													<div id="myModal" tabindex="-1" role="dialog"
														aria-labelledby="exampleModalLabel" aria-hidden="true"
														class="modal" data-backdrop="static">
														<div role="document" class="modal-dialog">
															<div class="modal-content">
																<form action="addEmployee">
																	<div class="modal-header">
																		<h3 style="width: 100%">
																			<center>Employee Registration</center>
																		</h3>
																		<button type="button" class="close"
																			data-dismiss="modal">&times;</button>
																	</div>
																	<div class="modal-body">

																		<div class="form-group">
																			<label>First Name</label> <input type="text" required
																				name="firstName" value="${data.firstName}"
																				placeholder="Enter First Name" class="form-control" />
																		</div>
																		<div class="form-group">
																			<label>Last Name</label> <input type="text" required
																				name="lastName" value="${data.lastName}"
																				placeholder="Enter Last Name" class="form-control" />
																		</div>
																		<div class="form-group">
																			<label>Address 1</label> <input type="text"
																				value="${data.address1}"
																				placeholder="Enter Address Line 1..."
																				name="address1" class="form-control" required />
																		</div>
																		<div class="form-group">
																			<label>Address 2</label> <input type="text"
																				value="${data.address2}"
																				placeholder="Enter Address Line 2..."
																				name="address2" class="form-control" required />
																		</div>
																		<div class="form-group">
																			<label>Gender</label><br> <label
																				class="form-control"> <input type="radio"
																				class="form-check-input" checked name="gender"
																				value="M" /> Male &nbsp;&nbsp; <input type="radio"
																				class="form-check-input" name="gender" value="F" />
																				Female
																			</label>
																		</div>

																		<div class="form-group">
																			<label>Email</label> <input type="text"
																				value="${data.email}" name="email" required
																				placeholder="Enter Email" class="form-control" />
																		</div>
																		<div class="form-group">
																			<label>Mobile Number</label> <input type="text"
																				value="${data.mobile}" name="mobile" required
																				placeholder="Enter Phone Number "
																				class="form-control" />
																		</div>
																		<div class="form-group">
																			<label>User Type</label><br> <select
																				class="btn btn-default dropdown-toggle"
																				value="${data.userType}" name="userType"
																				data-toggle="dropdown" style="width: 100%">
																				<option value="">--Select User Type--</option>
																				<option value="Employee">Employee</option>
																				<option value="Intern">Intern</option>
																			</select>
																		</div>
																		<div class="form-group">
																			<label>City</label> <input type="text" required
																				value="${data.city}" name="city"
																				placeholder="Enter City.." class="form-control" />
																		</div>
																		<div class="form-group">
																			<label>Pin Code</label> <input type="text" required
																				value="${data.pincode}" name="pincode"
																				placeholder="Enter pincode.." class="form-control" />
																		</div>
																		<div class="form-group">
																			<label>State</label> <input type="text" required
																				value="${data.state}" name="state"
																				placeholder="Enter state.." class="form-control" />
																		</div>
																		<div class="form-group">
																			<label>Country</label> <input type="text" required
																				value="${data.country}" name="country"
																				placeholder="Enter country.." class="form-control" />
																		</div>


																	</div>
																	<center>
																		<span style="color: red">${errors}</span>
																	</center>
																	<div class="modal-footer">
																		<button type="button" data-dismiss="modal"
																			class="btn btn-secondary">Close</button>
																		<input type="submit" class="btn btn-primary"
																			value="Submit" />
																	</div>
																</form>
															</div>
														</div>
													</div>
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
															<th data-field="state" data-checkbox="true"></th>
															<th data-field="id">No</th>
															<th data-field="name" data-editable="true">Name</th>
															<th data-field="Email" data-editable="true">Email</th>
															<th data-field="Phone" data-editable="true">Phone</th>
															<th data-field="action">Action</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${employees}" var="employee"
															varStatus="theCount">

															<tr>
																<td></td>
																<td>${theCount.count}</td>
																<td>${employee.firstName}&nbsp;${employee.lastName}</td>
																<td>${employee.email}</td>
																<td>${employee.mobile}</td>
																<td>
																	<center>
																		<div class="dropdown">
																			<div data-toggle="dropdown">
																				<i class="fa fa-ellipsis-v"></i>
																			</div>
																			<ul class="dropdown-menu">
																				<li><a href="editEmployee?id=${employee.objId}"><i
																						class='glyphicon glyphicon-pencil'></i>&nbsp;&nbsp;Edit</a></li>
																				<li class="divider"></li>
																				<li style="cursor: pointer;"
																					onclick="sweet(${employee.objId})"><a><i
																						class='glyphicon glyphicon-remove'></i>&nbsp;&nbsp;Delete</a></li>
																			</ul>
																		</div>
																	</center>
																</td>
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


	<c:if test="${error==1}">
		<script type="text/javascript">
			$(window).load(function() {
				$('#myModal').modal('show');
			});
		</script>
	</c:if>

	<c:if test="${editemployee==1}">
		<script type="text/javascript">
			$(window).load(function() {
				$('#updateModal').modal('show');
			});
		</script>
		<!--modal-->
		<div id="updateModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true" class="modal"
			data-backdrop="static">
			<div role="document" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h3 style="width: 100%">
							<center>Update Employee Details</center>
						</h3>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<form action="updateEmployee">
						<div class="modal-body">
							<input type="hidden" name="id" value="${editEmployee.objId}" />
							<div class="form-group">
								<label>First Name</label> <input type="text"
									value="${editEmployee.firstName}" required name="fname"
									placeholder="Enter First Name..." class="form-control" />
							</div>
							<div class="form-group">
								<label>LastName</label> <input type="text"
									value="${editEmployee.lastName}" required name="lname"
									placeholder="Enter Last Name..." class="form-control" />
							</div>
							<div class="form-group">
								<label>Address 1</label> <input type="text"
									placeholder="Enter Address Line 1..." required name="add1"
									value="${editEmployee.address1}" class="form-control" />
							</div>
							<div class="form-group">
								<label>Address 2</label> <input type="text"
									placeholder="Enter Address Line 2..." required name="add2"
									value="${editEmployee.address2}" class="form-control" />
							</div>
							<div class="form-group">
								<label>Email</label> <input type="text"
									value="${editEmployee.email}" required name="email"
									placeholder="Enter Email..." class="form-control" />
							</div>
							<div class="form-group">
								<label>Phone number</label> <input type="text"
									placeholder="Enter Phone Number..." required name="phone"
									value="${editEmployee.mobile}" class="form-control" />
							</div>
							<div class="form-group">
								<label>User Type</label><br> <select
									class="btn btn-default dropdown-toggle"
									value="${editEmployee.userType}" name="userType"
									data-toggle="dropdown" style="width: 100%">
									<option value="${editEmployee.userType}">${editEmployee.userType}</option>
									<c:choose>

										<c:when test="${editEmployee.userType eq 'Intern'}">
											<option value="Employee">Employee</option>
										</c:when>

										<c:otherwise>
											<option value="Intern">Intern</option>
										</c:otherwise>

									</c:choose>
								</select>
							</div>
							<div class="form-group">
								<label>Gender</label><br> <label class="form-control">
									<c:choose>
										<c:when test="${gender=='1'}">
											<input type="radio" checked class="form-check-input"
												value="M" name="optradio" />
										Male &nbsp;&nbsp; 
										<input type="radio" class="form-check-input" value="F"
												name="optradio" /> Female
									</c:when>
										<c:otherwise>
											<input type="radio" class="form-check-input" value="M"
												name="optradio" />
										Male &nbsp;&nbsp; 
										<input type="radio" checked class="form-check-input" value="F"
												name="optradio" /> Female
									</c:otherwise>
									</c:choose>
								</label>
							</div>

							<div class="form-group">
								<label>Pin Code</label> <input type="text"
									placeholder="Enter Pin Code..." required name="pincode"
									value="${editEmployee.pincode}" class="form-control" />
							</div>
							<div class="form-group">
								<label>City</label> <input type="text"
									placeholder="Enter City..." required name="city"
									value="${editEmployee.city}" class="form-control" />
							</div>
							<div class="form-group">
								<label>State</label> <input type="text"
									placeholder="Enter State..." required name="state"
									value="${editEmployee.state}" class="form-control" />
							</div>
							<div class="form-group">
								<label>Country</label> <input type="text"
									placeholder="Enter Country..." required name="country"
									value="${editEmployee.country}" class="form-control" />
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
	</c:if>

	<script type="text/javascript">
		function sweet(id) {
			swal({
				title : "Are you sure?",
				text : "Your will not be able to recover this data!",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : "btn-danger",
				confirmButtonText : "Yes, delete it!",
				closeOnConfirm : false
			}, function() {
			     confirm(id);
			    }); 
		
		};
		
		function confirm(id1)
		{
			location.replace("deleteEmployee?delete="+id1)
			 //swal("Deleted!", "Your imaginary file has been deleted.", "success");
		}
	</script>

	<script>
		function edit() {
			if (document.getElementById("edit").value == 'Import') {
				document.getElementById("importShow").style.visibility = "visible";
				document.getElementById("edit").value = "X";
				document.getElementById("edit").className = "btn btn-danger";
			} else {
				document.getElementById("importShow").style.visibility = "hidden";
				document.getElementById("edit").value = "Import";
				document.getElementById("edit").className = "btn btn-success";
			}
		}

	</script>

	<script src="./plugins/sweetalert/js/sweetalert.min.js"></script>
	<script src="./plugins/sweetalert/js/sweetalert.init.js"></script>
	<script src="vendor/popper.js/umd/popper.min.js"></script>
	<script src="js/data-table/bootstrap-table.js"></script>
	<script src="js/data-table/tableExport.js"></script>
	<script src="js/data-table/data-table-active.js"></script>
	<script src="js/data-table/bootstrap-editable.js"></script>
	<script src="js/data-table/bootstrap-table-resizable.js"></script>
	<script src="js/data-table/bootstrap-table-export.js"></script>

</body>
</html>