<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie List</title>
</head>
<body>
	<br>
	<h3>Chi tiết đơn hàng ${orderId}</h3>
	<table class="table">
		<tr>
			<th>Số lượng</th>
			<th>Đơn giá</th>
			<th>Giảm giá</th>
			<th>Thành tiền</th>
			<th>Vé phim</th>
		</tr>
		<c:forEach items="${orderDetails}" var="orderDetail">
			<tr>
				<td>${orderDetail.quantity}</td>
				<td><fmt:formatNumber pattern="#" value="${orderDetail.price}"
							var="price" /> <c:out value="${price}"></c:out></td>
				<td><fmt:formatNumber pattern="#" value="${orderDetail.discount}"
							var="discount" /> <c:out value="${discount}"></c:out> %</td>
				<td><fmt:formatNumber pattern="#" value="${orderDetail.totalPrice}"
							var="totalPrice" /> <c:out value="${totalPrice}"></c:out></td>
				<td>${orderDetail.ticket.movie.movieName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

