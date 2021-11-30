<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ticket Detail</title>
</head>
<body>

	<div class="card text-white bg-info mb-3" style="max-width: 500px;">
		<div class="card-body">
			<div class="border-bottom">Ngày đặt: ${ticket.issueDate}</div>

			<div class="container">
				<h3>${ticket.movie.movieName}</h3>
			</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-6 text-left">Giới hạn độ tuổi:</div>
					<div class="col-4 text-left">${ticket.movie.movieAge.ageLimit}</div>
				</div>
			</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-6 text-left">Thể loại:</div>
					<div class="col-4 text-left">${ticket.movie.genre.genreName}</div>
				</div>
			</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-6 text-left">Ngày chiếu:</div>
					<div class="col-4 text-left">
						<fmt:formatDate pattern="dd-MM-yyyy"
							value="${ticket.schedule.date}" />
					</div>
				</div>
			</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-6 text-left">Giờ:</div>
					<div class="col-4 text-left">${ticket.schedule.hour}</div>
				</div>
			</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-6 text-left">Ghế số:</div>
					<div class="col-4 text-left">${ticket.seat.chair}</div>
				</div>
			</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-6 text-left">Loại vé:</div>
					<div class="col-4 text-left">${ticket.ticketCategory.catName}</div>
				</div>
			</div>

			<div class="container">
				<div class="row justify-content-center">
					<div class="col-6 text-left">VAT:</div>
					<div class="col-4 text-left">${ticket.vat}%</div>
				</div>
			</div>

		</div>
	</div>
	<c:if test="${not empty orderId}">
		<a href="./detailOrder?orderId=${orderId}"><button type="button"
				class="btn btn-link">Xem đơn hàng</button></a>
	</c:if>

</body>