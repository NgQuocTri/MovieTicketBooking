<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Danh sách phim</title>
</head>
<body>
	<h3>Danh sách phim</h3>
	<div class="text-left">
	<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-3"
     	  action="./findMovie"
     	  method="post"
     >
       <div class="input-group">
         <input type="text" name="search" class="form-control" placeholder="Tìm kiếm" value="${searchValue}">
         <div class="input-group-append">
           <button class="btn btn-primary" type="submit">
             <i class="fas fa-search fa-sm"></i>
           </button>
         </div>
       </div>
     </form>
	</div>
	<c:if test="${search}">
		<h4 class="text-left"><b>Kết quả tìm kiếm:</b></h4>
		<c:if test="${empty movies}"><h5>Không tìm thấy kết quả tương ứng.</h5></c:if>
	</c:if>
	
	<c:if test="${not empty movies}">
	<table class="table">
		<tr>
			<th>Tên phim</th>
			<th>Thể loại</th>
			<th>Độ tuổi</th>
			<th class="tb-list">Chi tiết về phim</th>
			<th class="tb-list">Chỉnh sửa</th>
			<th class="tb-list">Xóa</th>
		</tr>
		<c:forEach items="${movies}" var="movie">
			<tr>
				<td>${movie.movieName}</td>
				<td>${movie.genre.genreName}</td>
				<td>${movie.movieAge.ageLimit}</td>
				<td class="tb-list"><a href="./${movie.movieId}"><i class="fas fa-info-circle"></i> <span></span> </a></td>
				<td class="tb-list"><a href="./editMovie?movieId=${movie.movieId}"><i class="fas fa-edit"></i> <span></span> </a></td>
				<td class="tb-list"><a href="./deleteMovie?movieId=${movie.movieId}"><i class="far fa-trash-alt"> </i><span></span> </a></td>
			</tr>
		</c:forEach>
	</table>
	<a class="btn btn-primary text-uppercase" href="./newMovie">Thêm phim mới</a>
	</c:if>
</body>
</html>

