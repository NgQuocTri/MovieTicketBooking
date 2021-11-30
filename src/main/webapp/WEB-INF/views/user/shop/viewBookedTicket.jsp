<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>View Book Ticket</title>
</head>

<body>
	<br>
	<br>
	<div class="entry-header-area ptb-40">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="entry-header">
						<h2 class="entry-title">Chọn thông tin cho vé</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- If error messages -->
	<div id="checkoutError" class=""></div>
	<!--alert-error-->
	<div class="cart-main-area ptb-40">
		<div class="container">
			<div class="cart-main-area ptb-40">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="table-content table-responsive">
								<table id="mainCartTable"
									class="table table-hover table-condensed">
									<tbody>
										<sec:authorize access="isAuthenticated()">
											<c:url var="saveUrl" value="" />
										</sec:authorize>
										<sec:authorize access="!isAuthenticated()">
											<c:url var="saveUrl" value="/shop/buyTicket" />
										</sec:authorize>
										<form:form method="POST" action="${saveUrl}">
											<input type="hidden" name="ticketId"
												value="${ticket.ticketId}">
											<input type="hidden" name="priceId" value="${price.priceId}" />
											<div class="product-preview-container">
												<tr>
													<th scope="row">Hình Ảnh</th>
													<td data-th="Image">
														<div class="row-cart">
															<div class="col-sm-4 hidden-xs">
																<img class="product-image" width="150" height="150"
																	src="<c:url value="/uploads/moviePoster/${ticket.movie.imageName}" />">
															</div>
														</div>
													</td>
												</tr>
											</div>
											<tr>
												<th scope="row">Ngày phát hành vé</th>
												<td><fmt:formatDate pattern="dd-MM-yyyy" value="${ticket.issueDate}" /></td>
											</tr>
											<tr>
												<th scope="row">VAT</th>
												<td>${ticket.vat}%</td>
											</tr>
											<tr>
												<th scope="row">Ngày Chiếu</th>
												<td><fmt:formatDate pattern="dd-MM-yyyy" value="${ticket.schedule.date}" /></td>
											</tr>
											<tr>
												<th scope="row">Giờ Chiếu</th>
												<td>${ticket.schedule.hour}</td>
											</tr>
											<tr>
												<th scope="row">Tên Phim</th>
												<td>${ticket.movie.movieName}</td>
											</tr>
											<tr>
												<th scope="row">Số ghế</th>
												<td>${ticket.seat.chair}</td>
											</tr>
											<tr>
												<th scope="row">Loại Vé</th>
												<td>${ticket.ticketCategory.catName}</td>
											</tr>
											<tr>
												<th scope="row">Giá</th>
												<td><fmt:formatNumber pattern="#,##0"
														value="${price.unitPrice}" var="unitPrice" /> <c:out
														value="${unitPrice}"></c:out>đ</td>
											</tr>
											<tr>
												<td class="p-3" colspan="2">
													<button type="submit"
														class="btn btn-primary text-uppercase">Tiếp tục</button>
												</td>
											</tr>

										</form:form>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>