<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Success</title>
<link href="<c:url value="/resources/css/notification.css" />" rel="stylesheet">
</head>
<body>

	<div id="w">
		<div id="content">
			<!-- Icons source http://dribbble.com/shots/913555-Flat-Web-Elements -->
			<div class="notify successbox">
				<h1>Thành công!</h1>
				<!-- <span class="alerticon"><img src="http://s22.postimg.org/i5iji9hv1/check.png" alt="checkmark" /></span> -->
				<p>Khôi phục mật khẩu hoàn tất, quay lại để đăng nhập</p>
			</div>

			<!-- <div class="notify errorbox">
        <h1>Warning!</h1>
        <span class="alerticon"><img src="http://s22.postimg.org/ulf9c0b71/error.png" alt="error" /></span>
        <p>You did not set the proper return e-mail address. Please fill out the fields and then submit the form.</p>
      </div> -->

			<div>
			<a class="btn btn-primary" href="<c:url value="/login"/>"> <i class="fas fa-reply"></i> Quay lại trang đăng nhập
			</a>
			</div>
			
		</div>
		<!-- @end #content -->
	</div>
	<!-- @end #w -->

</body>
</html>