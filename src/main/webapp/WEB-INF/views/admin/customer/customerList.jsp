<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>All customers</title>
</head>
<body>
	<h3>Danh sách khách hàng</h3>
	
	<div class="text-left form-inline">
	<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-3"
     	  action="./searchCustomer"
     	  method="post"
     >
       <div class="input-group">
         <input type="text" name="searchName" class="form-control" placeholder="Họ và Tên" value="${searchName}">
         <input type="text" name="searchPhone" class="form-control" placeholder="Số điện thoại" value="${searchPhone}">
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
		<c:if test="${empty customers}"><h5>Không tìm thấy kết quả tương ứng.</h5></c:if>
	</c:if>
	
	<c:if test="${not empty customers}">
	<table class="table">
		<tr>
			<th>Tên</th>
			<th>Địa chỉ</th>
			<th>Email</th>
			<th>Số điện thoại</th>
			<th>Sửa</th>
			<th>Xóa</th>
		</tr>
		<c:forEach items="${customers}" var="customer">
			<tr>
				<td>${customer.customerName}</td>
				<td>${customer.customerAddress}</td>
				<td>${customer.customerEmail}</td>
				<td>${customer.customerPhone}</td>
				<td><a href="./editCustomer?customerId=${customer.customerId}"><i class="fas fa-edit"></i> <span></span></a></td>
				<td><a href="./deleteCustomer?customerId=${customer.customerId}"><i class="far fa-trash-alt"></i><span></span></a></td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>