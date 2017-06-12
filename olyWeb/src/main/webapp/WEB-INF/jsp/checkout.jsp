<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Checkout"/>
		</jsp:include>
		<div id="content">
			<h2>Checkout</h2>
			<p>Here's what you're ordering:</p>
			<div id="cart_contents"><img src="resources/images/spinner.gif"/></div>
			<c:forEach items="${cart.orderProducts}" var="orderProduct">
				<c:set var="grandTotal" value="${grandTotal + (orderProduct.product.price * orderProduct.qty)}"/>
			</c:forEach>
			<p>
				<b>Grand total:</b> <span class="red"><fmt:formatNumber currencySymbol="$" value="${grandTotal}" type="currency" maxFractionDigits="2"/></span>
			</p>
			<p><i>Not correct?</i> <a href="cart">click here</a> to edit your cart.</p>
			<p>If that's right, let us get to know you better!</p>
			<form id="person_info" action="checkout" method="post" onsubmit="return validateCheckout();">
				<label for="first_name">First Name <span style="color:red;">*</span></label>
				<input type="text" name="first_name" id="first_name"><br>
				<label for="last_name">Last Name <span style="color:red;">*</span></label>
				<input type="text" name="last_name" id="last_name"><br>
				<label for="email">Email <span style="color:red;">*</span></label>
				<input type="text" name="email" id="email"><br>
				<label for="house_no">House number <span style="color:red;">*</span></label>
				<input type="text" name="house_no" id="house_no"><br>
				<label for="street">Street <span style="color:red;">*</span></label>
				<input type="text" name="street" id="street"><br>
				<label for="suburb">Suburb/Locality <span style="color:red;">*</span></label>
				<input type="text" name="suburb" id="suburb"><br>
				<label for="state">State/Province </label>
				<input type="text" name="state" id="state"><br>
				<label for="postcode">Postcode</labeL>
				<input type="text" name="postcode" id="postcode"><br>
				<label for="country">Country <span style="color:red;">*</span></label>
				<select id="country" name="country"></select><br>
				<label for="cc">Credit card number <span style="color:red;">*</span></label>
				<input type="text" name="cc" id="cc" maxlength="16"><br>
				<input class="button" type="submit" value="Continue"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			var countryList = null;
			var countries = "";
			
			$(document).ready(function() {
				$.get("async/cartTable?src=checkout", function(data) {
					$("#cart_contents").html(data);
				});
				
				$.getJSON('<c:url value="async/countryList"/>', function(data) {
					countryList = data;
					for (var i = 0; i < countryList.length; i++) {
						countries += "<option value=\"" + countryList[i].value + "\">" + countryList[i].label + "</option>";
					}
					$("#country").html(countries);
				});
			});
			
			$(".button").button();
		</script>
	</body>
</html>