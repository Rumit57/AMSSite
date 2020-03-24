<%-- 
    Document   : manageHR
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
<link href="./plugins/sweetalert/css/sweetalert.css" rel="stylesheet">
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
						<li><a href="adminDashboard"><i class="fa fa-dashboard"></i>
								Dashboard</a></li>
						<li><a class="active-menu" href="#"><i class="fa fa-edit"></i>
								HR </a></li>
						<li><a href="adminEmployee"><i class="fa fa-edit"></i>
								Employee </a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">HR data</h1>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="sparkline13-list">
							<div class="sparkline13-graph">
								<div class="datatable-dashv1-list custom-datatable-overright">
									<div id="toolbar">
										<button type="button" data-toggle="modal"
											data-target="#myModal" class="btn btn-primary">+ Add
											HR</button>
										<!-- Modal-->

										<div id="myModal" tabindex="-1" role="dialog"
											aria-labelledby="exampleModalLabel" aria-hidden="true"
											class="modal" data-backdrop="static">
											<div role="document" class="modal-dialog">
												<div class="modal-content">

													<div class="modal-header">
														<h3 style="width: 100%">
															<center>HR Registration</center>
														</h3>
														<button type="button" class="close" data-dismiss="modal">&times;</button>
													</div>
													<form action="addHR" method="post">
														<div class="modal-body">

															<div class="form-group">
																<label>First Name</label> <input type="text"
																	placeholder="Enter Name..." name="firstName" required
																	class="form-control" value="${data.firstName}" />
															</div>

															<div class="form-group">
																<label>Last Name</label> <input type="text"
																	placeholder="Enter Last Name..." name="lastName"
																	required class="form-control" value="${data.lastName}" />
															</div>

															<div class="form-group">
																<label>Email</label> <input type="text"
																	placeholder="Enter Email..." name="email" required
																	class="form-control" value="${data.email}" />
															</div>
															<div class="form-group">
																<label>Password</label> <input type="password"
																	name="password" placeholder="Enter Password.." required
																	class="form-control" value="${data.password}" />
															</div>
															<div class="form-group">
																<label>Confirm Password</label> <input type="password"
																	placeholder="Enter Confirm Password..." name="cpass"
																	value="${cpass}" required class="form-control" />
															</div>
															<span style="color: red">${passwordError}</span>

															<div class="form-group">
																<label>Phone number</label> <input type="text"
																	placeholder="Enter Phone Number..." required
																	name="mobile" value="${data.mobile}"
																	class="form-control" />
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
																<label>Address 1</label> <input type="text"
																	placeholder="Enter Address Line 1..." required
																	name="address1" class="form-control"
																	value="${data.address1}" />
															</div>
															<div class="form-group">
																<label>Address 2</label> <input type="text"
																	placeholder="Enter Address Line 2..." required
																	name="address2" class="form-control"
																	value="${data.address2}" />
															</div>
															<div class="form-group">
																<label>Pin Code</label> <input type="text"
																	placeholder="Enter Pin Code..." required name="pincode"
																	class="form-control" value="${data.pincode}" />
															</div>
															<div class="form-group">
																<label>City</label> <input type="text"
																	placeholder="Enter City..." required name="city"
																	class="form-control" value="${data.city}" />
															</div>
															<div class="form-group">
																<label>State</label> <input type="text"
																	placeholder="Enter State..." required name="state"
																	class="form-control" value="${data.state}" />
															</div>
															<div class="form-group">
																<label>Country</label> <input type="text"
																	placeholder="Enter Country..." required name="country"
																	class="form-control" value="${data.country}" />
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
										data-click-to-select="true" data-toolbar="#toolbar">
										<thead>
											<tr>
												<th data-field="id">ID</th>
												<th data-field="name" data-editable="true">Name</th>
												<th data-field="Email" data-editable="true">Email</th>
												<th data-field="Phone" data-editable="true">Phone</th>
												<th data-field="action">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="hr" items="${hrs}" varStatus="theCount">
												<tr>
													<td>${theCount.count}</td>
													<td>${hr.firstName}&nbsp;${hr.lastName}</td>
													<td>${hr.email}</td>
													<td>${hr.mobile}</td>
													<td>
														<center>
															<div class="dropdown">
																<div data-toggle="dropdown">
																	<i class="fa fa-ellipsis-v"></i>
																</div>
																<ul class="dropdown-menu">
																	<li><a href="editHR?id=${hr.objId}"><i
																			class='glyphicon glyphicon-pencil'></i>&nbsp;&nbsp;Edit</a></li>
																	<li class="divider"></li>
																	<li style="cursor: pointer;"
																		onclick="sweet(${hr.objId})"><a><i
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


			<c:if test="${error==1}">
				<script type="text/javascript">
			$(window).load(function() {
				$('#myModal').modal('show');
			});
		</script>
			</c:if>
			<c:if test="${editHR==1}">
				<script type="text/javascript">
			$(window).load(function() {
				$('#updateModal').modal('show');
			});
		</script>
				<div id="updateModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true"
					class="modal" data-backdrop="static">
					<div role="document" class="modal-dialog">
						<div class="modal-content">
							<form action="updateHR" method="post">
								<div class="modal-header">
									<h3 style="width: 100%">
										<center>HR Registration</center>
									</h3>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<div class="modal-body">
									<input type="hidden" name="id" value="${edithr.objId}" />
									<div class="form-group">
										<label>First Name</label> <input type="text"
											value="${edithr.firstName}" required name="fname"
											placeholder="Enter First Name..." class="form-control" />
									</div>
									<div class="form-group">
										<label>LastName</label> <input type="text"
											value="${edithr.lastName}" required name="lname"
											placeholder="Enter Last Name..." class="form-control" />
									</div>
									<div class="form-group">
										<label>Email</label> <input type="text"
											value="${edithr.email}" required name="email"
											placeholder="Enter Email..." class="form-control" />
									</div>

									<div class="form-group">
										<label>Status</label><br> <select name="sid"
											class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" style="width: 100%">
											<option value="${edithr.status.objId}">${edithr.status.status}</option>
											<c:forEach items="${statusData}" var="statusdata">

												<c:set var="oldStatus" scope="session" value="${statusdata.objId}" />
												<c:set var="newStatus" scope="session"
													value="${edithr.status.objId}" />
												<c:if test="${oldStatus ne newStatus}">
													<option value="${statusdata.objId}">${statusdata.status}</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label>Phone number</label> <input type="text"
											placeholder="Enter Phone Number..." required name="phone"
											value="${edithr.mobile}" class="form-control" />
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
											placeholder="Enter Address Line 1..." required name="add1"
											value="${edithr.address1}" class="form-control" />
									</div>
									<div class="form-group">
										<label>Address 2</label> <input type="text"
											placeholder="Enter Address Line 2..." required name="add2"
											value="${edithr.address2}" class="form-control" />
									</div>
									<div class="form-group">
										<label>Pin Code</label> <input type="text"
											placeholder="Enter Pin Code..." required name="pincode"
											value="${edithr.pincode}" class="form-control" />
									</div>
									<div class="form-group">
										<label>City</label> <input type="text"
											placeholder="Enter City..." required name="city"
											value="${edithr.city}" class="form-control" />
									</div>
									<div class="form-group">
										<label>State</label> <input type="text"
											placeholder="Enter State..." required name="state"
											value="${edithr.state}" class="form-control" />
									</div>
									<div class="form-group">
										<label>Country</label> <input type="text"
											placeholder="Enter Country..." required name="country"
											value="${edithr.country}" class="form-control" />
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




			<div id="page-inner">
				<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
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
			location.replace("deleteHR?delete="+id1)
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

</body>
</html>