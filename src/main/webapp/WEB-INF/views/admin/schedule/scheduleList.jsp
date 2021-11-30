<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule List</title>
</head>
<body>
	<h3>Lịch chiếu phim</h3>
	<h4>Phim: ${movie.movieName}</h4>
	<table class="table">
		<tr>
			<th>Xóa</th>
			<th>Ngày</th>
			<th>Giờ</th>
			<th>Trạng thái</th>
		</tr>
		<c:forEach items="${movie.schedules}" var="schedule">
			<tr>
				<td><a
					href="./deleteSchedule?scheduleId=${schedule.scheduleId}&movieId=${movie.movieId}"><i
						class="far fa-trash-alt"> </i><span></span></a></td>
				<td><fmt:formatDate pattern="dd-MM-yyyy"
						value="${schedule.date}" /></td>
				<td>${schedule.hour}</td>
				<td>${schedule.transientStatus}</td>
			</tr>
		</c:forEach>
	</table>
	<a class="btn btn-primary"
		href="./createNewSchedule?movieId=${movie.movieId}">Thêm lịch
		chiếu mới</a>
	<a class="btn btn-success" href="./movieList">Danh sách phim</a>
</body>
</html>