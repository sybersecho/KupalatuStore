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
<link rel="stylesheet" href="<c:url value="/resources/bootstrap/3.3.5/css/bootstrap.min.css"/>">
<!-- Font Awesome -->
<link rel="stylesheet"	href="<c:url value="/resources/font-awesome/4.4.0/css/font-awesome.min.css"/>">
<!-- Ionicons -->
<link rel="stylesheet"	href="<c:url value="/resources/ionicons/2.0.1/css/ionicons.min.css"/>">
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
			<li class="active">
				<a href="<c:url value="/supplier"/>"> 
					<i class="fa fa-users"></i> <span>Manage Supplier</span>
				</a>
			</li>
			<li>
				<a href="<c:url value="/product"/>"> 
					<i class="fa fa-barcode"></i><!--  fa-archive -->
					<span>Manage Product</span>
				</a>
			</li>
			<li>
				<a href="<c:url value="/purchase"/>"> 
					<i class="fa fa-opencart"></i> <span>Purchase</span>
				</a>
			</li>
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
			<h1>Manage Suppliers</h1>
			</section>

			<!-- Main content -->
			<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Supplier Information</h3>
						</div>
						<!-- /.box-header -->

						<c:url var="action" value="${actionUrl}"/>
						<form:form method="POST" commandName="supplier" action="${action}" cssClass="form-horizontal">
							<form:hidden path="id"/>
							<div class="box-body">
								<spring:bind path="code">
									<div class="form-group ${status.error  ? 'has-error' : ''}">
										<form:label path="code" cssClass="col-sm-2 control-label">Code</form:label>
										<div class="col-sm-10">
											<form:input path="code" cssClass="form-control" placeholder="Code"/>
											<form:errors path="code" class="control-label" />
										</div>
									</div>
								</spring:bind>
								<spring:bind path="name">
									<div class="form-group ${status.error  ? 'has-error' : ''}">
										<form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
										<div class="col-sm-10">
											<form:input path="name" cssClass="form-control" placeholder="Name"/>
											<form:errors path="name" class="control-label" />
										</div>
									</div>
								</spring:bind>
								<spring:bind path="contact">
									<div class="form-group ${status.error  ? 'has-error' : ''}">
										<form:label path="contact" cssClass="col-sm-2 control-label">Contact</form:label>
										<div class="col-sm-10">
											<form:input path="contact" cssClass="form-control" placeholder="Contact"/>
											<form:errors path="contact" class="control-label" />
										</div>
									</div>
								</spring:bind>
								<spring:bind path="email">
									<div class="form-group ${status.error  ? 'has-error' : ''}">
										<form:label path="email" cssClass="col-sm-2 control-label">Email</form:label>
										<div class="col-sm-10">
											<form:input path="email" cssClass="form-control" placeholder="Email"/>
											<form:errors path="email" class="control-label" />
										</div>
									</div>
								</spring:bind>

								<legend>Address Details</legend>
								<spring:bind path="supplierAddress.line1">
									<div class="form-group ${status.error  ? 'has-error' : ''}">
										<form:label path="supplierAddress.line1" cssClass="col-sm-2 control-label">Line 1</form:label>									
										<div class="col-sm-10">
											<form:input path="supplierAddress.line1" cssClass="form-control" placeholder="Address Line 1"/>
											<form:errors path="supplierAddress.line1" class="control-label" />
										</div>
									</div>
								</spring:bind>
								<div class="form-group">
									<form:label path="supplierAddress.line2" cssClass="col-sm-2 control-label">Line 2</form:label>
									<div class="col-sm-10">
										<form:input path="supplierAddress.line2" cssClass="form-control" placeholder="Address Line 2"/>
									</div>
								</div>
								<div class="form-group">
									<form:label path="supplierAddress.city" cssClass="col-sm-2 control-label">City</form:label>
									<div class="col-sm-10">
										<form:input path="supplierAddress.city" cssClass="form-control" placeholder="City"/>
									</div>
								</div>
								<spring:bind path="supplierAddress.postCode">
								<div class="form-group">
									<form:label path="supplierAddress.state" cssClass="col-sm-2 control-label">State</form:label>
									<div class="col-sm-4">
										<form:input path="supplierAddress.state" cssClass="form-control" placeholder="State"/>
									</div>
									<div class="${status.error  ? 'has-error' : ''}">
									<form:label path="supplierAddress.postCode" cssClass="col-sm-2 control-label ">Post Code</form:label>									
									<div class="col-sm-4 ">
										<form:input path="supplierAddress.postCode" cssClass="form-control" placeholder="Post Code"/>
										<form:errors path="supplierAddress.postCode" class="control-label" />
									</div>		
									</div>						
								</div>
								</spring:bind>	
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix">
								<a href="<c:url value="/supplier"/>" class="btn btn-sm btn-danger btn-flat pull-left">Cancel</a> 
								<button class="btn btn-sm btn-primary btn-flat pull-right" type="submit" id="submit" value="submit" >Submit</button>
							</div>
							<!-- /.box-footer -->
						</form:form>
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row --> </section>
			<!-- /.content -->

		</div>
		<!-- /.content-wrapper -->

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
    <script src="<c:url value="/resources/bootstrap/3.3.5/js/bootstrap.min.js"/>"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value="/resources/dist/js/app.min.js"/>"></script>
	<!-- DataTables -->
	<script
		src="<c:url value="/resources/plugins/datatables/jquery.dataTables.min.js"/>"></script>
	<script
		src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js"/>"></script>

	<!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
</body>
</html>