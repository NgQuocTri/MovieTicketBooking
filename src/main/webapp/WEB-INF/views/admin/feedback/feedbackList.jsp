<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feedback</title>
</head>
<body>
	<h3>Phản Hồi Từ Khách Hàng</h3>
	
	<div class="text-left">
	<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-3"
     	  action="./searchFeedback"
     	  method="post"
     >
       <div class="input-group">
         <input type="text" name="search" class="form-control" placeholder="Tên khách hàng" value="${searchValue}">
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
		<c:if test="${empty feedbacks}"><h5>Không tìm thấy kết quả tương ứng.</h5></c:if>
	</c:if>
	
	<c:if test="${not empty feedbacks}">
	<table class="table">
		<tr>
			<th>Tên khách hàng</th>
			<th>Số điện thoại</th>
			<th>Email</th>
			<th>Ngày giờ</th>
			<th>Nội dung</th>
			<th>Xóa</th>
		</tr>
			<c:forEach items="${feedbacks}" var="feedback">
				<tr>
					<td>${feedback.customerName}</td>
					<td>${feedback.customerPhone}</td>
					<td>${feedback.customerEmail}</td>
					<td><fmt:formatDate pattern = "dd-MM-yyyy HH:mm:ss" 
         			value = "${feedback.date}" /></td>
					<td>${feedback.feedbackContent}</td>
					<td><a href="./deleteFeedback?feedbackId=${feedback.feedbackId}"><i class="far fa-trash-alt"></i><span></span></a></td>
				</tr>
			</c:forEach>
	</table>
	</c:if>
</body>
</html>