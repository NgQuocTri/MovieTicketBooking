<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order details</title>
</head>

<body>
	<h3 class="text-center">Chi tiết đơn hàng</h3>

	<form:form enctype="multipart/form-data" action="./saveOrder"
		modelAttribute="order" method="post">
		<form:input type="hidden" path="orderId" />
		<h5 class="font-weight-bold">Chi tiết khách hàng</h5>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-4 text-left">Tên:</div>
				<div class="col-4 text-left">${order.customer.customerName}</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-4 text-left">Địa chỉ:</div>
				<div class="col-4 text-left">${order.customer.customerAddress}</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-4 text-left">Số điện thoại:</div>
				<div class="col-4 text-left">${order.customer.customerPhone}</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-4 text-left">Email:</div>
				<div class="col-4 text-left">${order.customer.customerEmail}</div>
			</div>
		</div>

		<h5 class="font-weight-bold">Chi tiết đơn hàng</h5>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-4 text-left">Ngày:</div>
				<div class="col-4 text-left">
					<fmt:formatDate pattern="dd-MM-yyyy" value="${order.orderDate}" />
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-4 text-left">Trạng thái:</div>

				<div class="col-4 dropdown text-left">
					<form:select path="status">
						<form:option value="false">Đang chờ xử lí</form:option>
						<form:option value="true">Đã xác nhận</form:option>
					</form:select>
				</div>

			</div>
		</div>

		<table class="table" style="margin-top: 30px;">

			<tr>
				<th>Hình ảnh</th>
				<th>Tên phim</th>
				<th>Giá</th>
				<th>Chi tiết vé</th>
				<th>Giảm giá</th>
				<th>Tổng tiền</th>
			</tr>

			<c:forEach var="orderDetail" items="${order.orderDetails}">
				<tr>
					<td><img alt="" width="50"
						src='<c:url value="/uploads/moviePoster/${orderDetail.ticket.movie.imageName}"/>'></td>
					<td>${orderDetail.ticket.movie.movieName}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0"
							value="${orderDetail.price }" />đ</td>
					<td><a
						href="./detailTicket?ticketId=${orderDetail.ticket.ticketId}&orderId=${order.orderId}"><i
							class="fas fa-search-plus"></i></a></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0"
							value="${orderDetail.discount}" />%</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0"
							value="${orderDetail.totalPrice}" />đ</td>
				</tr>
			</c:forEach>

		</table>
		<a href="./listOrder" class="btn btn-primary text-uppercase">Trở
			về</a>
		<button type="submit" class="btn btn-primary text-uppercase">Lưu</button>
	</form:form>
</body>