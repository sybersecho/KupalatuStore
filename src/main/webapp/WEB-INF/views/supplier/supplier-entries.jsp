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

      <jsp:include page="../common/header.jsp" />
		
		<jsp:include page="../common/sidebar.jsp" />

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

      <jsp:include page="../common/footer.jsp" />

      <!-- Control Sidebar -->
      
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
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
