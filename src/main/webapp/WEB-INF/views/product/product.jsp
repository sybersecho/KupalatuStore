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

		<jsp:include page="../common/header.jsp" />
		
		<jsp:include page="../common/sidebar.jsp" />

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
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Product Information</h3>							
		                </div><!-- /.box-header -->
		                <c:url var="action" value="${actionUrl}"/>
		                <form:form method="POST" commandName="product" action="${action}" cssClass="form-horizontal">
		                <div class="box-body">
		                	<form:hidden path="id"/>
		                	<spring:bind path="barcode">
		                	<div class="form-group ${status.error  ? 'has-error' : ''}">
		                		<form:label path="barcode" cssClass="col-sm-2 control-label">Barcode</form:label>
								<div class="col-sm-10">
									<form:input path="barcode" cssClass="form-control" placeholder="Barcode"/>
									<form:errors path="barcode" class="control-label" />
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
	                    	<spring:bind path="unit">
	                    	<div class="form-group ${status.error  ? 'has-error' : ''}">
	                    		<form:label path="unit" cssClass="col-sm-2 control-label">Unit</form:label>
                      			<div class="col-sm-10">
                        			<form:input path="unit" cssClass="form-control" placeholder="Unit"/>
                      				<form:errors path="unit" class="control-label" />
	                      		</div>
	                    	</div>
	                    	</spring:bind>
	                    	<spring:bind path="description">
	                    	<div class="form-group ${status.error  ? 'has-error' : ''}">
	                    		<form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
                      			<div class="col-sm-10">                      				
                      				<form:textarea path="description" rows="3" cssClass="form-control" placeholder="Description ..."/>
                      				<form:errors path="description" class="control-label" />
                   			 	</div>
                   			 </div>
	                    	</spring:bind>
		                </div><!-- /.box-body -->
		                <div class="box-footer clearfix">
		                	<a href="<c:url value="/product"/>" class="btn btn-sm btn-danger btn-flat pull-left">Cancel</a> 
							<button class="btn btn-sm btn-primary btn-flat pull-right" type="submit" id="submit" value="submit" >Submit</button>
						</div><!-- /.box-footer -->	
						</form:form>	                
					</div><!-- /.box -->
				</div><!-- /.col -->
			</div><!-- /.row --> 
			</section><!-- /.content -->
		</div><!-- /.content-wrapper -->

		<<jsp:include page="../common/footer.jsp" />

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