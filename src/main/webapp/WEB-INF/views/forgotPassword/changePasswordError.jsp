<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Success</title>
</head>
<body>
<div class="alert alert-success" role="alert">
${errorMessagePass}
</div>

<a href="<c:url value="/forgotPassword"/>"> <i class="fas fa-reply"></i>Quay lại</a>
</body>
</html>
