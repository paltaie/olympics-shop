<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Shopping Cart"/>
		</jsp:include>
		<div id="content">
			<c:if test="${cartUpdated eq true}">
				<p class="notification">&#10004; Success. Your cart has been updated.</p>
			</c:if>
			<c:if test="${cartCleared eq true}">
				<p class="notification">&#10004; Success. Your cart has been cleared.</p>
			</c:if>
			<h2>Shopping Cart</h2>
				<c:choose>
					<c:when test="${fn:length(cart.orderProducts) eq 0}">
						<p>There are no items in your shopping cart. Head over to the <a href="products">Product list</a> to find and add some!</p>
					</c:when>
					<c:otherwise>
						<c:forEach items="${cart.orderProducts}" var="orderProduct">
							<c:set var="numItems" value="${numItems + (orderProduct.qty)}"/>
						</c:forEach>
						<p>
						You have ${numItems} item<c:if test="${numItems gt 1}">s</c:if> in your cart:</p>
						<form method="post" action="updateCart" onsubmit="return validateUpdateCart();">
							<div id="cart_contents"><img src="resources/images/spinner.gif"/></div>
							<br/>
							<input class="button" type="submit" value="Update Cart"/>
					        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
						<c:forEach items="${cart.orderProducts}" var="orderProduct">
							<c:set var="grandTotal" value="${grandTotal + (orderProduct.product.price * orderProduct.qty)}"/>
						</c:forEach>
						<p>
							<b>Grand total:</b> <span class="red"><fmt:formatNumber currencySymbol="$" value="${grandTotal}" type="currency" maxFractionDigits="2"/></span>
						</p>
					</c:otherwise>
				</c:choose>
				<p>Your current options:</p>
				<a class="button" href="products">Continue shopping</a>
				<c:if test="${fn:length(cart.orderProducts) gt 0}">
					<a class="button" href="clearCart">Clear cart</a>
					<a class="button" href="checkout">Proceed to checkout</a>
				</c:if>
			<p>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			$(document).ready(function() {
				$.get("async/cartTable?src=cart", function(data) {
					$("#cart_contents").html(data);
				});
			});
			
			$(".button").button();
		</script>
	</body>
</html>