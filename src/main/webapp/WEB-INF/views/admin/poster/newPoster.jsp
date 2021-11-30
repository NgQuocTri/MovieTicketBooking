<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>New Poster</title>
</head>
<body>
	<div class="card" style="width: 700px;">
	<div class="card-header">
	    <h3>Thêm Poster</h3>
	 </div>
	<div class="card-body">
	<form enctype="multipart/form-data" action="./savePoster" method="post">
		<div class="form-group">
			<label for="imageFile">Poster của rạp:</label><br>
			<input type="file" name="imageFile" id="imageFile" class="required" title="Vui lòng nhập khẩu hình ảnh"/>
		</div>
		<button class="btn btn-primary" type="submit">Lưu</button>
	</form>
	</div>
</div>	
</body>