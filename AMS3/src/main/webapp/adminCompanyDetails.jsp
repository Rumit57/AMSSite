<%-- 
    Document   : Admin_Company_Details
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
<link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
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
	<link href="css/style_1.css" rel="stylesheet">
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

						<li><a href="adminDashboard"><i class="fa fa-dashboard"></i>
								Dashboard</a></li>
						<li><a href="manageHR"><i class="fa fa-edit"></i> HR </a></li>
						<li><a href="adminEmployee"><i class="fa fa-edit"></i>Employee </a></li>
					</ul>
				</div>
			</div>
		</nav>



		<div id="page-wrapper">
			<div class="header">
				<br>

				<div class="row">

					<div class="col-md-12">
						<div class="sparkline13-list">

							<div class="row">
								<center>
									<div class="col-sm-10">
										<h1>Company Details</h1>
									</div>
								</center>
							</div>
							<br>

							<div class="row">
								<div class="col-sm-3">
									<form action="updatePhotoCompanyAdmin"
										enctype="multipart/form-data" method="post">
										<input type="hidden" name="objId" value="${company1.objId}"
											required />
										<div class="profile-img1" style="border-style: solid">
											<img src="${url}${company1.companyPhoto}"
												class="img-thumbnail" max-width="-webkit-fill-available"
												max-height="-webkit-fill-available" alt="" />
											<div>
												<input type="file" name="imageFile" value="Change Photo"
													required="required" />
											</div>
										</div>
										<br>
										<center>
											<input type="submit" class="btn btn-success"
												value="Update Photo">
										</center>
									</form>
								</div>

								<input style="margin-right: 15px;" onclick="edit()" id="edit"
									type="submit" class="btn btn-primary pull-right"
									value="Edit Details" /> <br>
								<div class="col-sm-9">



									<div class="tab-content">

										<div class="tab-pane active" id="home">
											<hr>
											<form class="form" action="adminCompanyDetailsUpdate"
												method="post" id="registrationForm">
												<input type="hidden" readonly class="form-control" name="id"
													value="${company1.objId}">
												<div class="form-group">

													<div class="col-xs-6">
														<label for="company_name"><h4>Company name</h4></label> <input
															type="text" readonly class="form-control"
															id="company_name" name=name value="${company1.name}">
													</div>
												</div>
												<div class="form-group"></div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="GST"><h4>GSTIN NO</h4></label> <input
															type="text" readonly class="form-control" id="gst"
															name="gstin" value="${company1.taxId}">
													</div>
												</div>



												<div class="form-group">
													<div class="col-xs-6">
														<label for="mobile"><h4>Mobile</h4></label> <input
															type="text" class="form-control" readonly name="mobile"
															id="mobile" value="${company1.phoneNumber}">
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="email"><h4>Email</h4></label> <input
															type="email" class="form-control" readonly name="email"
															id="email" value="${company1.companyEmail}">
													</div>
												</div>

												<div class="form-group">

													<div class="col-xs-6">
														<label for="Address1"><h4>Address1</h4></label> <input
															type="text" class="form-control" readonly name="add1"
															id="address1" value="${company1.address1}">
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="Address2"><h4>Address2</h4></label> <input
															type="text" class="form-control" readonly name="add2"
															id="address2" value="${company1.address2}">
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="City"><h4>City</h4></label> <input type="text"
															class="form-control" readonly id="city" name="city"
															value="${company1.city}">
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="State"><h4>State</h4></label> <input
															type="text" class="form-control" readonly name="state"
															id="state" value="${company1.state}">
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="pin"><h4>PIN CODE</h4></label> <input
															type="text" class="form-control" readonly name="pincode"
															id="pincode" value="${company1.pincode}">
													</div>
												</div>

												<div class="form-group">

													<div class="col-xs-6">
														<label for="Conutry"><h4>Conutry</h4></label> <input
															type="text" class="form-control" readonly name="country"
															id="conutry" value="${company1.country}">
													</div>
												</div>


												<div class="form-group">

													<div class="col-xs-12">
														<br>
														<center>
															<button type="submit"
																style="width: 20%; visibility: hidden"
																class="btn btn-primary" name="update" id="update"
																value="8866291338">Update</button>
														</center>
													</div>
												</div>
											</form>
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

	<script>
		function edit() {
			if (document.getElementById("edit").value == 'Edit Details') {
				document.getElementById("company_name").readOnly = false;
				document.getElementById("mobile").readOnly = false;
				document.getElementById("email").readOnly = false;
				document.getElementById("address1").readOnly = false;
				document.getElementById("address2").readOnly = false;
				document.getElementById("city").readOnly = false;
				document.getElementById("state").readOnly = false;
				document.getElementById("pincode").readOnly = false;
				document.getElementById("conutry").readOnly = false;
				document.getElementById("update").style.visibility = "visible";
				document.getElementById("edit").value = "X";
				document.getElementById("edit").className = "btn btn-danger pull-right";

			} else {
				document.getElementById("company_name").readOnly = true;
				document.getElementById("mobile").readOnly = true;
				document.getElementById("email").readOnly = true;
				document.getElementById("address1").readOnly = true;
				document.getElementById("address2").readOnly = true;
				document.getElementById("city").readOnly = true;
				document.getElementById("state").readOnly = true;
				document.getElementById("pincode").readOnly = true;
				document.getElementById("conutry").readOnly = true;
				document.getElementById("update").style.visibility = "hidden";
				document.getElementById("edit").value = "Edit Details";
				document.getElementById("edit").className = "btn btn-primary pull-right";
			}

		}
	</script>
</html>