<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New schedule</title>
</head>
<body>
	<h3>Thêm lịch chiếu</h3>
	<h4>Phim: ${movie.movieName}</h4>
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>
	<form:form action="./saveSchedule" modelAttribute="movie" method="post">
		<form:input type="hidden" path="movieId" value="${movie.movieId}" />
		
		<label for="date">Ngày</label>
		<form:input type="date" id="date" path="transientDate" required="required"/>
		
		<label for="hour">Giờ</label>
		<form:input type="time" id="hour" path="transientHour" required="required"/>
		
		<button type="submit">Lưu</button>
	</form:form>
</body>
</html>