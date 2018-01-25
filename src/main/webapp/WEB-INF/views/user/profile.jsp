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
          		<h1>User Profile</h1>
        	</section>

			<!-- Main content -->
			<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Details</h3>							
		                </div><!-- /.box-header -->
		                <c:url var="action" value="/profile"/>
		                <form:form method="POST" commandName="userProfile" action="${action}" cssClass="form-horizontal">
		                <c:if test="${updated==true }">
		                	<div class="box-body">
			                <div class="col-xs-12">
			                	<div class="callout callout-success">
			                		<h4>Password has successfully changed</h4>
			                	</div>
			                </div>
			                </div>
						</c:if>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		                <div class="box-body">
		                	<spring:bind path="username">		                
		                	<div class="form-group ${status.error  ? 'has-error' : ''}">
		                		<form:label path="username" cssClass="col-sm-2 control-label">Username</form:label>	                	
			                	<!-- <label class="col-sm-2 control-label">Username</label> -->
			                	<div class="col-sm-10">
			                		<form:input path="username" cssClass="form-control" placeholder="Username" disabled="true"/>
									<form:errors path="username" class="control-label" />
			                	</div>
		                	</div>
		                	</spring:bind>
		                	<spring:bind path="password">
		                	<div class="form-group ${status.error  ? 'has-error' : ''}">
			                	<form:label path="password" cssClass="col-sm-2 control-label">Password</form:label>
			                	<div class="col-sm-10">
			                		<form:password path="password" cssClass="form-control" placeholder="Password"/>
									<form:errors path="password" class="control-label" />
			                	</div>
		                	</div>
		                	</spring:bind>
		                	<spring:bind path="confirmPassword">
		                	<div class="form-group ${status.error  ? 'has-error' : ''}">
			                	<form:label path="confirmPassword" cssClass="col-sm-2 control-label">Confirm Password</form:label>
			                	<div class="col-sm-10">
			                		<form:password path="confirmPassword" cssClass="form-control" placeholder="Confirm Password"/>
									<form:errors path="confirmPassword" class="control-label" />
			                	</div>
		                	</div>
		                	</spring:bind>
		                 </div> 
		                 <div class="box-footer clearfix">
		                	<a href="<c:url value="/profile"/>" class="btn btn-sm btn-danger btn-flat pull-left">Cancel</a> 
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
      /* $(function () {        
        $('#product').DataTable({
		"sDom": '<"row view-filter"<"col-sm-12"<"pull-left"i><"pull-right"><"clearfix">>>t<"row view-pager"<"col-sm-12"<"text-center"p>>>',
          "paging": true,
          "lengthChange": false,
          "searching": false,
          "ordering": false,
          "autoWidth": false
        });
      }); */
    </script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
</body>
</html>