<%-- 
    Document   : yesterday_missed_punch
    Created on : 20 Jan, 2020, 2:30:59 PM
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.min.css" />
</head>

<body>


	<div class="form-group">
		<div class="input-group date" id="datePicker">
			<input type="text" class="form-control" name="date" value="">
			<span class="input-group-addon"> <i
				class="glyphicon glyphicon-calendar"></i>
			</span>
		</div>
	</div>


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
		$('#datePicker').datepicker(
				'setDate', today);
	</script>

</body>
</html>