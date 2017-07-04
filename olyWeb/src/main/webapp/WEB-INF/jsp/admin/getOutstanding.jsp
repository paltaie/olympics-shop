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
					<tr>
						<td><a href="<c:url value="updateOrder?order_number=${cart.order.orderNumber}"/>">${cart.order.orderNumber}</a></td>
						<td>${cart.order.customer.surname}</td>
						<td>${cart.order.customer.country}</td>
						<td>${cart.order.customer.postcode}</td>
						<td><fmt:formatNumber currencySymbol="$" value="${cart.getOrderTotal()}" type="currency" maxFractionDigits="2"/></td>
						<td>${cart.order.status}</td>
					</tr>
				</c:forEach>
			</table>
			<p><button id="logoutBtn" class="button">Log out</button><a class="button" href="<c:url value="/admin"/>">Main page</a></p>
		</div>
		<%@include file="../include/footer.html"%>
	</body>
</html>
