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
	<h3>Bảng giá</h3>
	<br>
	<table class="table table-striped table-dark">
		<thead>
			<tr>
				<th scope="col">Loại vé</th>
				<th scope="col">Từ (giờ:)</th>
				<th scope="col">Đến (giờ:)</th>
				<th scope="col">Giá</th>
				<th scope="col">Đơn vị tính</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${prices}" var="price">
				<tr>
					<th scope="row">${price.ticketCategory.catName}</th>
					<td>${price.fromHour}:00</td>
					<td>${price.toHour}:00</td>
					<td>${price.unitPrice}</td>
					<td>VND</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>