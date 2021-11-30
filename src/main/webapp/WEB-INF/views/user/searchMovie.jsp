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
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<title>Danh sách tìm kiếm</title>
</head>
<body>
	<div class="text-left">
	<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-3"
     	  action="<c:url value="/home/searchMovie"/>"
     	  method="post">
       <div class="input-group">
         <input type="text" name="search" class="form-control" placeholder="Tìm kiếm" value="${searchValue}">
         <div class="input-group-append">
           <button class="btn btn-secondary" type="submit">
             <i class="fas fa-search fa-sm"></i>
           </button>
         </div>
       </div>
     </form>
	</div>
	<div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0" class=""></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1" class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2" class=""></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="carousel-item">
				<img class="d-block img-fluid" height="900" src='<c:url value="/uploads/cinemaPoster/${poster1.imageName}"/>' alt="First slide">
			</div>
			<div class="carousel-item active">
				<img class="d-block img-fluid" height="900" src='<c:url value="/uploads/cinemaPoster/${poster2.imageName}"/>' alt="Second slide">
			</div>
			<div class="carousel-item">
				<img class="d-block img-fluid" height="900" src='<c:url value="/uploads/cinemaPoster/${poster3.imageName}"/>' alt="Third slide">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev"> 
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> 
			<span class="sr-only">Previous</span>
		</a> 
		
		<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next"> 
			<span class="carousel-control-next-icon" aria-hidden="true"></span> 
			<span class="sr-only">Next</span>
		</a>
	</div>
	
	<div><h3>Kết quả tìm kiếm:</h3></div>
	<c:if test="${empty movies}">
		<h5>Không tìm thấy kết quả!</h5>
	</c:if>
	<c:if test="${not empty movies}">
    <div class="row">
		<c:forEach items="${movies}" var="movie">
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-100">
					<a href="${pageContext.request.contextPath}/home/movie?movieId=${movie.movieId}">
						<img class="card-img-top" src='<c:url value="/uploads/moviePoster/${movie.imageName}"></c:url>' alt="">
					</a>
					<div class="card-body">
						<h4 class="card-title">
							Tên phim: <a href=<c:url value="/home/movie?movieId=${movie.movieId}"/>>${movie.movieName}</a>
						</h4>
						<h5>Thể loại: ${movie.genre.genreName}</h5>
					</div>
					<a class="btn btn-primary text-uppercase" href="<c:url value="/home/getDate?movieId=${movie.movieId}"/>" type="button">Đặt vé</a> 
				</div>
			</div>
		</c:forEach>
	</div>
	</c:if>
	
</body>
</html>