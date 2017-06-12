<%@include file="../include/include.jsp"%>
		<jsp:include page="../include/header.jsp">
			<jsp:param name="title" value="Outstanding Orders"/>
		</jsp:include>
		<div id="content">
			<h2>Outstanding Orders</h2>
			<table class="lined_table">
				<tr>
					<th>Order ID</th>
					<th>Surname</th>
					<th>Country</th>
					<th>Postcode</th>
					<th>Order Total</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${carts}" var="cart">
					<c:set var="orderTotal" value="0"/>
					<c:forEach items="${cart.orderProducts}" var="orderProduct">
						<c:set var="orderTotal" value="${orderTotal + (orderProduct.product.price * orderProduct.qty)}"/>
					</c:forEach>
					<tr>
						<td><a href="<c:url value="updateOrder&order_number=${cart.order.orderNumber}"/>">${cart.order.orderNumber}</a></td>
						<td>${cart.order.customer.surname}</td>
						<td>${cart.order.customer.country}</td>
						<td>${cart.order.customer.postcode}</td>
						<td><fmt:formatNumber currencySymbol="$" value="${orderTotal}" type="currency" maxFractionDigits="2"/></td>
						<td>${cart.order.status}</td>
					</tr>
				</c:forEach>
			</table>
			<p><a class="button" href="<c:url value="logout"/>">Log out</a><a class="button" href="<c:url value="admin"/>">Main page</a></p>
		</div>
		<%@include file="../include/footer.html"%>
		<script type="text/javascript">
			$(".button").button();
		</script>
	</body>
</html>
