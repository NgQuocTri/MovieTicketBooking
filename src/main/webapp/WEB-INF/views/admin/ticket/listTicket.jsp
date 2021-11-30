<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>List Ticket </title>
</head>
<body>
	<h3>Danh sách vé</h3>
	
	<div class="text-left">
	<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-3"
     	  action="./searchTicket"
     	  method="post"
     >
       <div class="input-group">
         <input type="text" name="search" class="form-control" placeholder="Nhập tên phim" value="${searchValue}">
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
		<c:if test="${empty ticketList}"><h5>Không tìm thấy kết quả tương ứng.</h5></c:if>
	</c:if>
	
	<c:if test="${not empty ticketList}">
	<table class="table">
		<tr>
			<th>Chi tiết</th>
			<th>Tên phim</th>
			<th>Ngày & giờ đặt</th>
			<th>Ghế</th>
			<th>Loại vé</th>
			<th>Xóa</th>
		</tr>
		
		<c:forEach var="ticket" items="${ticketList}">
		<tr>
			<td><a href="./detailTicket?ticketId=${ticket.ticketId}&orderId=${orderId}"><i class="fas fa-info-circle"></i></a></td>
			<td>${ticket.movie.movieName}</td>
			<td><fmt:formatDate pattern = "dd-MM-yyyy HH:mm:ss" 
         			value = "${ticket.issueDate}" /> </td>
			<td>${ticket.seat.chair}</td>
			<td>${ticket.ticketCategory.catName}</td>
			<sec:authorize access="hasRole('ADMIN')">  
				<td><a href="./deleteTicket?ticketId=${ticket.ticketId}"><i class="far fa-trash-alt"></i></a></td>
			</sec:authorize>  
		</tr>
		</c:forEach>
	</table>
	</c:if>
	
</body>