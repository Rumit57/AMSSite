<%-- 
    Document   : SA_company_Deatils
    Created on : 17 Dec, 2019, 9:59:25 AM
    Author     : rumit
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!------ Include the above in your HEAD tag ---------->

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
		<%@include file="header3.jsp"%>

		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<div id="mySidebar" class="sidebar">
					<ul class="nav" id="main-menu">
						<li><a href="superAdminDashboard"><i
								class="fa fa-dashboard"></i> Dashboard</a></li>
						<li><a href="saCompany"><i class="fa fa-pencil"></i>
								Company</a></li>
						<li><a href="saAdmin"><i class="fa fa-pencil"></i> Admin</a></li>
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
									<form action="updatePhotoCompany" enctype="multipart/form-data"
										method="post">
										<input type="hidden" name="objId"
											value="${detailsCompanys.objId}" required />
										<div class="profile-img1" style="border-style: solid">
											<img src="${url}${detailsCompanys.companyPhoto}"
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

								<input style="margin-right: 15px; visibility: visible"
									onclick="edit()" id="edit" type="submit"
									class="btn btn-primary pull-right" value="Edit Details" /> <br>
								<div class="col-sm-9">
									<div class="tab-content">
										<div class="tab-pane active" id="home">
											<hr>
											<form action="updateCompanyDetails" method="post">
												<input type="hidden" name="objId"
													value="${detailsCompanys.objId}" required />

												<div class="form-group">
													<div class="col-xs-6">
														<label for="name"><h4>Company name</h4></label> <input
															type="text" readonly class="form-control" name="name"
															id="name" placeholder="company name"
															value="${detailsCompanys.name}" required />
													</div>
												</div>
												<div class="form-group">
													<div class="col-xs-6">
														<label for="taxId"><h4>GSTIN NO</h4></label> <input
															type="text" readonly class="form-control" name="taxId"
															id="taxId" placeholder="Enter GST no."
															value="${detailsCompanys.taxId}" required />
													</div>
												</div>

												<div class="form-group">
													<div class="col-xs-6">
														<label for="phoneNumber"><h4>Mobile</h4></label> <input
															type="text" class="form-control" readonly
															name="phoneNumber" id="phoneNumber"
															placeholder="enter mobile number"
															value="${detailsCompanys.phoneNumber}" required />
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="companyEmail"><h4>Email</h4></label> <input
															type="email" class="form-control" readonly
															name="companyEmail" id="companyEmail"
															value="${detailsCompanys.companyEmail}" required />
													</div>
												</div>

												<div class="form-group">

													<div class="col-xs-6">
														<label for="address1"><h4>Address1</h4></label> <input
															type="text" class="form-control" readonly name="address1"
															id="address1" value="${detailsCompanys.address1}"
															required />
													</div>
												</div>

												<div class="form-group">

													<div class="col-xs-6">
														<label for="address2"><h4>Address2</h4></label> <input
															type="text" class="form-control" readonly name="address2"
															id="address2" value="${detailsCompanys.address2}"
															required />
													</div>
												</div>

												<div class="form-group">

													<div class="col-xs-6">
														<label for="city"><h4>City</h4></label> <input type="text"
															class="form-control" readonly id="city" name="city"
															value="${detailsCompanys.city}" required />
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="state"><h4>State</h4></label> <input
															type="text" class="form-control" readonly name="state"
															id="state" value="${detailsCompanys.state}" required />
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="country"><h4>Conutry</h4></label> <input
															type="text" class="form-control" readonly name="country"
															id="country" value="${detailsCompanys.country}" required />
													</div>
												</div>
												<div class="form-group">

													<div class="col-xs-6">
														<label for="Admin"><h4>Admin</h4></label> <input
															type="text" class="form-control" readonly
															value="${detailsCompanyAdminFirstName}" />
													</div>
												</div>
												<div class="form-group">
													<div class="col-xs-6">
														<label for="Admin_phone"><h4>Admin phone
																number</h4></label> <input type="text" class="form-control" readonly
															value="${detailsCompanyAdminMobile}" />
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
				document.getElementById("name").readOnly = false;
				document.getElementById("taxId").readOnly = false;
				document.getElementById("phoneNumber").readOnly = false;
				document.getElementById("companyEmail").readOnly = false;
				document.getElementById("address1").readOnly = false;
				document.getElementById("address2").readOnly = false;
				document.getElementById("city").readOnly = false;
				document.getElementById("state").readOnly = false;
				document.getElementById("country").readOnly = false;
				document.getElementById("update").style.visibility = "visible";
				document.getElementById("edit").value = "X";
				document.getElementById("edit").className = "btn btn-danger pull-right";
			} else {
				document.getElementById("name").readOnly = true;
				document.getElementById("taxId").readOnly = true;
				document.getElementById("phoneNumber").readOnly = true;
				document.getElementById("companyEmail").readOnly = true;
				document.getElementById("address1").readOnly = true;
				document.getElementById("address2").readOnly = true;
				document.getElementById("city").readOnly = true;
				document.getElementById("state").readOnly = true;
				document.getElementById("country").readOnly = true;
				document.getElementById("update").style.visibility = "hidden";
				document.getElementById("edit").value = "Edit Details";
				document.getElementById("edit").className = "btn btn-primary pull-right";
			}

		}

	</script>
</html>
