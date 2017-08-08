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
		<header class="main-header"> <!-- Logo --> 
			<a
			href="index2.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>A</b>LT</span> <!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>Admin</b>LTE</span>
		</a> <!-- Header Navbar --> <nav class="navbar navbar-static-top"
			role="navigation"> <!-- Sidebar toggle button--> <a
			href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a> <!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="<c:url value="/resources/dist/img/user2-160x160.jpg"/>"
						class="user-image" alt="User Image"> <span
						class="hidden-xs">Alexander Pierce</span>
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
								<a href="#" class="btn btn-default btn-flat">Sign
									out</a>
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
			<li>
				<a href="<c:url value="/supplier"/>"> 
					<i class="fa fa-users"></i> 
					<span>Manage Supplier</span>
				</a>
			</li>
			<li class="active">
				<a href="<c:url value="/product"/>"> 
					<i class="fa fa-barcode"></i><!--  fa-archive -->
					<span>Manage Product</span>
				</a>
			</li>
		</ul> <!-- /.sidebar-menu --> 
		</section> <!-- /.sidebar --> 
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
          		<h1>Manage Product</h1>
        	</section>

			<!-- Main content -->
			<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">Product Entries</h3>
							<!-- tools box -->
			               	<div class="pull-right box-tools">
			              		<a href="<c:url value="/product/add" />" class="btn btn-info btn-sm btn-flat"  title="Add New Product">Add New Product</a>
			               	</div><!-- /. tools -->
		                </div><!-- /.box-header -->
		                <div class="box-body">
		                	<c:if test="${alert }">
		                		<div class="alert alert-success alert-dismissable">
			                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			                    <!-- Success save a product. -->
			                    ${alertMessage }
		                  	</div>
		                	</c:if>
		                	
			                <c:url var="action" value="${actionUrl}"/>
							<form:form method="POST" commandName="criteria" action="${action}" cssClass="form-horizontal">
			                	<div class="form-group">
			                		<form:label path="barcode" cssClass="col-sm-2 control-label">Barcode</form:label>
									<div class="col-sm-10">
										<form:input path="barcode" cssClass="form-control" placeholder="Barcode"/>
	                      			</div>
	                    		</div>
	                    		<div class="form-group">
	                    			<form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                      				<div class="col-sm-10">
                      					<form:input path="name" cssClass="form-control" placeholder="Name"/>
	                      			</div>
	                    		</div>
	                    		<c:url value="/product" var="clear" />
	                    		<a href="${clear }" class="btn btn-default btn-flat">Clear</a>
                    			<button type="submit" class="btn btn-primary btn-flat pull-right">Search</button>
			                </form:form>
		                </div><!-- /.box-body -->
		                <div class="box-body">
		                	<table id="product" class="table table-striped">
                    			<thead>
                      			<tr>
								  <th>#</th>
								  <th>Barcode</th>
								  <th>Name</th>
								  <th>Action</th>
                      			</tr>
                    			</thead>
                    			<tbody>
                    				<c:forEach var="product" items="${products }" varStatus="index">
                    				<tr>
                    					<td>${index.count }</td>
				                      	<td>${product.barcode }</td>
				                      	<td>${product.name }</td>
				                      	<td>
				                              <a href="<c:url value="/product/${product.id}"/>" class="btn btn-warning"><i class="fa fa-pencil"></i></a>
				                              <a class="btn btn-danger" data-toggle="modal" data-target="#remove_${product.id }" data-original-title><i class="fa fa-trash"></i></a>
				                         </td>
				                         <div class="modal fade" id="remove_${product.id}" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
								                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								                    <h4 class="modal-title">Confirm Delete</h4>
								                  </div>
												  <div class="modal-body">
								                    <p>Are you sure you want to delete this product? </p>
								                  </div>
												  <div class="modal-footer">
								                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
								                    <c:url value="/product/delete/${product.id}" var="confirmDelete"/>
								                    <form:form method="POST" action="${confirmDelete }">
								                    
								                    </form:form>
								                    <c:url value="/delete/${product.id}" var="delete"/>
								                    <a href="${confirmDelete }" class="btn btn-primary">Confirm</a>
								                  </div>
												</div>
											</div>
										  </div>
                    				</tr>
				                    </c:forEach>
                    			</tbody>
                    		</table>
		                </div><!-- /.box-body -->
					</div><!-- /.box -->
				</div><!-- /.col -->
			</div><!-- /.row --> 
			</section><!-- /.content -->

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
	<script>
      $(function () {        
        $('#product').DataTable({
		"sDom": '<"row view-filter"<"col-sm-12"<"pull-left"i><"pull-right"><"clearfix">>>t<"row view-pager"<"col-sm-12"<"text-center"p>>>',
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": false,
          "autoWidth": false
        });
      });
    </script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
</body>
</html>