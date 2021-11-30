<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Danh sách giảm giá</title>
</head>
<body>
	<h3>Danh sách giảm giá</h3>

	<table class="table">
		<tr>
			<th>Ngày bắt đầu</th>
			<th>Ngày kết thúc</th>
			<th>Giảm giá</th>
			<th class="tb-list">Chỉnh sửa</th>
			<th class="tb-list">Xóa</th>
		</tr>
		<c:forEach items="${discounts}" var="d">
			<tr>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${d.startDate}" /></td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${d.endDate}" /></td>
				<td><fmt:formatNumber pattern="#" value="${d.percentage}"
						var="percentage" /> <c:out value="${percentage}"></c:out>%</td>
				<td class="tb-list"><a
					href="./editDiscount?discountId=${d.discountId}"><i
						class="fas fa-edit"></i> <span></span> </a></td>
				<td class="tb-list"><a
					href="./deleteDiscount?discountId=${d.discountId}"><i
						class="far fa-trash-alt"> </i><span></span> </a></td>
			</tr>
		</c:forEach>

	</table>
	<a class="btn btn-primary text-uppercase" href="./newDiscount">Thêm
		giảm giá mới</a>
</body>
</html>

