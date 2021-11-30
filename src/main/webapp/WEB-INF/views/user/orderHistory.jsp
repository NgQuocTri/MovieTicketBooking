<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie List</title>
</head>
<body>
	<br>
	<h3>Lịch sử đặt vé</h3>

	<c:if test="${empty orders}">
		<h5>Bạn vẫn chưa đặt vé</h5>
	</c:if>
	<c:if test="${not empty orders}">
		<table class="table">
			<tr>
				<th>Mã đơn hàng</th>
				<th>Ngày đặt hàng</th>
				<th>Giờ</th>
				<th>Tổng số tiền</th>
				<th>Số lượng vé</th>
				<th>Chi tiết</th>
				<th>Trạng thái</th>
			</tr>
			<c:forEach items="${orders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.orderDate}</td>
					<td><fmt:formatDate type="time" value="${order.time}" /></td>
					<td><fmt:formatNumber pattern="#" value="${order.totalPrice}"
							var="totalPrice" /> <c:out value="${totalPrice}"></c:out></td>
					<td><fmt:formatNumber pattern="#"
							value="${order.orderDetailQuantity}" var="orderDetailQuantity" />
						<c:out value="${orderDetailQuantity}"></c:out></td>
					<td><a href="./orderDetailList?orderId=${order.orderId}"><i
							class="fas fa-info-circle"></i></a></td>
					<td><c:if test="${order.status == false}">Đang chờ xử lý</c:if>
						<c:if test="${order.status == true}">Đã xác nhận</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>

