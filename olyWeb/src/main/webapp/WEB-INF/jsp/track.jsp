<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Order Tracking"/>
		</jsp:include>
		<div id="content">
			<h2>Order Tracking<c:if test="${surnameMatch eq true}">: ${cart.order.orderNumber}</c:if></h2>
			<c:choose>
				<c:when test="${orderNotFound eq true or surnameMatch eq false}">
					<p id="orderNotFoundOrSurnameMismatch">The details you provided do not match any entry in our system. Please <a href="<c:url value="track"/>">try again</a> or start a new order by looking at our <a href="<c:url value="products"/>">products page</a></p>
				</c:when>
				<c:when test="${empty cart or empty surnameMatch}">
					<form id="order_tracking" action="track" method="post" onsubmit="return validateTrack();">
						<p>
						<label for="order_number">Order Number <span style="color:red;">*</span></label>
						<input type="text" id="order_number" name="order_number"/>
						<br/>
						<label for="surname">Surname</label>
						<input type="text" id="surname" name="surname"/>
						<br/>
						<input type="submit" id="submit_track" value="Search"/>
					    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</p>
					</form>
				</c:when>
				<c:when test="${surnameMatch eq true}">
					<p>Welcome <span>${cart.order.customer.givenName}</span> <span>${cart.order.customer.surname}</span>!</p>
					<p>Your order status is: <span class="red" id="orderStatus">${cart.order.status}</span></p>
					<p>Here's what you ordered:</p>
					<div id="cart_contents"><img src="<c:url value="/resources/images/spinner.gif"/>"/></div>
					<p><b>Grand total:</b> <span class="red" id="grandTotal"><fmt:formatNumber currencySymbol="$" value="${cart.orderTotal}" type="currency" maxFractionDigits="2"/></span></p>
				</c:when>
			</c:choose>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			<c:if test="${surnameMatch eq true}">
				$(document).ready(function() {
					$.get("async/cartTable?order_id=${cart.order.orderId}&src=checkout", function(data) {
						$("#cart_contents").html(data);
					});
				});
			</c:if>
		</script>
	</body>
</html>