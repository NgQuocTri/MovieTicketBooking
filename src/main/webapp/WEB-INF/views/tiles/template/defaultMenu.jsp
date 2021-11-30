<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<ul	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a	class="sidebar-brand d-flex align-items-center justify-content-center"	href="./userList">
		<div class="sidebar-brand-icon rotate-n-15">
			<!-- <i class="fas fa-laugh-wink"></i> -->
			<i class="fas fa-film"></i>
		</div>
		<div class="sidebar-brand-text mx-3">Xin Chào Admin</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Divider -->
	<hr class="sidebar-divider">
	

	<!-- Heading -->
	<!-- <div class="sidebar-heading">Customer</div> -->

	<!-- Nav Item - Pages Collapse Menu -->
	<sec:authorize access="hasRole('ADMIN')">
		<li class="nav-item">
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/home"><i class="fas fa-home"></i>Trang chủ</a>
		</li>
		<!-- Divider -->
	<hr class="sidebar-divider">
	</sec:authorize>

	

	<!-- Heading -->
<!-- 	<div class="sidebar-heading">Admin</div> -->

	<!-- Nav Item - Pages Collapse Menu -->
	<sec:authorize access="hasRole('ADMIN')">
		<li class="nav-item">
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/priceList"><i class="fas fa-clipboard-list"></i>Bảng giá</a>
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/listTicket"><i class="fas fa-clipboard-list"></i>Danh sách vé</a>
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/movieList"><i class="fas fa-clipboard-list"></i>Danh sách phim</a>
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/listOrder"><i class="fas fa-clipboard-list"></i>Danh sách đơn hàng</a> 
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/userList"><i class="fas fa-clipboard-list"></i>Danh sách người dùng</a>
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/customerList"><i class="fas fa-clipboard-list"></i>Danh sách khách hàng</a>
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/feedbackList"><i class="fas fa-clipboard-list"></i>Danh sách phản hồi</a>
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/discountList"><i class="fas fa-clipboard-list"></i>Danh sách giảm giá</a>
			<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/posterList"><i class="fas fa-clipboard-list"></i>Danh sách áp phích</a>
			
		</li>
	</sec:authorize>
</ul>