<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<form action="./categorizePayment">
		<div class="card" style="width: 18rem;">
  		<ul class="list-group list-group-flush">
   			 <li class="list-group-item"><input type="radio" name="payment" value="i" checked>International payment</li>
   			 <li class="list-group-item"><input type="radio" name="payment" value="a" >ATM card</li>
 		 </ul>
 		 <button type="submit">Submit</button>
	</div>	
	</form>
</body>
</html>