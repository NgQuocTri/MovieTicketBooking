<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script language="javascript" src="/resources/js/effect.js"></script>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/for-user-password.css" />"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP"
	crossorigin="anonymous">
</head>
<body>
	
	<h2>Đăng kí tài khoản</h2>
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">
		  ${errorMessage}
		</div>
	</c:if>
	<div class="card cd-center">
		<div class="card-body">
	
			
			<form:form enctype="multipart/form-data" action="./register" modelAttribute="user" method="post">
		
				<div class="form-group">
					<label for="userName">Tên đăng nhập:</label> 
					<form:input type="text" class="form-control" path="userName"/>
				</div>
				
				<div class="form-group">
					<label for="userName">Mật khẩu:</label> 
					<form:input type="password" class="form-control" path="password"/>
				</div>

				<div class="form-group">
					<label for="lastName">Họ:</label> 
					<form:input type="text" class="form-control" path="lastName" />
				</div>
				
				<div class="form-group">
					<label for="firstName">Tên:</label> 
					<form:input type="text" class="form-control" path="firstName"/>
				</div>

				<div class="form-group">
					<label for="email">Email:</label> 
					<form:input type="email" class="form-control" path="email" />
				</div>
				
				<div class="form-group">
					<label for="address">Địa chỉ:</label> 
					<form:input type="text" class="form-control" path="address" />
				</div>
				
				<div class="form-group">
					<label for="phoneNumber">Số điện thoại:</label> 
					<form:input type="text" class="form-control" path="phoneNumber" />
				</div>
				
				<button type="submit" class="btn btn-primary">Đăng kí</button>
			</form:form>

		</div>
	</div>

	<!--  -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
