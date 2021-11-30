<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New price</title>
</head>
<body>
	<h3>Chỉnh sửa giá vé</h3>
	<form:form action="./savePrice" modelAttribute="price" method="post">
		<table class="table">
		<tr>
			<th>Loại</th>
			<th>Từ (giờ:)</th>
			<th>Đến (giờ:)</th>
			<th>Giá</th>
		</tr>
		<tr>
			<form:input type="hidden" path="priceId" value="${price.priceId}" />
				<td>
					<form:select path="ticketCategory">
						<c:forEach items="${ticketCategories}" var="ticketCategory">
							<form:option value="${ticketCategory.catId}">${ticketCategory.catName}</form:option>
						</c:forEach>
					</form:select>
				</td>
				<td>
					<form:input path="fromHour" type="number"/>
				</td>
				<td>
					<form:input path="toHour" type="number"/>
				</td>
				<td>
					<form:input type="number" path="unitPrice"/>
				</td>
			</tr>
		</table>
		<button type="submit" class="text-uppercase btn btn-primary">Lưu</button>
	</form:form>
</body>