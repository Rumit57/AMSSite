<%-- 
    Document   : HRProfile2
    Created on : 16 Jan, 2020, 11:02:19 AM
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
<style>
.emp-profile1 {
	padding: 3%;
	padding-left: 3%;
	margin-top: 1%;
	margin-bottom: 3%;
	margin-left: 10px;
	border-radius: 0.5rem;
	background: #fff;
}

.profile-img1 {
	text-align: center;
}

.profile-img1 img1 {
	width: 70%;
	height: 100%;
}

.profile-img1 .file {
	position: relative;
	overflow: hidden;
	margin-top: -20%;
	width: 70%;
	border: none;
	border-radius: 0;
	font-size: 15px;
	background: #212529b8;
}

.profile-img1 .file input {
	position: absolute;
	opacity: 0;
	right: 0;
	top: 0;
}

.profile-head1 h5 {
	color: #333;
}

.profile-head1 h6 {
	color: #0062cc;
}

.profile-edit-btn1 {
	border: none;
	border-radius: 1.5rem;
	width: 70%;
	padding: 2%;
	font-weight: 600;
	color: #6c757d;
	cursor: pointer;
	left: 200px;
}

.proile-rating1 {
	font-size: 12px;
	color: #818182;
	margin-top: 5%;
}

.proile-rating1 span {
	color: #495057;
	font-size: 15px;
	font-weight: 600;
}

.profile-head1 .nav-tabs1 {
	margin-bottom: 5%;
}

.profile-head1 .nav-tabs1 .nav-link1 {
	font-weight: 600;
	border: none;
}

.profile-head1 .nav-tabs1 .nav-link1.active {
	width: 130%;
	border: none;
	border-bottom: 2px solid #0062cc;
}

.profile-work1 {
	padding: 14%;
	margin-top: -15%;
}

.profile-work1 p1 {
	font-size: 12px;
	color: #818182;
	font-weight: 600;
	margin-top: 10%;
}

.profile-work1 a {
	text-decoration: none;
	color: #495057;
	font-weight: 600;
	font-size: 14px;
}

.profile-work1 ul {
	list-style: none;
}

.profile-tab1 label {
	font-weight: 600;
}

.profile-tab1 p {
	padding: 0;
	font-weight: 600;
	color: #0062cc;
	color: teal;
}

