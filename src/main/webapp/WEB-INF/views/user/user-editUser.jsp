<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thông tin cá nhân</title>
</head>
<body>
	<br>
	<h2>Sửa thông tin cá nhân</h2>
	<div class="card" style="width: 18rem;">

		<div class="card-body">
			<p class="card-text">
			<form:form enctype="multipart/form-data" action="${pageContext.request.contextPath}/user/saveEditedUser" modelAttribute="user" method="post">
				<form:input type="hidden" path="userId" value="${user.userId}"/>
				
				<div class="form-group">
					<label for="userName">Tên tài khoản:</label> 
					<input type="text" class="form-control" name="userName" value="${user.userName}" readonly/>
				</div>
				
				<div class="form-group">
					<label for="firstName">Tên:</label> 
					<form:input type="text" class="form-control" path="firstName" required="required"/>
				</div>

				<div class="form-group">
					<label for="lastName">Họ:</label> 
					<form:input type="text" class="form-control" path="lastName" required="required"/>
				</div>
				
				<div class="form-group">
					<label for="email">Email:</label> 
					<form:input type="email" class="form-control" path="email" required="required"/>
				</div>
				
				<div class="form-group">
					<label for="address">Địa chỉ:</label> 
					<form:input type="text" class="form-control" path="address" required="required"/>
				</div>
				
				<div class="form-group">
					<label for=phoneNumber>Số điện thoại:</label> 
					<form:input type="text" class="form-control" path="phoneNumber" required="required"/>
				</div>
				
				<div class="form-group">
				    <label for="exampleFormControlFile1">Ảnh đại diện</label>
				    <img alt="" width="100" src="<c:url value="/uploads/user/${user.imageName}"/>" />
				    <form:input type="hidden" path="imageName" value="${user.imageName}"/>
				    <form:input type="file" path="imageFile" class="form-control-file" id="profileImage"/>
				</div>
				
				<button type="submit" class="btn btn-primary">Lưu</button>
			</form:form>
			</p>
		</div>
	</div>
</body>