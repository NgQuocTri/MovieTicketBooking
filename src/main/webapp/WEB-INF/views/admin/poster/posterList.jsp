<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>All price</title>
</head>
<body>
	<table class="table">
		<tr>
			<th>Xóa</th>
			<th>Áp phích</th>
		</tr>
		<c:forEach items="${posters}" var="poster">
			<tr>
				<td><a href="./deletePoster?posterId=${poster.posterId}"><i class="far fa-trash-alt"> </i><span></span></a>
				<td><img width="150" src="<c:url value="/uploads/cinemaPoster/${poster.imageName}"></c:url>" alt=""/></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${posterNumber < 3}">
		<a class="btn btn-primary" href="./addPoster">Thêm Áp phích</a>
	</c:if>
</body>