.img {
	position: fixed;
	max-width: 100%;
	height: auto;
	vertical-align: middle;
	border: 0;
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
				<br>
				<ol class="breadcrumb">
					<h1>
						<li class="active">HR Profile</li>
					</h1>
				</ol>

				<div style="border-radius: 1.5rem" class="row">
					<div class="col-md-12">
						<div class="sparkline13-list">
							<div class="row">
								<div class="col-md-4">
									<form action="updatePhotoHR" enctype="multipart/form-data"
										method="post">
										<div class="profile-img1">
											<img src="${url}${profilePhoto}"
												max-width="-webkit-fill-available"
												max-height="-webkit-fill-available" alt="" />
											<div class="file btn btn-lg btn-primary">
												Change Photo <input type="file" name="imageFile"
													required="required" />
											</div>
											<input type="submit" class="btn btn-success"
												value="Update Photo">
										</div>
									</form>
								</div>

								<div class="col-md-6">
									<div class="profile-head1">
										<h3 style="text-transform: uppercase;">${name}</h3>


										<ul class="nav nav-tabs1" id="myTab" role="tablist">
											<li class="nav-item"><a class="nav-link1 active"
												id="home-tab" data-toggle="tab" href="#home" role="tab"
												aria-controls="home" aria-selected="true">Personal
													details</a></li>
										</ul>
									</div>
								</div>

								<div class="col-md-2">
									<input type="button" class="profile-edit-btn1" onclick="edit()"
										id="edit" value="Edit Profile" />
										
									<button type="button" id="cancel" class="btn btn-danger" onclick="cancel()"
										 style="visibility: hidden">X</button>
								</div>

								<div class="col-md-8">
									<div class="tab-content profile-tab1">
										<form action="hrProfileUpdate" method="post">
											<input type="hidden" readonly class="form-control"
												name="user_id" id="user_id" value="${userId}" required /> <br>
											<div class="row">
												<div class="col-md-2">
													<label>Name</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-3">
													<input type="text" readonly class="form-control"
														name="fname" id="fname" value="${fname}" required />
												</div>
												<div class="col-md-3">
													<input type="text" readonly class="form-control"
														name="lname" id="lname" value="${lname}" required />
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-md-2">
													<label>Email</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="email" readonly class="form-control"
														name="email" id="email" value="${email}" required />
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-md-2">
													<label>Phone</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="text" readonly class="form-control"
														name="phone" id="phone" value="${phone}" required />
												</div>
											</div>
											<br>
											<div id="addressHide" class="row">
												<div class="col-md-2">
													<label>Address</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="text" readonly class="form-control" name="add"
														id="add" value="${address}" required />
												</div>
											</div>
											<div id="address1Show" style="display: none" class="row">
												<div class="col-md-2">
													<label>Address 1</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="text" class="form-control" name="add1"
														id="add1" value="${add1}" required />
												</div>
												<br>
											</div>

											<div id="address2Show" style="display: none" class="row">
												<div class="col-md-2">
													<label>Address 2</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="text" class="form-control" name="add2"
														id="add2" value="${add2}" required />
												</div>

											</div>

											<div class="row">
												<br>
												<div class="col-md-2">
													<label>Gender</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="text" readonly class="form-control"
														name="gender" id="gender" value="${gender}" required />
												</div>
											</div>
											<br>

											<div class="row">
												<div class="col-md-2">
													<label>City</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="text" readonly class="form-control"
														name="city" id="city" value="${city}" required />
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-md-2">
													<label>State</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="text" readonly class="form-control"
														name="state" id="state" value="${state}" required />
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-md-2">
													<label>Country</label>
												</div>
												<div class="col-md-1">
													<label>:-</label>
												</div>
												<div class="col-md-6">
													<input type="text" readonly class="form-control"
														name="country" id="country" value="${country}" required />
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-md-8">
													<center>
														<button type="submit" id="update" class="btn btn-primary"
															name="update" style="width: 20%; visibility: hidden">Update</button>
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

			<script>
				function edit() {
					var name = document.getElementById("fname").readOnly = false;
					var name = document.getElementById("lname").readOnly = false;
					var email = document.getElementById("email").readOnly = false;
					var phone = document.getElementById("phone").readOnly = false;
					var address = document.getElementById("add").readOnly = false;
					var city = document.getElementById("city").readOnly = false;
					var state = document.getElementById("state").readOnly = false;
					var country = document.getElementById("country").readOnly = false;
					document.getElementById("addressHide").style.display = "none";
					document.getElementById("address1Show").style.display = "block";
					document.getElementById("address2Show").style.display = "block";
					document.getElementById("update").style.visibility = "visible";
					document.getElementById("edit").style.visibility = "hidden";
					document.getElementById("cancel").style.visibility = "visible";
				}
			
				function cancel() {
					var name = document.getElementById("fname").readOnly = true;
					var name = document.getElementById("lname").readOnly = true;
					var email = document.getElementById("email").readOnly = true;
					var phone = document.getElementById("phone").readOnly = true;
					var address = document.getElementById("add").readOnly = true;
					var city = document.getElementById("city").readOnly = true;
					var state = document.getElementById("state").readOnly = true;
					var country = document.getElementById("country").readOnly = true;
					document.getElementById("addressHide").style.display = "block";
					document.getElementById("address1Show").style.display = "none";
					document.getElementById("address2Show").style.display = "none";
					document.getElementById("update").style.visibility = "hidden";
					document.getElementById("edit").style.visibility = "visible";
					document.getElementById("cancel").style.visibility = "hidden";
				}
			</script>

			<div id="page-inner">
				<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
	<script src="assets/js/jquery-1.10.2.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>



</body>
</html>

