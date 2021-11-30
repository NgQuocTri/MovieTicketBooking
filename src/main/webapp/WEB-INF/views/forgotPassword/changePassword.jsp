<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script language="javascript" src="/resources/js/effect.js"></script>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/for-user-password.css" />"
	rel="stylesheet">

<link href="<c:url value="/resources/css/forgotpassword.css" />"
	rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ChangePassword</title>

<!-- <style type="text/css">
.inputpassword {
	margin-left: 50px;
}
</style> -->
</head>
<body>


	<div class="container forget-password" style="margin-top: 70px;">
		<div class="row">
			<div class="col-md-12 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body cd-center">
						<c:if test="${not empty errorMessagePass}">
							<div class="alert alert-danger" role="alert">${errorMessagePass}</div>
						</c:if>
						<c:set var="saveUrl"
							value="${pageContext.request.contextPath}/changePassword" />

						<form class="form" action="${saveUrl}" method="post">
							<input type="hidden" value="${userName}" name="userName" />
							<h3 class="text-center">Khôi phục mật khẩu?</h3>
							<label>Mật khẩu mới</label>
							<div class="form-group pass_show">
								<input type="password" name="newPassword" class="form-control"
									id="newPassword" required>
							</div>
							<label>Xác nhận mật khẩu</label>
							<div class="form-group pass_show">
								<input type="password" name="retypePassword"
									class="form-control" id="retypePassword" required>
							</div>
							<center>
								<input class="btn btn-primary" type="submit" value="Khôi phục" />
							</center>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>