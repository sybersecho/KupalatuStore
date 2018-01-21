<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar Menu -->
		<ul class="sidebar-menu">
			<li class="header">Main Navigation</li>
			<!-- Optionally, you can add icons to the links -->
			<li class="${supplierActive}"><a href="<c:url value="/supplier"/>"> <i
					class="fa fa-users"></i> <span>Manage Supplier</span>
			</a></li>
			<li class="${productActive}"><a href="<c:url value="/product"/>"> <i
					class="fa fa-barcode"></i> <span>Manage Product</span>
			</a></li>
			<li class="${purchaseActive}"><a href="<c:url value="/purchase"/>"> <i
					class="fa fa-opencart"></i> <span>Purchase</span>
			</a></li>
			<li class="${salesActive}"><a href="<c:url value="/sales"/>"> <i
					class="fa fa-opencart"></i> <span>Sales</span>
			</a></li>
			<li class="treeview ${reportActive}">
				<a href="#">
                	<i class="fa fa-share"></i> <span>Report</span>
                	<i class="fa fa-angle-left pull-right"></i>
              	</a>
              	<ul class="treeview-menu">
              		<li class="${reportPurchaseActive}"><a href="<c:url value="/report/purchase"/>"><i class="fa fa-circle-o"></i> Purchase</a></li>
              		<li class="${reportSalesActive}"><a href="<c:url value="/report/sales"/>"><i class="fa fa-circle-o"></i> Sales</a></li>
              	</ul>
			</li>
		</ul>
		<!-- /.sidebar-menu -->
	</section>
	<!-- /.sidebar -->
</aside>