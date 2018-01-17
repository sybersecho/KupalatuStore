<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Manage Supplier</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="<c:url value="/resources/bootstrap/3.3.5/css/bootstrap.min.css"/>">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<c:url value="/resources/font-awesome/4.4.0/css/font-awesome.min.css"/>">
<!-- Ionicons -->
<link rel="stylesheet"
	href="<c:url value="/resources/ionicons/2.0.1/css/ionicons.min.css"/>">
<!-- Bootstrap time Picker -->
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/timepicker/bootstrap-timepicker.min.css"/>">
<!-- Select2 -->
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/select2/select2.min.css"/>">
<!-- Theme style -->
<link rel="stylesheet"
	href="<c:url value="/resources/dist/css/AdminLTE.min.css"/>">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
<link rel="stylesheet"
	href="<c:url value="/resources/dist/css/skins/skin-blue.min.css"/>">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header"> <!-- Logo --> <a
			href="index2.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>A</b>LT</span> <!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>Admin</b>LTE</span>
		</a> <!-- Header Navbar --> <nav class="navbar navbar-static-top"
			role="navigation"> <!-- Sidebar toggle button--> <a href="#"
			class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span
			class="sr-only">Toggle navigation</span>
		</a> <!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="<c:url value="/resources/dist/img/user2-160x160.jpg"/>"
						class="user-image" alt="User Image"> <span class="hidden-xs">Alexander
							Pierce</span>
				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header"><img
							src="<c:url value="/resources/dist/img/user2-160x160.jpg"/>"
							class="img-circle" alt="User Image">
							<p>
								Alexander Pierce - Web Developer <small>Member since
									Nov. 2012</small>
							</p></li>
						<!-- Menu Body -->
						<li class="user-body">
							<div class="col-xs-4 text-center">
								<a href="#">Followers</a>
							</div>
							<div class="col-xs-4 text-center">
								<a href="#">Sales</a>
							</div>
							<div class="col-xs-4 text-center">
								<a href="#">Friends</a>
							</div>
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="#" class="btn btn-default btn-flat">Profile</a>
							</div>
							<div class="pull-right">
								<a href="#" class="btn btn-default btn-flat">Sign out</a>
							</div>
						</li>
					</ul></li>
			</ul>
		</div>
		</nav> </header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar"> <!-- Sidebar Menu -->
		<ul class="sidebar-menu">
			<li class="header">Main Navigation</li>
			<!-- Optionally, you can add icons to the links -->
			<li><a href="<c:url value="/supplier"/>"> <i
					class="fa fa-users"></i> <span>Manage Supplier</span>
			</a></li>
			<li><a href="<c:url value="/product"/>"> <i
					class="fa fa-barcode"></i> <span>Manage Product</span>
			</a></li>
			<li class="active"><a href="<c:url value="/purchase"/>"> <i
					class="fa fa-opencart"></i> <span>Purchase</span>
			</a></li>
			<li>
				<a href="<c:url value="/sales"/>"> <i
					class="fa fa-opencart"></i> <span>Sales</span>
				</a>
			</li>
		</ul>
		<!-- /.sidebar-menu --> </section> <!-- /.sidebar --> </aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>Purchase Information</h1>
			</section>

			<!-- Main content -->
			<section class="invoice">
				<!-- title row -->
				<div class="row">
		            <div class="col-xs-12">
		              <h2 class="page-header">
		                <i class="fa fa-globe"></i> Kupalatu Store.
		                <small class="pull-right">Date: ${purchased.purchaseDate }</small>
		              </h2>
		            </div><!-- /.col -->
	         	 </div>
	         	 <!-- info row -->
		         <div class="row invoice-info">
		         	<div class="col-sm-8 invoice-col">
			           	From
			           	<address>
			          	<strong>${supplier.name }.</strong><br>
			                ${supplier.supplierAddress.line1}<br>
			                ${supplier.supplierAddress.city },${supplier.supplierAddress.state },Post: ${supplier.supplierAddress.postCode }<br>
			                Phone:  ${supplier.contact }<br>
			                Email: ${supplier.email }
			           	</address>
		            </div><!-- /.col -->
		            <div class="col-sm-4 invoice-col">
		              <b>Purchase Number #${purchased.purchaseNo }</b><br>
		            </div><!-- /.col -->
		          </div><!-- /.row -->
		          <!-- Table row -->
          		<div class="row">
          			<div class="col-xs-12 table-responsive">
          				<table class="table table-striped">
			          		<thead>
			                  <tr>
			                    <th>Product</th>
			                    <th>Unit</th>
			                    <th>Qty</th>
			                    <th>Price</th>
			                    <th>Subtotal</th>
			                  </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${purchased.productLineInfos}" var="productLine">
			                	<tr>
					         		<td>${productLine.product.name }</td>
					               	<td>${productLine.product.unit }</td>
					            	<td>${productLine.quantity }</td>
					             	<td>${productLine.purchasePrice }</td>
					            	<td>${productLine.totalItem }</td>
				           		</tr>
			                	</c:forEach>							   	
			                </tbody>
          				</table>
          			</div>
          		</div><!-- /.row -->
          		<div class="row">
          			<div class="col-xs-6">
          				<p class="lead">Details:</p>
          				<p class="text-muted well well-sm no-shadow" style="margin-top: 10px;">
                			${purchased.details }
           				</p>
          			</div><!-- /.col -->
          			<div class="col-xs-6">
          				<p class="lead">Purchase Payment</p>
          				<div class="table-responsive">
          					<table class="table">
          						<tr>
                 					<th style="width:50%">Total:</th>
                 					<td>${purchased.totalPurchased}</td>
                				</tr>
          					</table>
          				</div>
          			</div><!-- /.col -->
          		</div><!-- /.row -->
          		<div class="row">
          			<div class="col-xs-12">          				
          				<c:url value="/purchase/confirmed" var="confirmed"/>
          				<form action="${confirmed }" method="GET">
          					<button class="btn btn-success pull-right"><i class="fa fa-credit-card"></i> Submit Payment</button>
          					<!-- <button class="btn btn-primary pull-right" style="margin-right: 5px;"><i class="fa fa-download"></i> Generate PDF</button> -->
          				</form>           				
          			</div>
          		</div><!-- /.row -->
			</section><!-- /.content -->
			<div class="clearfix"></div>
		</div><!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer"> <!-- To the right -->
		<div class="pull-right hidden-xs">Anything you want</div>
		<!-- Default to the left --> <strong>Copyright &copy; 2015 <a
			href="#">Company</a>.
		</strong> All rights reserved. </footer>

		<!-- Control Sidebar -->
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 2.1.4 -->
	<script src="<c:url value="/resources/jquery/2.1.4/jquery.min.js"/>"></script>
	<!-- Bootstrap 3.3.5 -->
	<script
		src="<c:url value="/resources/bootstrap/3.3.5/js/bootstrap.min.js"/>"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value="/resources/dist/js/app.min.js"/>"></script>
	<!-- DataTables -->
	<script
		src="<c:url value="/resources/plugins/datatables/jquery.dataTables.min.js"/>"></script>
	<script
		src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js"/>"></script>
	<!-- Select2 -->
	<script
		src="<c:url value="/resources/plugins/select2/select2.full.min.js"/>"></script>
	<!-- InputMask -->
	<script
		src="<c:url value="/resources/plugins/input-mask/jquery.inputmask.js"/>"></script>
	<script
		src="<c:url value="/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"/>"></script>
	<script
		src="<c:url value="/resources/plugins/input-mask/jquery.inputmask.extensions.js"/>"></script>
	<script>
		$(function() {
			//Datemask dd/mm/yyyy
			$("#purchaseDate").inputmask("dd/mm/yyyy", {
				"placeholder" : "dd/mm/yyyy"
			});
		});
	</script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
</body>
</html>