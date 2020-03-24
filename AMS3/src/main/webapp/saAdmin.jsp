<%-- 
    Document   : SA_admin
    Created on : 20 Jan, 2020, 2:30:38 PM
    Author     : rumit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

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
<link href="./plugins/sweetalert/css/sweetalert.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
.alert {
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
                    <circle class="path" cx="50" cy="50" r="20"
					fill="none" stroke-width="3" stroke-miterlimit="10" />
                </svg>
		</div>
	</div>
	<div id="wrapper">
		<%@include file="header3.jsp"%>

		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<div id="mySidebar" class="sidebar">
					<ul class="nav" id="main-menu">
						<li><a href="superAdminDashboard"><i
								class="fa fa-dashboard"></i> Dashboard</a></li>
						<li><a href="saCompany"><i class="fa fa-pencil"></i>
								Company</a></li>
						<li><a class="active-menu" href="#"><i
								class="fa fa-pencil"></i> Admin</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">Admin Data</h1>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="sparkline13-list">
							<div class="sparkline13-graph">
								<div class="datatable-dashv1-list custom-datatable-overright">
									<div id="toolbar">
										<button type="button" data-toggle="modal"
											data-target="#myModal" class="btn btn-primary">+ Add
											Admin</button>
										<!-- Modal-->
										<div id="myModal" tabindex="-1" role="dialog"
											aria-labelledby="exampleModalLabel" aria-hidden="true"
											class="modal" data-backdrop="static">
											<div role="document" class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h3 style="width: 100%">
															<center>Admin Registration</center>
														</h3>
														<button type="button" class="close" data-dismiss="modal">&times;</button>
													</div>
													<form action="addAdmin" method="post">
														<div class="modal-body">
															<span style="color: red">${Errors}</span>
															<div class="form-group">
																<label>First Name</label> <input type="text"
																	placeholder="Enter First Name..."
																	value="${data.firstName}" name="firstName"
																	class="form-control" required />
															</div>
															<div class="form-group">
																<label>Last Name</label> <input type="text"
																	placeholder="Enter Last Name..."
																	value="${data.lastName}" name="lastName"
																	class="form-control" required />
															</div>
															<div class="form-group">
																<label>Email</label> <input type="email"
																	placeholder="Enter Email..." value="${data.email}"
																	name="email" class="form-control" required />
															</div>
															<span style="color: red">${emailError}</span>
															<div class="form-group">
																<label>Password</label> <input type="password"
																	placeholder="Enter Password.." value="${data.password}"
																	name="password" class="form-control" required />
															</div>
															<div class="form-group">
																<label>Confirm Password</label> <input type="password"
																	placeholder="Enter Confirm Password..."
																	value="${cpass}" name="cPass" class="form-control"
																	required />
															</div>
															<span style="color: red">${passwordError}</span>
															<div class="form-group">
																<label>Company Name</label><br>
																 <select
																	name="companyId" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
																	style="width: 100%">
																	<option value="0">--Select company--</option>
																	<c:forEach items="${companyData}" var="companydata">
																		<option value="${companydata.objId}">${companydata.name}</option>
																	</c:forEach>
																</select>
															</div>
															<span style="color: red">${companyError}</span>

															<div class="form-group">
																<label>Phone number</label> <input type="text"
																	placeholder="Enter Phone Number..."
																	value="${data.mobile}" name="mobile"
																	class="form-control" required />
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
															<script>
															
															</script>
															<div class="form-group">
																<label>Address 1</label> <input type="text"
																	value="${data.address1}"
																	placeholder="Enter Address Line 1..." name="address1"
																	class="form-control" required />
															</div>
															<div class="form-group">
																<label>Address 2</label> <input type="text"
																	value="${data.address2}"
																	placeholder="Enter Address Line 2..." name="address2"
																	class="form-control" required />
															</div>
															<div class="form-group">
																<label>Pin Code</label> <input type="text"
																	value="${data.pincode}" placeholder="Enter Pin Code..."
																	name="pincode" class="form-control" required />
															</div>
															<div class="form-group">
																<label>City</label> <input type="text"
																	value="${data.city}" placeholder="Enter City..."
																	name="city" class="form-control" required />
															</div>
															<div class="form-group">
																<label>State</label> <input type="text"
																	value="${data.state}" placeholder="Enter State..."
																	name="state" class="form-control" required />
															</div>
															<div class="form-group">
																<label>Country</label> <input type="text"
																	value="${data.country}" placeholder="Enter Country..."
																	name="country" class="form-control" required />
															</div>
															
															<span style="color: red">${Errors}</span>

														</div>
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
										data-click-to-select="true" data-toolbar="#toolbar">
										<thead>
											<tr>
												<th data-field="id">No</th>
												<th data-field="name" data-editable="true">Name</th>
												<th data-field="Email" data-editable="true">Email</th>
												<th data-field="company" data-editable="true">Company
													Name</th>
												<th data-field="Phone" data-editable="true">Phone</th>
												<th data-field="action">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${admins}" var="admin" varStatus="theCount">
												<tr>
													<td>${theCount.count}</td>
													<td>${admin.firstName} ${admin.lastName}</td>
													<td>${admin.email}</td>
													<td>${admin.company.name}</td>
													<td>${admin.mobile}</td>
													<td>
														<center>
															<div class="dropdown dropleft">
																<div data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																	<i class="fa fa-ellipsis-v"></i>
																</div>
																<div class="dropdown-menu">
																	<li><a class="dropdown-item" href="edit?id=${admin.objId}"><i
																			class='glyphicon glyphicon-pencil'></i>&nbsp;&nbsp;Edit</a></li>
																	<li class="divider"></li>
																	<li style="cursor: pointer;"
																		onclick="sweet(${admin.company.objId})"><a class="dropdown-item"><i
																			class='glyphicon glyphicon-remove'></i>&nbsp;&nbsp;Delete</a></li>
																</div>
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
	<c:if test="${editclick==1}">
		<script type="text/javascript">
			$(window).load(function() {
				$('#myModalone').modal('show');
			});
		</script>

		<!--modal-->

		<div id="myModalone" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true" class="modal"
			data-backdrop="static">
			<div role="document" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h3 style="width: 100%">
							<center>Update Admin Details</center>
						</h3>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<form action="editAdmin" method="get">
						<div class="modal-body">
							<input type="hidden" name="id" value="${editadmins.objId}"
								required />
							<div class="form-group">
								<label>First Name</label> <input type="text"
									placeholder="Enter Name..." value="${editadmins.firstName}"
									name="fname" class="form-control" required />
							</div>
							<div class="form-group">
								<label>Last Name</label> <input type="text"
									placeholder="Enter Name..." value="${editadmins.lastName}"
									name="lname" class="form-control" required />
							</div>
							<div class="form-group">
								<label>Email</label> <input type="text"
									placeholder="Enter Email..." value="${editadmins.email}"
									name="email" class="form-control" required />
							</div>
							<div class="form-group">
								<label>Company Name</label><br> <select name="cName"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown"
									style="width: 100%">
									<option value="${editadmins.company.objId}">${editadmins.company.name}</option>
									<c:forEach items="${companyData}" var="companydata">
									
									<c:set var="day" scope="session" value="${companydata.objId}"/>
									<c:set var="new_day" scope="session" value="${editadmins.company.objId}"/>
									<c:if test="${day ne new_day}">
										<option value="${companydata.objId}">${companydata.name}</option>
									</c:if>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label>Phone number</label> <input type="text"
									placeholder="Enter Phone Number..."
									value="${editadmins.mobile}" name="phone" class="form-control"
									required />
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
								<label>Address 1</label> <input type="text"
									placeholder="Enter Address Line 1..."
									value="${editadmins.address1}" name="add1" class="form-control"
									required />
							</div>
							<div class="form-group">
								<label>Address 2</label> <input type="text"
									placeholder="Enter Address Line 2..."
									value="${editadmins.address2}" name="add2" class="form-control"
									required />
							</div>
							<div class="form-group">
								<label>Pin Code</label> <input type="text"
									placeholder="Enter Pin Code..." value="${editadmins.pincode}"
									name="pinCode" class="form-control" required />
							</div>
							<div class="form-group">
								<label>City</label> <input type="text"
									placeholder="Enter City..." value="${editadmins.city}"
									name="city" class="form-control" required />
							</div>
							<div class="form-group">
								<label>State</label> <input type="text"
									placeholder="Enter State..." value="${editadmins.state}"
									name="state" class="form-control" required />
							</div>
							<div class="form-group">
								<label>Country</label> <input type="text"
									placeholder="Enter Country..." value="${editadmins.country}"
									name="country" class="form-control" required />
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
			location.replace("success?delete="+id1)
			 //swal("Deleted!", "Your imaginary file has been deleted.", "success");
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
<!-- 	<script src="js/data-table/colResizable-1.5.source.js"></script>
		<script src="js/data-table/bootstrap-table-editable.js"></script>  -->

</body>
</html>
