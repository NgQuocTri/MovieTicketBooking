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
	<title>List Order</title>
</head>
<body>
	<h3>Danh sách đơn hàng</h3>
	<!-- Search -->
	<div class="text-left form-inline">
	<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-3"
     	  action="./searchOrder"
     	  method="post"
     >
       <div class="input-group">
         <input type="text" name="searchName" class="form-control" placeholder="Họ và Tên" value="${searchName}">
         <input class="form-control" type="date" name="searchDate" value="${searchDate}"/>
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
		<c:if test="${empty orderList}"><h5>Không tìm thấy kết quả tương ứng.</h5></c:if>
	</c:if>
	
	<c:if test="${not empty orderList}">
	<table class="table pd-3">
		<tr>
			<th class="tb-list">Chi tiết</th>
			<th>Ngày</th>
			<th>Giá</th>
			<th>Tên khách hàng</th>
			<th>Trạng thái</th>
		</tr>

		<c:forEach var="item" items="${orderList}">
			<tr>
				<td><a href="./detailOrder?orderId=${item.orderId}"><i class="fas fa-info-circle"></i></a></td>
				<td><fmt:formatDate pattern = "dd-MM-yyyy" 
         			value = "${item.orderDate}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.totalPrice}"/>đ</td>
				<td>${item.customer.customerName }</td>
				<td>
					<c:if test="${item.status == false}"> Đang chờ xử lí </c:if>
					<c:if test="${item.status == true}"> Đã xác nhận </c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>