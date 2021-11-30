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
<title>Đổi mật khẩu</title>
</head>
<body>
	<br><br>
	<h2>Đổi mật khẩu</h2>
	
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>
	
	<div class="card cd-center">
		<div class="card-body">
			<p class="card-text">
				<form:form action="${pageContext.request.contextPath}/user/userProfile/changePassword" modelAttribute="changedPasswordModel" method="post">
					<div class="form-group">
						<label for="oldPassword">Vui lòng nhập mật khẩu cũ của bạn:</label>
						<form:input type="password" id="oldPassword" class="form-control" path="oldPassword"/>
					</div>
					
					<div class="form-group">
						<label for="newPassword1">Vui lòng nhập mật khẩu mới:</label>
						<form:input type="password" id="newPassword1" class="form-control" path="newPassword1"/>
					</div>
					
					<div class="form-group">
						<label for="newPassword2">Vui lòng nhập lại mật khẩu mới:</label>
						<form:input type="password" id="newPassword2" class="form-control" path="newPassword2"/>
					</div>
					
					<button class="btn btn-primary text-uppercase" type="submit">Lưu</button>
				</form:form>
			</p>
		</div>
	</div>
</body>