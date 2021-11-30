<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<br>
	<div class="entry-header-area ptb-40">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="entry-header">
						<h1 class="entry-title">Thông tin người đặt hàng</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="checkout-area pb-50 cd-center">
		<div class="container card-body">
			<div>
				<!-- If error messages -->
				<div id="checkoutError" class=""></div>
				<!--alert-error-->
				<c:if test="${not empty errorMessage }">
					<div class="alert alert-danger" role="alert">${errorMessage }
					</div>
				</c:if>

				<c:set var="saveUrl"
					value="${pageContext.request.contextPath}/shop/shoppingCartCustomer" />
				<form:form action="${saveUrl }" method="POST"
					modelAttribute="customerInfo">
					<input type="hidden" id="useDistanceWindow"
						name="useDistanceWindow" value="false">
 					
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
					<button id="submitOrder" type="submit" class="btn btn-primary text-uppercase">Tiếp theo</button>
				</form:form>
			</div>

		</div>
	</div>

</body>
</html>