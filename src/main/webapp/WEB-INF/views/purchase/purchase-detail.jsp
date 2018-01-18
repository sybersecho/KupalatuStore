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
<link rel="stylesheet" href="<c:url value="/resources/plugins/timepicker/bootstrap-timepicker.min.css"/>">
<!-- Select2 -->
<link rel="stylesheet" href="<c:url value="/resources/plugins/select2/select2.min.css"/>">
<!-- DatePicer -->
<link rel="stylesheet" href="<c:url value="/resources/plugins/datepicker/datepicker3.css"/>">
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
			<section class="content">
			<div class="row">
				<div class="col-xs-12">
				
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Purchase Detail</h3>							
		                </div><!-- /.box-header -->
		                <c:url var="action" value="/purchase/"/>
		                <form:form method="POST" commandName="purchased" action="${action}" cssClass="form-horizontal">
		                <c:if test="${purchaseSaved==true }">
		                	<div class="box-body">
			                <div class="col-xs-12">
			                	<div class="callout callout-success">
			                		<h4>Successfully save a purchase</h4>
			                	</div>
			                </div>
			                </div>
						</c:if>
		                <div class="col-xs-12 col-md-6">
		                <div class="box-body">
		                	<form:hidden path="totalPurchased"/>
		                	<form:hidden path="index"/>
		                	<form:hidden path="edit"/>
		                	<spring:bind path="productLineInfos">
	                    	<div class="form-group ${status.error  ? 'has-error' : ''}">
	                    		<%-- <form:label path="purchaseNo" cssClass="col-sm-2 control-label">No Purchase</form:label>
                      			<div class="col-sm-10"> --%>
                        			<%-- <form:input path="purchaseNo" cssClass="form-control" placeholder="No Purchase"/> --%>
                      				<form:errors path="productLineInfos" class="control-label" />
	                      		<!-- </div> -->
	                    	</div>
	                    	</spring:bind>
		                	<spring:bind path="purchaseNo">
	                    	<div class="form-group ${status.error  ? 'has-error' : ''}">
	                    		<form:label path="purchaseNo" cssClass="col-sm-2 control-label">No Purchase</form:label>
                      			<div class="col-sm-10">
                        			<form:input path="purchaseNo" cssClass="form-control" placeholder="No Purchase"/>
                      				<form:errors path="purchaseNo" class="control-label" />
	                      		</div>
	                    	</div>
	                    	</spring:bind>
	                    	<spring:bind path="purchaseDate">
	                    	<div class="form-group ${status.error  ? 'has-error' : ''}">
	                    		<form:label path="purchaseDate" cssClass="col-sm-2 control-label">Date</form:label>
                      			<div class="col-sm-10">
                        			<form:input path="purchaseDate" cssClass="form-control" />
                        			<form:errors path="purchaseDate" class="control-label" />
	                      		</div>
	                    	</div>
	                    	</spring:bind>
	                    	<spring:bind path="supplier">
	                    	<div class="form-group ${status.error  ? 'has-error' : ''}">
	                    		<form:label path="supplier" cssClass="col-sm-2 control-label">Supplier</form:label>
                      			<div class="col-sm-10">
                        			<form:select path="supplier" cssClass="form-control select2" style="width: 100%;" items="${suppliers }"  itemValue="id"  itemLabel="name">
                        				<%-- <form:options items="${suppliers }" itemValue="id"  itemLabel="name" /> --%>
                        				<%-- <c:forEach items="${suppliers }" var="s">
                        					<form:option value="${s.id }"/>
                        				</c:forEach> --%>
                        			</form:select>
	                      		</div>
	                    	</div>
	                    	</spring:bind>
	                    	<spring:bind path="details">
	                    	<div class="form-group ${status.error  ? 'has-error' : ''}">
	                    		<form:label path="details" cssClass="col-sm-2 control-label">Details</form:label>
                      			<div class="col-sm-10">                      				
                      				<form:textarea path="details" rows="3" cssClass="form-control" placeholder="details ..."/>
                      				<form:errors path="details" class="control-label" />
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
							<spring:bind path="quantity">
							<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="quantity" cssClass="col-sm-2 control-label">Quantity</form:label>
								<div class="col-sm-10">
									<form:input path="quantity" cssClass="form-control" placeholder="Quantity" />
									<form:errors path="quantity" class="control-label" />
								</div>
							</div>
							</spring:bind>
							<spring:bind path="purchasePrice">
							<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="purchasePrice" cssClass="col-sm-2 control-label">Purchase Price</form:label>
								<div class="col-sm-10">
									<form:input path="purchasePrice" cssClass="form-control" placeholder="Purchase Price" />
									<form:errors path="purchasePrice" class="control-label" />
								</div>
							</div>
							</spring:bind>
							<spring:bind path="productPrice">
								<div class="form-group ${status.error  ? 'has-error' : ''}">
								<form:label path="productPrice" cssClass="col-sm-2 control-label">Sales Price</form:label>
								<div class="col-sm-10">
									<form:input path="productPrice" cssClass="form-control" placeholder="Sales Price" />
									<form:errors path="productPrice" class="control-label" />
								</div>
								</div>
							</spring:bind>
							<div class="pull-right">
							<button class="btn btn-primary btn-sm" type="submit" id="action" name="action" value="add">
										<i class="fa fa-plus-square"></i> Add
									</button>
							</div>
		                </div><!-- /.box-body -->
		                </div>
		                <div class="col-xs-12">
		                <div class="box-body table-responsive">								
								<table id="product" class="table table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Quantity</th>
											<th>Price</th>
											<th>Total</th>
											<th>Sell Price</th>
											<th style="width: 100px"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="pline" items="${purchased.productLineInfos }"
											varStatus="index">
											<tr>
												<td>${pline.product.name }</td>
												<td>${pline.quantity }</td>
												<td>${pline.purchasePrice }</td>
												<td>${pline.totalItem }</td>
												<td>${pline.salePrice }</td>
												<td>
													<div class="pull-right">
														<a href='<c:url value="/purchase/select/line/${index.count }"/>' class="btn btn-primary btn-sm"><i class="fa fa-edit"></i></a> 
														<a href='<c:url value="/purchase/delete/line/${index.count }"/>' class="btn btn-primary btn-sm"><i class="fa fa-trash"></i></a>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.box-footer -->
		                </div>
		                <div class="box-footer clearfix">
		                	<a href="<c:url value="/purchase/clear"/>" class="btn btn-sm btn-danger btn-flat pull-left">Clear</a> 
							<button class="btn btn-sm btn-info btn-flat pull-right" type="submit" id="action" name="action" value="confirm">Submit</button>
						</div><!-- /.box-footer -->
		                </form:form>		                	                
					</div><!-- /.box -->
				</div><!-- /.col -->
			</div><!-- /.row --> 
			</section><!-- /.content -->
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
    <script src="<c:url value="/resources/plugins/select2/select2.full.min.js"/>"></script>
	<!-- InputMask -->
    <script src="<c:url value="/resources/plugins/input-mask/jquery.inputmask.js"/>"></script>
    <script src="<c:url value="/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"/>"></script>
    <script src="<c:url value="/resources/plugins/input-mask/jquery.inputmask.extensions.js"/>"></script>
    <!-- DatePicker -->
    <script src="<c:url value="/resources/plugins/datepicker/bootstrap-datepicker.js"/>"></script>
	<script>
      $(function () {        
		//Datemask dd/mm/yyyy
       	/* $("#purchaseDate").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"}); */
       	/*
       	$("#purchaseDate").datepicker({
       		format:"dd/mm/yyyy"
       	}); 
       	$("#purchaseDate").datepicker().datepicker('setDate', new Date());

       	//*/
       	$("#purchaseDate").inputmask("dd/mm/yyyy",{placeholder:"dd/mm/yyyy"}); 
      });
    </script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
</body>
</html>