<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<link rel="stylesheet" href="<c:url value="/resources/dist/css/AdminLTE.min.css"/>">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
<link rel="stylesheet" href="<c:url value="/resources/dist/css/skins/skin-blue.min.css"/>">

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
      <header class="main-header">

        <!-- Logo -->
        <a href="index2.html" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>A</b>LT</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>Admin</b>LTE</span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<c:url value="/resources/dist/img/user2-160x160.jpg"/>" class="user-image" alt="User Image">
                  <span class="hidden-xs">Alexander Pierce</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="<c:url value="/resources/dist/img/user2-160x160.jpg"/>" class="img-circle" alt="User Image">
                    <p>
                      Alexander Pierce - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
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
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

          <!-- Sidebar Menu -->
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
          </ul><!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Manage Suppliers
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box box-primary">
				<div class="box-header with-border">
				  <h3 class="box-title">Supplier Entry</h3>
				  <!-- tools box -->
                  <div class="pull-right box-tools">
                    <a href="<c:url value="/supplier/add" />" class="btn btn-info btn-sm btn-flat"  title="Add New Supplier">Add New Supplier</a>
                  </div><!-- /. tools -->
				</div><!-- /.box-header -->
				
                <div class="box-body">
                  <table id="supplier" class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Contact</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="supplier" items="${suppliers}">
                    <tr>
                    	<td>${supplier.code}</td>
                    	<td>${supplier.name}</td>
                    	<td>${supplier.contact}</td>
                    	<td>${supplier.email}</td>
                    	<td>${supplier.supplierAddress.line1}</td>
                    	<td align="center">
                              <a href="<c:url value="/supplier/${supplier.id}"/>" class="btn btn-warning"><i class="fa fa-pencil"></i></a>
                              <a class="btn btn-danger" data-toggle="modal" data-target="#remove_${supplier.id}" data-original-title><i class="fa fa-trash"></i></a>
                         </td>
                         <div class="modal fade" id="remove_${supplier.id}" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
				                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				                    <h4 class="modal-title">Confirm Delete</h4>
				                  </div>
								  <div class="modal-body">
				                    <p>Are you sure you want to delete this supplier? </p>
				                  </div>
								  <div class="modal-footer">
				                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
				                    <c:url value="/supplier/delete/${supplier.id}" var="confirmDelete"/>
				                    <form:form method="POST" action="${confirmDelete }">
				                    
				                    </form:form>
				                    <c:url value="/delete/${supplier.id}" var="delete"/>
				                    <a href="${confirmDelete }" class="btn btn-primary">Confirm</a>
				                  </div>
								</div>
							</div>
						  </div>
                    </tr>
                    </c:forEach>                  
                    </tbody>
                    <tfoot>
                      <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Contact</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th></th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
				
              </div><!-- /.box -->
            </div><!-- /.col -->
          </div><!-- /.row -->

        </section><!-- /.content -->
      
	  </div><!-- /.content-wrapper -->

      <!-- Main Footer -->
      <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
          Anything you want
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2015 <a href="#">Company</a>.</strong> All rights reserved.
      </footer>

      <!-- Control Sidebar -->
      <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
          <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
          <!-- Home tab content -->
          <div class="tab-pane active" id="control-sidebar-home-tab">
            <h3 class="control-sidebar-heading">Recent Activity</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-birthday-cake bg-red"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>
                    <p>Will be 23 on April 24th</p>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

            <h3 class="control-sidebar-heading">Tasks Progress</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Custom Template Design
                    <span class="label label-danger pull-right">70%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

          </div><!-- /.tab-pane -->
          <!-- Stats tab content -->
          <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div><!-- /.tab-pane -->
          <!-- Settings tab content -->
          <div class="tab-pane" id="control-sidebar-settings-tab">
            <form method="post">
              <h3 class="control-sidebar-heading">General Settings</h3>
              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Report panel usage
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  Some information about this general settings option
                </p>
              </div><!-- /.form-group -->
            </form>
          </div><!-- /.tab-pane -->
        </div>
      </aside><!-- /.control-sidebar -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>
    </div><!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 2.1.4 -->
    <script src="<c:url value="/resources/jquery/2.1.4/jquery.min.js"/>"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<c:url value="/resources/bootstrap/3.3.5/js/bootstrap.min.js"/>"></script>
    <!-- AdminLTE App -->
    <script src="<c:url value="/resources/dist/js/app.min.js"/>"></script>
	<!-- DataTables -->
    <script src="<c:url value="/resources/plugins/datatables/jquery.dataTables.min.js"/>"></script>
    <script src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js"/>"></script>
	<script>
      $(function () {        
        $('#supplier').DataTable({
			"dom": '<<"pull-left"i><"pull-right"f>>t<"bottom pull-left"p>',
			"ordering": false,			
        });
      });
    </script>

    <!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
  </body>
</html>
