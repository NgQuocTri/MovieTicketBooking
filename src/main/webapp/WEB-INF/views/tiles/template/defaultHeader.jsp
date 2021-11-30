<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop"	class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	


	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">

		<!-- Nav Item - User Information -->
		<li class="nav-item dropdown no-arrow">
			<a 	class="nav-link dropdown-toggle" href="#" id="userDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> 
				<span class="mr-2 d-none d-lg-inline text-gray-600 small"><sec:authentication property="principal.username" /></span>  
				<i class="far fa-user-circle"></i>
			</a> 
			
			<!-- Dropdown - User Information -->
			<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
				<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/userProfile"> 
					<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Thông tin cá nhân
				</a>
				<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/userProfile/changePassword"> 
					<i class="fas fa-passport fa-sm fa-fw mr-2 text-gray-400"></i> Đổi mật khẩu
				</a>
				<a class="dropdown-item" href="/logout"> 
					<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Đăng xuất
				</a>
			</div>
		</li>

	</ul>

</nav>