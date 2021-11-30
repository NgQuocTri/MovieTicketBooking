<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Insert title here</title>
</head>
<body>
	<br>
	<div class="entry-header-area ptb-40">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="entry-header">
						<h1 class="entry-title">Xác nhận đặt vé</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="customer-info-container">
		<div class="card">

			<div class="card-body">
				<p class="card-text">
					<strong>Thông tin khách hàng:</strong>
				<div class="row">
					<div class="col text-left">
						<label>Tên khách hàng:</label>
					</div>
					<div class="col text-left">${myCart.customerInfo.customerName}</div>
				</div>
				<div class="row">
					<div class="col text-left">
						<label>Địa chỉ:</label>
					</div>
					<div class="col text-left">${myCart.customerInfo.customerAddress}</div>
				</div>

				<div class="row">
					<div class="col text-left">
						<label>Số điện thoại liên hệ:</label>

					</div>
					<div class="col text-left">${myCart.customerInfo.customerPhone}</div>
				</div>
				<div class="row">
					<div class="col text-left">
						<label>Email:</label>

					</div>
					<div class="col text-left">${myCart.customerInfo.customerEmail}</div>
				</div>
			</div>

		</div>
	</div>

	<br>
	<div class="table-responsive">

		<table class="table table-bordered dataTable">
			<thead>

				<tr>
					<th>Thông tin phim</th>
					<th>Thông tin vé</th>
					<!--						<th>Số lượng</th>	  
						<th>Giá</th>				-->
					<th>Tổng giá</th>
				</tr>
			<tbody>

				<c:forEach items="${myCart.cartLines}" var="cartLineInfo">
					<tr>

						<td>
							<div class="container">
								<div class="row">
									<div class="col-4">
										<img class="product-image" width="80" height="80"
											src="<c:url value="/uploads/moviePoster/${cartLineInfo.bookedTicketInfo.movie.imageName}" />" />
									</div>
									<div class="col">
										<div>
											<h3>${cartLineInfo.bookedTicketInfo.movie.movieName}</h3>
										</div>
										<div class="text-left">Thể loại:
											${cartLineInfo.bookedTicketInfo.movie.genre.genreName}</div>
										<div class="text-left">Độ tuổi:
											${cartLineInfo.bookedTicketInfo.movie.movieAge.ageLimit}</div>
									</div>
								</div>
							</div>
						</td>

						<td>
							<div class="container">
								<div class="row">
									<div class="col text-left">Ngày:</div>
									<div class="col text-left">${cartLineInfo.bookedTicketInfo.schedule.date}</div>
								</div>
								<div class="row">
									<div class="col text-left">Giờ chiếu:</div>
									<div class="col text-left">${cartLineInfo.bookedTicketInfo.schedule.hour}</div>
								</div>
								<div class="row">
									<div class="col text-left">Ghế ngồi:</div>
									<div class="col text-left">${cartLineInfo.bookedTicketInfo.seat.chair}</div>
								</div>
							</div>
						</td>
						<td><span class="subtotal"> <fmt:formatNumber
									pattern="#,##0" value="${cartLineInfo.amount}" />đ
						</span></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

		<table class="dataTable">
			<tbody>
				<tr>
					<td style="width:600"></td>
					<td style="width:400">
						<div class="container">
							<div class="row">
								<div class="col-8 text-left">
									<label>Tổng giá hóa đơn: </label>

								</div>
								<div class="col-4 text-right">
									<fmt:formatNumber pattern="#,##0" value="${myCart.amountTotal}"
										var="formattedAmountTotal" />
									<c:out value="${formattedAmountTotal}"></c:out>đ
								</div>

							</div>
							<div class="row">
								<div class="col-8 text-left">
									<label>Giảm giá: </label>

								</div>
								<div class="col-4 text-right">
									<fmt:formatNumber pattern="#" value="${myCart.discount}"
										var="discount" />
									${discount}%
								</div>
							</div>
							<div class="row">
								<div class="col-8 text-left">
									<label>Tổng số tiền thanh toán: </label>

								</div>
								<div class="col-4 text-right">
									<fmt:formatNumber pattern="#,##0"
										value="${myCart.amountDiscountTotal}"
										var="formattedAmountDiscountTotal" />
									<c:out value="${formattedAmountDiscountTotal}"></c:out>đ
								</div>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<form:form method="POST"
		action="${pageContext.request.contextPath}/shop/shoppingCartConfirmation">

		<a class="btn btn-secondary text-uppercase"
			href="${pageContext.request.contextPath}/shop/shoppingCartCustomer">Trở
			lại</a>


		<!-- Send/Save -->
		<input type="submit" value="Đặt vé"
			class="btn btn-primary text-uppercase" />
	</form:form>

</body>
</html>