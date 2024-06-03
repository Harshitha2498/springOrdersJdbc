<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.app.model.Items,com.app.model.OrderItems,java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>adding items</title>
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
<body>

		<form:form method="POST" modelAttribute="order" action="addOrderItems"
			cssClass="form-horizontal">
	<input type="text"  name="orderId" value="${orderId}"/> 
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

	<!-- <script>
		function myFunction() {
			let x = (document.getElementsByName("quantity")[0].value);
			console.log("Num: " + Number(x));
			if (isNaN((x))|| (x)<=0 || (x)>1000000||(x % 1) !== 0||x.endsWith(".0")) {
				alert("Quantity should be 1 to 1000000 and the number is in integer value");
			} else {
				document.getElementById("addItem").submit();
			}
		}
	</script> -->
</body>
</html>