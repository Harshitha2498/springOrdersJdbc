<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,com.app.model.Items,com.app.model.OrderItems"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	max-width: 300px;
	/* 	margin: auto; */
	text-align: center;
	font-family: arial;
	padding: 1rem;
}

.title {
	color: grey;
	font-size: 18px;
}

a {
	border: solid;
	outline: 0;
	display: inline-block;
	padding: 8px;
	color: white;
	background-color: green;
	text-align: center;
	cursor: pointer;
	/* width: 100%; */
	font-size: 18px;
}

img {height =
	
}

a:hover {
	opacity: 0.7;
}
</style>
</head>
<body>
	<h2 style="text-align: center">Ordered Items</h2>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<%-- <form
						action="addOrderedItems"
						method="post" style="text-align: left">
						<input type="submit" value="Add Item" class="btn btn-primary">
					</form>
					<input type="hidden"  name="orderId" value="${orderId}"/>  --%>
				<form:form method="POST" action="addOrderedItems"
					cssClass="form-horizontal">
					<input type="hidden" name="orderId" value="${orderId}" />
					<input type="submit" value="Add Item" class="btn btn-primary">
				</form:form>
			</div>
			<div class="col-md-6">
				<form action="/SpringOrders/" style="text-align: right">
					<input type="submit" value="back" class="btn btn-primary">
				</form>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<c:forEach items="${list}" var="orderItems">
				<div class="col-md-3 h-100 d-flex">
					<div class="card h-100">
						<img src="img/${orderItems.items.itemId}.jpg" alt="Items"
							class="card-img-top img-fluid"
							style="width: 100%; height: 200px; object-fit: cover;">
						<h1>${orderItems.items.itemName}</h1>
						<p>Price :${orderItems.items.itemPrice}</p>
						<p>Quantity : ${orderItems.quantity}</p>
						<div class="d-flex align-items-center">
						<%-- <input type="hidden" name="orderId" value="${orderId}" /> --%>
							<a href="oiedit?id=${orderItems.id}">Edit</a> <a
								href="oidelete?id=${orderItems.id}&orderitemid=${orderItems.orders.orderId}">Delete</a>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
	<!-- Bootstrap JS (Optional) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>