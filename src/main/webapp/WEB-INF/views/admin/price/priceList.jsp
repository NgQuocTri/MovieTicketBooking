<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All price</title>
</head>
<body>
	<h3>Bảng giá</h3>

	<div class="text-left">
		<form
			class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-3"
			action="./searchPrice" method="post">
			<div class="input-group">
				<input type="text" name="search" class="form-control"
					placeholder="Nhập loại vé" value="${searchValue}">
				<div class="input-group-append">
					<button class="btn btn-primary" type="submit">
						<i class="fas fa-search fa-sm"></i>
					</button>
				</div>
			</div>
		</form>
	</div>
	<c:if test="${search}">
		<h4 class="text-left">
			<b>Kết quả tìm kiếm:</b>
		</h4>
		<c:if test="${empty prices}">
			<h5>Không tìm thấy kết quả tương ứng.</h5>
		</c:if>
	</c:if>

	<c:if test="${not empty prices}">
		<table class="table">
			<tr>
				<th>Loại vé</th>
				<th>Từ (giờ:)</th>
				<th>Đến (giờ:)</th>
				<th>Giá</th>
				<th class="tb-list">Chỉnh sửa</th>
				<th class="tb-list">Xóa</th>
			</tr>
			<c:forEach items="${prices}" var="price">
				<tr>
					<td>${price.ticketCategory.catName}</td>
					<td>${price.fromHour}:00</td>
					<td>${price.toHour}:00</td>
					<td><fmt:formatNumber pattern="#,##0"
							value="${price.unitPrice}"
							var="unitPrice" /> <c:out value="${unitPrice}"></c:out>đ</td>
					<td class="tb-list"><a
						href="./editPrice?priceId=${price.priceId}"><i
							class="fas fa-edit"></i></a></td>
					<td class="tb-list"><a
						href="./deletePrice?priceId=${price.priceId}"><i
							class="far fa-trash-alt"></i></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<a class="btn btn-primary" href="./newPrice">Thêm giá vé</a>
</body>