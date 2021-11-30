<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Danh sách người dùng </title>
</head>
<body>
	<h3>Danh sách người dùng</h3>	
	<div class="text-left">
	<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-3"
     	  action="./searchUser"
     	  method="post"
     >
       <div class="input-group">
         <input type="text" name="search" class="form-control" placeholder="Tìm kiếm" value="${searchValue}">
         <div class="input-group-append">
           <button class="btn btn-primary" type="submit">
             <i class="fas fa-search fa-sm"></i>
           </button>
         </div>
       </div>
     </form>
	</div>
	
	<c:if test="${search}">
		<h4 class="text-left"><b>Kết quả tìm kiếm:</b></h4>
		<c:if test="${empty userList}"><h5 class="text-left">Không tìm thấy kết quả</h5></c:if>
	</c:if>
	
	<c:if test="${not empty userList}">
	<table class="table">
		<tr>
			<th>ID</th>
			<th>Họ</th>
			<th>Tên</th>
			<th class="tb-list">Chỉnh sửa</th>
			<th class="tb-list">Xóa</th>
		</tr>
		
		<c:forEach var="user" items="${userList}">
		<tr>
			<td><a href="<c:url value="/admin/viewUser?userId=${user.userId}"/>">${user.userId}</a></td>
			<td>${user.lastName}</td>
			<td>${user.firstName}</td>
			<sec:authorize access="hasRole('ADMIN')">  
				<td class="tb-list"><a href="./editUser?userId=${user.userId}"><i class="fas fa-edit"></i></a></td>
				<td class="tb-list"><a href="./deleteUser?userId=${user.userId}"><i class="far fa-trash-alt"></i></a></td>
			</sec:authorize>  
		</tr>
		</c:forEach>
	</table>
	<sec:authorize access="hasRole('ADMIN')">  
		<a class="btn btn-primary text-uppercase" href="./newUser" type="button">Tạo mới</a> 
	</sec:authorize>  
	
	</c:if>
</body>