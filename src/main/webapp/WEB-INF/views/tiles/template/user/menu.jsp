<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-lg-2">
	<h1 class="my-4"><i class="far fa-grin-wink"></i></h1>
	<div class="list-group">
		<h4 class="btn btn-info">Phim theo ngày</h4>
		<a class="btn btn-warning" href="${pageContext.request.contextPath}/home/todayMovies">Phim hôm nay</a>
	</div>
	
	<hr class="sidebar-divider">
	<div class="list-group">
		<h4 class="btn btn-info">Độ tuổi</h4>
		<a href="${pageContext.request.contextPath}/home/movieByAge?ageId=${1}" class="list-group-item">Mọi người</a>
		<a href="${pageContext.request.contextPath}/home/movieByAge?ageId=${2}" class="list-group-item">13+</a>
		<a href="${pageContext.request.contextPath}/home/movieByAge?ageId=${3}" class="list-group-item">16+</a>
		<a href="${pageContext.request.contextPath}/home/movieByAge?ageId=${4}" class="list-group-item">18+</a>
	</div>
	
	<hr class="sidebar-divider">
	<div class="list-group">
		<h4 class="btn btn-info">Thể loại</h4>
		<c:forEach items="${genres}" var="genre">
			<a href="${pageContext.request.contextPath}/home/movieByGenre?genreId=${genre.genreId}" class="list-group-item">${genre.genreName}</a>
		</c:forEach>
	</div>
</div>
