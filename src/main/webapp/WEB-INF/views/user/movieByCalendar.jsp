<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<br><br>
	<h3><b>Chọn ngày</b></h3>
	<br>
	
	<c:set var="lastDate" value=""/>
	<c:set var="fistDate" value=""/>
	<c:forEach items="${dates}" var="date" varStatus="status">
		<c:if test="${status.first}">
			<c:set var="firstDate" value="${date}" />
		</c:if>
		<c:if test="${status.last}">
			<c:set var="lastDate" value="${date}" />
		</c:if>
	</c:forEach>
	
	<div class="carousel-inner">
		<a href="./previous7Dates?date=${firstDate}">
			<i class="fas fa-arrow-alt-circle-left" style="font-size: 30px"></i>
		</a>
		
		<c:forEach items="${dates}" var="date">
				<a href="./movieByDate?date=${date}&dates=${datesAsString}">
					<button type="button" class="btn btn-light">
						<fmt:formatDate type="date" pattern="E" value="${date}"/>
						<br>
						<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${date}"/>
					</button>
				</a>
		</c:forEach>
		
		<a href="./next7Dates?date=${lastDate}">
			<i class="fas fa-arrow-alt-circle-right" style="font-size: 30px"></i>
		</a>
	</div>
	
	<br>
	<h5><b><fmt:formatDate type="date" pattern="E yyyy-MM-dd" value="${thisDate}"/></b></h5>
	
	<br>
	<c:if test="${empty moviesByDate}">
		<h4>Không tìm thấy kết quả tương ứng</h4>
	</c:if>
	
    <div class="row">
		<c:forEach items="${moviesByDate}" var="movie">
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
					<a class="btn btn-primary text-uppercase" href="<c:url value="/home/getDate?movieId=${movie.movieId}"/>" type="button">Đặt vé</a> 
				</div>
			</div>
		</c:forEach>
	</div>
	
	
</body>
</html>