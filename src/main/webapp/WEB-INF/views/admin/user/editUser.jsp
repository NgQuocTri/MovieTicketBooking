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
<title>Chỉnh sửa</title>
</head>
<body>
	<h2>Chỉnh sửa thông tin người dùng</h2>
	<div class="card cd-center">

		<div class="card-body">
			<p class="card-text">
			<form:form enctype="multipart/form-data" action="./saveUser" modelAttribute="user" method="post">
				<form:input type="hidden" path="userId"/>
				
				<div class="form-group">
					<label for="lastName">Họ:</label> 
					<form:input type="text" class="form-control" path="lastName" />
				</div>
				
				<div class="form-group">
					<label for="firstName">Tên:</label> 
					<form:input type="text" class="form-control" path="firstName" />
				</div>

				<div class="form-group">
					<label for="email">Email:</label> 
					<form:input type="email" class="form-control" path="email"/>
				</div>
				
				<div class="form-group">
					<label for="address">Địa chỉ:</label> 
					<form:input type="text" class="form-control" path="address" />
				</div>
				
				<div class="form-group">
					<label for="phoneNumber">Số điện thoại:</label> 
					<form:input type="text" class="form-control" path="phoneNumber" />
				</div>
				
				<div class="form-group">
				    <label for="exampleFormControlFile1">Ảnh đại diện</label>
				    <img alt="" width="100" src="<c:url value="/uploads/user/${user.imageName}"/>" />
				    <form:input type="hidden" path="imageName" value="${user.imageName}"/>
				    <form:input type="file" path="imageFile" class="form-control-file" id="profileImage"/>
				</div>
				
				<div class="dropdown text-left">
					<label for="dept">Chọn quyền hạn</label><br>
					<form:select mutipal="true" path="permissionList">
					<c:forEach items="${permissionList}" var="permission">
						<c:set var="isSelected" value="false" />
						<c:forEach items="${user.permissionList }" var="c">
							<c:if test="${permission.permissionId == c.permissionId }">
								<c:set var="isSelected" value="true" />
								<form:option selected="selected" value="${permission.permissionId}">${permission.permissionName}</form:option>
							</c:if>
						</c:forEach>

						<c:if test="${isSelected == false }">
							<form:option value="${permission.permissionId}">${permission.permissionName}</form:option>
						</c:if>
					</c:forEach>
					</form:select>
				</div>
				
				<a href="./userProfile" class="btn btn-primary text-uppercase">Trở lại</a>
				<button type="submit" class="btn btn-primary text-uppercase">Lưu</button>
			</form:form>
			</p>
		</div>
	</div>
</body>