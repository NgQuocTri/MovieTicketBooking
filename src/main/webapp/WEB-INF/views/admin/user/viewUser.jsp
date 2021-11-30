<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thông tin cá nhân</title>
</head>
<body>
	<br>
	<h2>Trang cá nhân</h2>
	<table class="table">
		<tr>
			<th rowspan="6"><img alt="" width="100" src="<c:url value="/uploads/user/${user.imageName}"/>" /></th>
			<th>Tên tài khoản</th>
			<td>${user.userName}</td>
		</tr>
		
		<tr>
			<th>Tên</th>
			<td>${user.firstName}</td>
		</tr>
		
		<tr>
			<th>Họ</th>
			<td>${user.lastName}</td>
		</tr>
		
		<tr>
			<th>Email</th>
			<td>${user.email}</td>
		</tr>
		
		<tr>
			<th>Số điện thoại</th>
			<td>${user.phoneNumber}</td>
		</tr>
		
		<tr>
			<th>Địa chỉ</th>
			<td>${user.address}</td>
		</tr>
	</table>
</body>