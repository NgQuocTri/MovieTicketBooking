<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<title>Home</title>
</head>
<body>
	<br><br>
	<h3>Chia sẻ ý kiến của bạn tại đây</h3>
	<br>
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>
	<c:if test="${not empty succes}">
		<div class="alert alert-success" role="alert">${succes}</div>
	</c:if>
	<form:form action="${pageContext.request.contextPath}/home/saveFeedback" method="POST" modelAttribute="feedback">
		<div class="form-group">
		   <label for="exampleFormControlInput1">Họ và tên</label>
		   <form:input path="customerName" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Bắt buộc"/>
		 </div>
		 <div class="form-group">
		   <label for="exampleFormControlInput1">Số điện thoại</label>
		   <form:input path="customerPhone" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Bắt buộc"/>
		 </div>
		 <div class="form-group">
		   <label for="exampleFormControlInput1">Email</label>
		   <form:input path="customerEmail" type="email" class="form-control" id="exampleFormControlInput1" placeholder="Bắt buộc"/>
		 </div>
		 <div class="form-group">
		   <label for="exampleFormControlTextarea1">Nội dung</label>
		   <form:textarea path="feedbackContent" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Bắt buộc"></form:textarea>
		 </div>
		 <button class="btn btn-primary" type="submit">Gửi</button>
	</form:form>
</body>
</html>