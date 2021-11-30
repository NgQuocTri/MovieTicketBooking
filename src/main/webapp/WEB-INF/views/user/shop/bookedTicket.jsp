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
<link rel="stylesheet" type="text/css" href="app.css">
<title>Book Ticket</title>
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
	<c:set var="saveUrl" value="${pageContext.request.contextPath}/home/viewBookedTicketInfo" />
	<form:form method="POST" action="${saveUrl}">
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
											<input type="hidden" name="movieId" value="${movie.movieId}">
											<input type="hidden" name="scheduleId" value="${schedule.scheduleId}">
											<div class="product-preview-container">
												<tr>
													<th scope="row">Hình ảnh</th>
													<td data-th="Image">
														<div class="row-cart">
															<div class="col-sm-4 hidden-xs">
																<img class="product-image" width="100" height="100"
																	src="<c:url value="/uploads/moviePoster/${movie.imageName}" />">
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<th scope="row">Tên phim</th>
													<td><input class="form-control"	value="${movie.movieName}" readonly></td>
												</tr>
												<tr>
													<th scope="row">Ngày chiếu</th>
													<td>
														<input class="form-control" name="" value="<fmt:formatDate
																		pattern="dd-MM-yyyy" value="${schedule.date}" />" readonly>
													</td>
												</tr>
												<tr>
													<th scope="row">Giờ chiếu</th>
													<td>
														<input class="form-control" name="" value="${schedule.hour}" readonly>
														<input class="form-control" name="scheduleId" value="${schedule.scheduleId}" type="hidden">
													</td>
												</tr>
												<tr>
													<th scope="row">Loại vé</th>
													<td>
														<select id="catId" name="catId"
															class="form-control">
																<c:forEach items="${ticketCategories}" var="cat">
																	<option value="${cat.catId}">${cat.catName}</option>
																</c:forEach>
														</select>
													</td>
												</tr>
												<tr>
													<th scope="row">Chọn ghế</th>
												</tr>
											</div>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<c:forEach items="${rows}" var="row">
		<div class="row">
			<c:forEach items="${columns}" var="column">
				<c:set var="chair" value="${row}${column}"/>
				<c:forEach items="${activeSeats}" var="activeSeat">
					<c:if test="${chair == activeSeat.chair}">
						<div class="col-sm-0.5">
							<div class="form-check">
								<input class="form-check-input" name="chair" type="radio" value="${row}${column}" disabled>
								<div><button style="width:4rem" class="btn btn-secondary" type="button">${row}${column}</button></div> 
							</div>
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${inactiveSeats}" var="inactiveSeat">
					<c:if test="${chair == inactiveSeat.chair}">
						<div class="col-sm-0.5">
							<div class="form-check">
								<input class="form-check-input" name="chair" type="radio" value="${row}${column}" required oninvalid="this.setCustomValidity('Vui lòng chọn ghế')">
								<div><button style="width:4rem" class="btn btn-primary" type="button">${row}${column}</button></div>
							</div>
						</div>
					</c:if>
				</c:forEach>
				<br><br>
			</c:forEach>
		</div>
		</c:forEach>
	</div>
	<tr>
		<td class="p-3" colspan="2">
			<button type="submit" class="btn btn-primary">TIẾP THEO</button>
		</td>								
	</tr>
	</form:form>
</body>