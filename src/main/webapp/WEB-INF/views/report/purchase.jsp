<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

		<jsp:include page="../common/header.jsp" />
		
		<jsp:include page="../common/sidebar.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>Purchase Report</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
				<div class="col-xs-12">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Total Purchased Report</h3>
						</div><!-- /.box-header -->
						<c:url var="action" value="/report/purchase"/>
						<form:form method="POST" commandName="report" action="${action }" cssClass="form-horizontal">						
						<div class="box-body">
						<div class="col-md-6">
							<spring:bind path="strFromDate">
							<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="strFromDate" cssClass="col-sm-2 control-label">From Date</form:label>
								<div class="col-sm-10">
									<form:input path="strFromDate" cssClass="form-control" placeholder="From Date"/>
                      				<form:errors path="strFromDate" class="control-label" />
								</div>
							</div>
							</spring:bind>
						</div>
						<div class="col-md-6">
							<spring:bind path="strToDate">
							<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="strToDate" cssClass="col-sm-2 control-label">To Date</form:label>
								<div class="col-sm-10">
									<form:input path="strToDate" cssClass="form-control" placeholder="To Date"/>
                      				<form:errors path="strToDate" class="control-label" />
								</div>
							</div>
							</spring:bind>					
						</div>
						</div>
						<div class="box-footer clearfix">
							<button class="btn btn-sm btn-danger btn-flat pull-left" type="submit" id="action" name="action" value="clear">Clear</button>
							<button class="btn btn-sm btn-primary btn-flat pull-right" type="submit" id="action" name="action" value="search">Search</button>
						</div>
						
						<div class="box-body table-responsive">	
							<table id="purchase" class="table table-striped">
								<thead>
									<tr>
										<th>No</th>
										<th>Product Name</th>
										<th>Quantity</th>
										<th>Purchase Price</th>
										<th>Total Cost</th>									
									</tr>
								</thead>
								<tbody>
									<c:forEach var="purchase" items="${report.purchased}" varStatus="index">
										<c:forEach var="line" items="${purchase.productLineInfos }"> 
											<tr>
												<td>${purchase.purchaseNo }</td>
												<td>${line.product.name }</td>
												<td>${line.quantity }</td>
												<td>${line.purchasePrice }</td>
												<td>${line.totalItem }</td>
											</tr>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="box-body">
							<div class="col-xs-12 col-md-6 col-md-offset-6">
								<p class="lead">Sales Payment</p>
								<div class="table-responsive">
	          					<table class="table">
	          						<thead>
	          							<tr>
	          								<th>Product Count</th>
	          								<th>Purchase Qty</th>
	          								<th>Total Purchase</th>
	          							</tr>
	          						</thead>
	          						<tbody>
	          							<tr>
	          								<td>${report.productCount }</td>
	          								<td>${report.purchaseQty }</td>
	          								<td>${report.totalPurchase }</td>
	          							</tr>
	          						</tbody>
	          					</table>								
								</div>
							</div>
						</div>
						</form:form>
					</div><!-- /.box -->		
				</div>		
				</div>		
			</section><!-- /.content -->
			<div class="clearfix"></div>
		</div><!-- /.content-wrapper -->

		<jsp:include page="../common/footer.jsp" />

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
			$("#strFromDate").inputmask("dd/mm/yyyy", {
				"placeholder" : "dd/mm/yyyy"
			});
			$("#strToDate").inputmask("dd/mm/yyyy", {
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