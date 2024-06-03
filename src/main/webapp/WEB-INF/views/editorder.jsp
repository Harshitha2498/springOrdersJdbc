
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.app.model.OrderItems"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Updated order Items</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.card {
	width: 50%;
	margin: 0 auto;
	float: none;
	margin-bottom: 10px;
	font-size: 1em;
}
</style>
</head>
<body>orderItemsList
		<form:form method="POST" modelAttribute="order" action="editOrderItems"
			cssClass="form-horizontal">
	<input type="hidden"  name="orderItemId" value="${id}"/>
	
 		<div class="card">
			<div class="card-header"
				style="background-color: lightgreen; text-align: center;">Add
				Items</div>
				<div class="card-body" style="text-align: center;">
                <label for="name">Item Name : </label>
                <select name="itemId" style="width: 188px">
                    <c:forEach var="item" items="${itemsList}">
                        <option value="${item.itemId}">${item.itemName} - ${item.itemPrice}</option>
                    </c:forEach>
                </select>
                <div>
                    <label>Item Quantity : </label>
                    <input type="text" name="quantity" />
                </div>
            </div>
			<div class="card-footer">
				<div class="btn-wrapper text-center">
					<input class="btn btn-primary" type="submit" 
						value="Submit"> <input class="btn btn-secondary "
						type="reset" value="reset">
				</div>
			</div>
		</div>

		</form:form>
	<script>
		document.getElementById("itemname").readOnly = true;
	</script>
</body>
</html>