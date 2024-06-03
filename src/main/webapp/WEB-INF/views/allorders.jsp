<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="com.app.model.Orders,java.util.List,com.app.service.OrdersService"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
form {
	text-align: right;
}

table {
	border-collapse: collapse;
	width: 100%;
	border: 1px solid black;
	text-align: center;
}

th, td {
	text-align: left;
	padding: 8px;
	text-align: center;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #04AA6D;
	color: white;
}

.center {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<div class=container>
	<h3 id="h3" style="text-align: center">Welcome to DashBoard</h3>
	
	<div class="form-group">
			<a class="btn btn-secondary" href="<c:url value='/addOrder' />">Add Order</a>
		</div>
	
		<div>
			<table class="center">
				<tr>
					<th>Order Id</th>
					<th>Order Date</th>
					<th>Modify</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${allorders}" var="orders">
					<tr>
						<td>${orders.orderId}</td>
						<td>${orders.orderDate}</td>
						<td><a href="<c:url value='/edit-${orders.orderId}' />">Modify</a></td>
						<td><a href="<c:url value='/delete-${orders.orderId}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script>
		document.getElementById("h3").style.color = "purple";
	</script>
</body>
</html>