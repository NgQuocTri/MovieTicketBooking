<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit movie</title>
<style type="text/css">
	div{
		text-align:left;
	}
</style>
</head>
<body>
	<div class="checkout-area pb-50 cd-center">
 		<div class="card-header"> 
			<h3>Chỉnh sửa thông tin khách hàng</h3> 
			<c:if test="${not empty errorMessage }">
				<div class="alert alert-danger" role="alert">${errorMessage }</div>
			</c:if>
 		</div>
		<div class="container card-body">
			<form:form enctype="multipart/form-data" action="./editCustomer" method="post" modelAttribute="customer">
				<form:input type="hidden" path="customerId" />
								
				<div class="form-group">
						<label for="customerName">Họ và tên</label>
						<form:input path="customerName" id="customer.customerName"
									name="customer.billing.customerName" class="required form-control"
									title="Nhập tên của bạn" autofocus="autofocus" type="text"
									value="" maxlength="32" />
					</div>
					
					<div class="form-group">
						<label for="customerAddress">Địa chỉ</label>
						<form:input path="customerAddress" id="customer.billing.customerAddress"
									name="customer.billing.customerAddress" class="required form-control"
									title="Nhập địa chỉ của bạn" autofocus="autofocus" type="text"
									value="" />
					</div>
					
					<div class="form-group">
						<label for="customerEmail">Email</label>
						<form:input path="customerEmail" id="customer.customerEmail"
									name="customer.customerEmail" class="required form-control"
									pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
									title="Email không đúng chuẩn!" autofocus="autofocus" type="text"
									value="" />
					</div>
					
					<div class="form-group">
						<label for="customerPhone">Số điện thoại</label>
						<form:input path="customerPhone" id="customer.billing.customerPhone"
									name="customer.billing.customerPhone" class="required form-control"
									pattern="((09|03|07|08|05)+([0-9]{8})\\b)"
									title="Số điện không hợp lệ hoặc số điện thoại phải không đúng chuẩn!"
									autofocus="autofocus" type="text" value="" />
					</div>
				
				<a href="./customerList" class="btn btn-primary text-uppercase">Trở về</a>
				<button class="btn btn-primary text-uppercase" type="submit">Lưu</button>
			</form:form>
		</div>
	</div>
</body>
</html>