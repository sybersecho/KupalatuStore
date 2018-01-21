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
			<h1>Purchase Information</h1>
			</section>

			<!-- Main content -->
			<section class="invoice">
				<!-- title row -->
				<div class="row">
		            <div class="col-xs-12">
		              <h2 class="page-header">
		                <i class="fa fa-globe"></i> Kupalatu Store.
		                <small class="pull-right">Date: <fmt:formatDate value="${purchased.purchaseDate }" pattern="dd/MM/yyyy"/> </small>
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