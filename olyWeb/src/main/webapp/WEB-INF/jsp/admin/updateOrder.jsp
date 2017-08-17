<%@include file="../include/include.jsp"%>
		<jsp:include page="../include/header.jsp">
			<jsp:param name="title" value="Update Order"/>
		</jsp:include>
		<div id="content">
			<c:choose>
				<c:when test="${not empty cart.order.orderId}">
					<c:if test="${statusUpdated eq true}">
						<p class="notification">&#10004; Success. New status for order ${cart.order.orderNumber} is now ${cart.order.status}.</p>
					</c:if>
					<h2>Updating Order: ${cart.order.orderNumber}</h2>
					<form action="<c:url value="updateOrder"/>" method="post">
					<input type="hidden" name="order_number" value="${cart.order.orderNumber}"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<ul>
						<li>Name: ${cart.order.customer.givenName}&nbsp;${cart.order.customer.surname}</li>
						<li>Email: ${cart.order.customer.email}</li>
						<li>Address: <span>${cart.order.customer.houseNo}</span> ${cart.order.customer.street}, ${cart.order.customer.suburb}, ${cart.order.customer.state}, ${cart.order.customer.postcode}, ${cart.order.customer.country}</li>
						<li>Credit card: XXXX-XXXX-XXXX-${fn:substring(cart.order.customer.ccNumber, fn:length(cart.order.customer.ccNumber)-4, fn:length(cart.order.customer.ccNumber))}</li>
						<li>Status:
							<select name="new_status" id="new_status">
								<option value="ORDERED" <c:if test="${cart.order.status == 'ORDERED'}">selected = "selected"</c:if>>ORDERED</option>
								<option value="PAID" <c:if test="${cart.order.status == 'PAID'}">selected = "selected"</c:if>>PAID</option>
								<option value="SENT" <c:if test="${cart.order.status == 'SENT'}">selected = "selected"</c:if>>SENT</option>
							</select>
							<input id="updateOrder" type="submit" value="Update Order"/>
						</li>
					</ul>
					</form>
					<div id="cart_contents"><img src="<c:url value="/resources/images/spinner.gif"/>"/></div>
						<p>
							<b>Grand total:</b> <span class="red"><fmt:formatNumber currencySymbol="$" value="${cart.orderTotal}" type="currency" maxFractionDigits="2"/></span>
						</p>
						<p>
							<a class="button" href="<c:url value="/admin/getOutstanding"/>">Outstanding orders</a>
							<a class="button" href="<c:url value="/admin"/>">Admin page</a>
							<button class="button" id="logoutBtn">Log out</button>
						</p>
				</c:when>
				<c:otherwise>
					<h2>Order Not Found</h2>
					<p>Can't find that order. Go back to the <a href="admin">main page</a> and try again</p>
				</c:otherwise>
			</c:choose>
		</div>
		<%@include file="../include/footer.html"%>
		<script type="text/javascript">
			$(".button").button();
			$(document).ready(function() {
				$.get("../async/cartTable?order_id=${cart.order.orderId}&src=checkout", function(data) {
					$("#cart_contents").html(data);
				});
			});
		</script>
	</body>
</html>