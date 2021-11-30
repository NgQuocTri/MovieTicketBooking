<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
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
						<h2 class="entry-title">Giỏ hàng</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- If error messages -->
	<div id="checkoutError" class=""></div>
	<!--alert-error-->
	<c:if test="${not empty errorMessage&& errorMessage!=null }">
		<div class="alert alert-danger" role="alert">${errorMessage }</div>
	</c:if>
	<c:choose>
		<c:when
			test="${cartForm != null && cartForm.cartLines != null&& !empty cartForm.cartLines}">
			<div class="cart-main-area ptb-40">
				<div class="container">
					<div class="cart-main-area ptb-40">
						<div class="container">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="table-content table-responsive">
										<table id="mainCartTable"
											class="table table-hover table-condensed">
											<thead>
												<tr>
													<th>Hình ảnh</th>
													<th>Ngày mua vé</th>
													<th>Tên phim</th>
													<th>VAT</th>
													<th>Số Ghế</th>
													<th>Ngày chiếu</th>
													<th>Giờ chiếu</th>
													<th>Giá</th>
													<th>Xóa sản phẩm</th>
												</tr>
											</thead>
											<tbody>
												<c:set var="saveUrl"
													value="${pageContext.request.contextPath}/shop/shoppingCart" />
												<%-- <c:if
											test="${cartForm != null && cartForm.cartLines != null&& !empty cartForm.cartLines}"> --%>
												<form:form method="POST" modelAttribute="cartForm"
													action="${saveUrl }">

													<div class="product-preview-container">
														<c:forEach items="${cartForm.cartLines}"
															var="cartLineInfo" varStatus="status">
															<tr>
																<td data-th="Image">
																	<div class="row-cart">
																		<div class="col-sm-4 hidden-xs">
																			<img class="product-image" width="150" height="150"
																				src="<c:url value="/uploads/moviePoster/${cartLineInfo.bookedTicketInfo.movie.imageName}" />">
																		</div>
																	</div>
																</td>
																<td><fmt:formatDate pattern="dd-MM-yyyy"
																		value="${cartLineInfo.bookedTicketInfo.issueDate}" /></td>
																<td>${cartLineInfo.bookedTicketInfo.movie.movieName }</td>
																<td>${cartLineInfo.bookedTicketInfo.vat}%</td>
																<td>${cartLineInfo.bookedTicketInfo.seat.chair}</td>
																<td><fmt:formatDate pattern="dd-MM-yyyy"
																		value="${cartLineInfo.bookedTicketInfo.schedule.date}" /></td>
																<td>${cartLineInfo.bookedTicketInfo.schedule.hour}</td>
																<td><fmt:formatNumber pattern="#,##0"
																		value="${cartLineInfo.bookedTicketInfo.price.unitPrice}"
																		var="unitPrice" /> <c:out value="${unitPrice}"></c:out>đ
																</td>

																<td><input type="hidden" name="priceId"
																	value="${cartLineInfo.bookedTicketInfo.price.priceId}" />
																	<a
																	href="<c:url value='/shop/shoppingCartRemoveBookedTicket?id=${cartLineInfo.bookedTicketInfo.ticketId}&&priceId=${cartLineInfo.bookedTicketInfo.price.priceId }' />">
																		<i class="far fa-trash-alt"></i>
																</a></td>
															</tr>
														</c:forEach>
													</div>

													<tr>
														<td class="p-3"><a
															class="btn btn-primary text-uppercase"
															href="${pageContext.request.contextPath}/home">Mua vé
																khác</a></td>

														<td class="p-3"><a
															href="<c:url value='/shop/shoppingCartCustomer' />"
															class="btn btn-primary text-uppercase">Mua Vé</a></td>
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
		</c:when>
		<c:otherwise>
			<div class="p-3">
				<div>Giỏ hàng chưa có sản phẩm!!</div>
			</div>
			<div>
				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/home">Tiếp tục mua</a>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>