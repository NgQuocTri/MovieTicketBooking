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
	<form:form action="./savePrice" modelAttribute="price" method="post">
		<h3>Thêm giá vé</h3>
		<table class="table">
		<tr>
			<th>Loại vé</th>
			<th>Từ (giờ:)</th>
			<th>Đến (giờ:)</th>
			<th>Giá</th>
		</tr>
		<tr>
			<td>
				<form:select path="ticketCategory">
					<c:forEach items="${ticketCategories}" var="ticketCategory">
						<form:option value="${ticketCategory.catId}">${ticketCategory.catName}</form:option>
					</c:forEach>
				</form:select>
			</td>
			<td>
				<form:input path="fromHour" type="number" />giờ
			</td>
			<td>
				<form:input path="toHour" type="number" />giờ
			</td>
			<td>
				<form:input type="number" path="unitPrice" />
			</td>
		</tr>
	</table>
	<button class="text-uppercase btn btn-primary" type="submit">Lưu</button>
	</form:form>
</body>