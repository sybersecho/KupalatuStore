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
								<a href="<c:url value="/logout"/>" class="btn btn-default btn-flat">Sign out</a>
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
			<li><a href="<c:url value="/purchase"/>"> <i
					class="fa fa-opencart"></i> <span>Purchase</span>
			</a></li>
			<li class="active"><a href="<c:url value="/sales"/>"> <i
					class="fa fa-opencart"></i> <span>Sales</span>
			</a></li>
		</ul>
		<!-- /.sidebar-menu --> </section> <!-- /.sidebar --> </aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>Sales Information</h1>
			</section>

			<!-- Main content -->
			<section class="content">
			<div class="row">
				<div class="col-md-12">
					
					<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">Product Detail</h3>
						</div><!-- /.box-header -->
						<c:url var="action" value="/sales"/>
						<form:form method="POST" commandName="sales" action="${action}" cssClass="form-horizontal">
						<c:if test="${saleSaved==true }">
		                	<div class="box-body">
			                <div class="col-xs-12">
			                	<div class="callout callout-success">
			                		<h4>Successfully save a sale</h4>
			                	</div>
			                </div>
			                </div>
						</c:if>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<div class="col-xs-12 col-md-6">
						<div class="box-body">
							<spring:bind path="productLines">
	                    	<div class="form-group ${status.error  ? 'has-error' : ''}">
	                    		<%-- <form:label path="purchaseNo" cssClass="col-sm-2 control-label">No Purchase</form:label>
                      			<div class="col-sm-10"> --%>
                        			<%-- <form:input path="purchaseNo" cssClass="form-control" placeholder="No Purchase"/> --%>
                      				<form:errors path="productLines" class="control-label" />
	                      		<!-- </div> -->
	                    	</div>
	                    	</spring:bind>
							<spring:bind path="no">
							<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="no" cssClass="col-sm-2 control-label">No</form:label>
								<!-- <label for="No" class="col-sm-2 control-label">No</label> -->
								<div class="col-sm-10">
                        			<form:input path="no" cssClass="form-control" placeholder="No"/>
                      				<form:errors path="no" class="control-label" />
								</div>
							</div>
							</spring:bind>
							<spring:bind path="salesDate">
							<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="salesDate" cssClass="col-sm-2 control-label">Date</form:label>
								<!-- <label for="Date" class="col-sm-2 control-label">Date</label> -->
								<div class="col-sm-10">
                        			<form:input path="salesDate" cssClass="form-control" placeholder="Date"/>
                      				<form:errors path="salesDate" class="control-label" />
									<!-- <input type="text" class="form-control" id="salesDate" placeholder="Date"> -->
								</div>
							</div>
							</spring:bind>
						</div><!-- /.box-body -->
						</div>
						<div class="col-xs-12 col-md-6">						
						<div class="box-body">												
							<spring:bind path="productBarcode">
		                	<div class="form-group ${status.error  ? 'has-error' : ''}">
		                		<form:label path="productBarcode" cssClass="col-sm-2 control-label">Barcode</form:label>
		                		<div class="col-sm-8">
		                			<form:input path="productBarcode" cssClass="form-control" placeholder="Barcode" />
									<form:errors path="productBarcode" class="control-label" />
								</div>
								<div class="col-sm-2">
									<div class="pull-right">
									<button class="btn btn-primary" type="submit" id="action" name="action" value="search">
										<i class="fa fa-search"></i> Search
									</button>
									</div>
								</div>
		                	</div>
		                	</spring:bind>
		                	<spring:bind path="productName">
							<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="productName" cssClass="col-sm-2 control-label">Name</form:label>
								<div class="col-sm-10">
									<form:input path="productName" cssClass="form-control" placeholder="Name" />
									<form:errors path="productName" class="control-label" />
								</div>
							</div>
							</spring:bind>							
							
							<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="salesPrice" cssClass="col-sm-2 control-label">Price</form:label>								
								<div class="col-sm-4">
									<form:input path="salesPrice" cssClass="form-control" placeholder="Price" disabled="true"/>									
								</div>
								<spring:bind path="quantity">
								<form:label path="quantity" cssClass="col-sm-2 control-label">Quantity</form:label>								
								<div class="col-sm-4">
									<form:input path="quantity" cssClass="form-control" placeholder="Quantity"/>
									<form:errors path="quantity" class="control-label" />
								</div>
								</spring:bind>
							</div>
							<div class="pull-right">
							<button class="btn btn-primary btn-sm" type="submit" id="action" name="action" value="add">
										<i class="fa fa-plus-square"></i> Add
									</button>
							</div>
						</div><!-- /.box-body -->
						</div>
						<div class="box-body table-responsive">	
						<%-- <form:hidden path="productLines"/> --%>
						<table id="product" class="table table-striped">
							<thead>
								<tr>
									<th>Name</th>
									<th>Quantity</th>
									<th>Price</th>
									<th>Sub Total</th>
									<th style="width: 100px"></th>
								</tr>
							</thead>
							<tbody>								
								<c:forEach var="line" items="${sales.productLines}" varStatus="index">
								<tr>
									<td>${line.product.name }</td>
									<td>${line.quantity }</td>
									<td>${line.product.salesPrice }</td>
									<td>${line.subTotal }</td>
									
									<td>
										<div class="pull-right">
											<a href='<c:url value="/sales/select/line/${index.count }"/>' class="btn btn-primary btn-sm"><i class="fa fa-edit"></i></a>
											<a href='<c:url value="/sales/delete/line/${index.count }"/>' class="btn btn-primary btn-sm"><i class="fa fa-trash"></i></a>
										</div>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						
						</div><!-- /.box-body -->
						<div class="box-body">
							<div class="col-xs-6 col-xs-offset-6">
								<p class="lead">Sales Payment</p>
								<div class="table-responsive">
	          					<table class="table">
	          						<tr>
	                 					<th style="width:50%">Total:</th>
	                 					<td>${sales.totalSales }</td>
	                				</tr>
	          					</table>
	          				</div>
							</div>
						</div><!-- /.box-body -->
						<div class="box-body">
							<div class="col-xs-12">          				
          				<%-- <c:url value="/purchase/submit" var="submit"/>
          				<form action="${submit }" method="post"> --%>
          					<button class="btn btn-success pull-right" type="submit" id="action" name="action" value="submit"><i class="fa fa-credit-card"></i> Submit Payment</button>
          				<%-- </form>  --%>          				
          			</div>
						</div>
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
			$("#salesDate").inputmask("dd/mm/yyyy", {
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