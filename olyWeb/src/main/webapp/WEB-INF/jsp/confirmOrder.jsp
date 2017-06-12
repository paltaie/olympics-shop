<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Confirm Order"/>
		</jsp:include>
		<div id="content">
			<h2>Confirm Order</h2>
			<p>Just have a quick one-over at this info to make sure we aren't screwing you over or sending the order to the wrong person:</p>
			<p>Here's what you're ordering:</p>
			<div id="cart_contents"><img src="resources/images/spinner.gif"/></div>
			<p>And here's the personal info you gave us:</p>
			<table class="lined_table">
				<c:set var="customer" value="${cart.order.customer}"/>
				<tr>
					<td><b>Name</b></td>
					<td><span>${customer.givenName}</span> <span>${customer.surname}</span></td>
				</tr>
				<tr>
					<td><b>Email</b></td>
					<td>${customer.email}</td>
				</tr>
				<tr>
					<td><b>Address</b></td>
					<td><span>${customer.houseNo}</span> ${customer.street}, ${customer.suburb}, ${customer.state}, ${customer.postcode}, ${customer.country}</td>
				</tr>
				<tr>
					<td><b>Credit Card</b></td>
					<td><i>Ending in ${fn:substring(customer.ccNumber, fn:length(customer.ccNumber)-4, fn:length(customer.ccNumber))}</i></td>
				</tr>
			</table>
			<p>Your current options</p>
			<a id="place_order" class="button" href="checkout?confirmed=true">Place order</a>
			<a class="button" href="checkout">Re-enter information</a>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			$(document).ready(function() {
				$.get("async/cartTable", function(data) {
					$("#cart_contents").html(data);
				});
			});
			
			$(".button").button();
		</script>
	</body>
</html>