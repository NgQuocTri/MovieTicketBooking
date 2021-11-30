<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${movie.movieName}</title>
</head>
<body>
	<br>
	<h2>Chi tiết bộ phim</h2>
	<div class="card mt-4">
		${movie.trailerLink}
	</div>
	<br>
	<table border="1" cellpadding="10" cellspacing="5">
		<tr>
			<th rowspan="2"><img width="150" src="<c:url value="/uploads/moviePoster/${movie.imageName}"></c:url>" alt=""/></th>
			<th>Tên phim</th>
			<th>Thể loại</th>
			<th>Độ tuổi</th>
			<th>Nội dung</th>
		</tr>
		<tr>
			<td>${movie.movieName}
			<td>${movie.genre.genreName}</td>
			<td>${movie.movieAge.ageLimit}</td>
			<td>${movie.description}</td>
		</tr>
	</table>
	<br>
	<a class="btn btn-primary text-uppercase" href="<c:url value="/home/getDate?movieId=${movie.movieId}"/>" type="button">Đặt vé</a>  
		 
</body>
</html>

