<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/home">MiniCinema</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        <sec:authorize access="isAuthenticated()">
	        <li class="nav-item active">
	            <a class="nav-link" href="${pageContext.request.contextPath}/user/userProfile"><i class="fas fa-user"></i>
	              Xin chào <sec:authentication property="principal.username"/>
	              <span class="sr-only">(current)</span>
	            </a>
	          </li>
          </sec:authorize>
          <sec:authorize access="isAuthenticated()">
	        <li class="nav-item active">
	            <a class="nav-link" href="${pageContext.request.contextPath}/user/orderHistoryList">Lịch sử đặt vé
	              <span class="sr-only">(current)</span>
	            </a>
	          </li>
          </sec:authorize>
          
          <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item active">
          	<a class="nav-link" href="${pageContext.request.contextPath}/home/ticketPrice">Giá vé</a>
          </li>
	      <li class="nav-item active">
	            <a class="nav-link" href="${pageContext.request.contextPath}/home/customerService">Hỗ trợ khách hàng</a>
	      </li>
          
          <sec:authorize access="hasRole('ADMIN')">
			  <li class="nav-item">
	            <a class="nav-link" href="${pageContext.request.contextPath}/admin/userList">Quản lý rạp</a>
	          </li>
		  </sec:authorize>
		  
		  <sec:authorize access="isAuthenticated()">
          	<li class="nav-item">
          		<a class="nav-link" href="/logout">Đăng xuất</a>
          	</li>
          </sec:authorize>
		  
<%--          <a class="nav-link" href="/logout">Logged as <sec:authentication property="principal.username" /></a> --%>
         
          <sec:authorize access="!isAuthenticated()">
			  <li class="nav-item">
	            <a class="nav-link" href="/login">Đăng nhập</a>
	          </li>
		  </sec:authorize>
        </ul>
      </div>
    </div>
  </nav>