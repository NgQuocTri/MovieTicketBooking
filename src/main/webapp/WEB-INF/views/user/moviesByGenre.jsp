<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
		<br><br>
		<h3><b>${genre.genreName}</b></h3>
		<br>
		
		<c:if test="${empty moviesByGenre}">
			<h4>Không tìm thấy phim tương ứng</h4>
		</c:if>
		
		<div class="row">
				<c:forEach items="${moviesByGenre}" var="movie">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="./movie?movieId=${movie.movieId}"> 
								<img class="card-img-top" src='<c:url value="/uploads/moviePoster/${movie.imageName}"></c:url>' alt="">
							</a>
							<div class="card-body">
								<h4 class="card-title">
									Tên: <a href="./movie?movieId=${movie.movieId}">${movie.movieName}</a>
								</h4>
								<h5>Thể loại: ${movie.genre.genreName}</h5>
							</div>
							<a class="btn btn-primary text-uppercase" href="./getDate?movieId=${movie.movieId}" type="button">
								Đặt vé
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
</body>
</html>