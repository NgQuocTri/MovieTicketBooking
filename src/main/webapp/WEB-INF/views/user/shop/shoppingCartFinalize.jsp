<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<div class="entry-header-area ptb-40 bd-margin-top">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="entry-header">
						<h1 class="entry-title"><div class="alert alert-success" role="alert">Đặt vé thành công</div></h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- <div class="checkout-area pb-50">
		<div class="container">
			<h3>Cảm ơn đã đặt hàng</h3>
			Số đơn hàng của bạn là: ${lastOrderedCart.orderNum}
		</div>
	</div> --%>
	<div>
		<div class="wc-proceed-to-checkout">
			<div class="buttons-cart">
				<a class="btn btn-primary text-uppercase"
					href="${pageContext.request.contextPath}/home">Trở về trang chủ</a>
			</div>
		</div>
	</div>
</body>
</html>
