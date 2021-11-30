<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thông tin phim</title>
</head>
<body>
	<h3>Thông tin Phim</h3>
	<h4>Tên phim: <font style="color:red">${movie.movieName}</font></h4>
	
	<table class="table">
		<tr>
			<th>Thể loại</th>
			<th>Độ tuổi giới hạn</th>
		</tr>
		<tr>
			<td>${movie.genre.genreName}</td>
			<td>${movie.movieAge.ageLimit}</td>
		</tr>
	</table>
	
	<h5><b>Mô tả</b></h5>
	<p class="text-left">${movie.description}</p>
	
	<br>
	<table class="table">
		<tr>
			<th>Ngày chiếu</th>
			<th>Giờ chiếu</th>
			<th>Tình trạng</th>
		</tr>
		<c:forEach items="${movie.schedules}" var="schedule">
			<tr>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${schedule.date}" /></td>	
				<td>${schedule.hour}</td>
				<td>${schedule.transientStatus}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div>
		<label><b>Hình ảnh:</b></label>
		<img alt="" width="200"  src='<c:url value="/uploads/moviePoster/${movie.imageName}"/>'>
	</div>
	
	<br>
	<a class="btn btn-primary" href="./movieList">Danh sách phim</a>
	
	<a class="btn btn-success" href="./editMovie?movieId=${movieId}">Chỉnh sửa thông tin phim</a>
	
	<a class="btn btn-info" href="./scheduleList?movieId=${movie.movieId}">Thay đổi lịch chiếu</a>
	
	<a class="btn btn-danger" href="./deleteMovie?movieId=${movieId}">Xóa phim</a>
	
	<a class="btn btn-warning" href="./newMovie">Tạo phim mới</a>	
	
</body>
</html>