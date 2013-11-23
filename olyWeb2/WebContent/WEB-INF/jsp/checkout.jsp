<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Checkout"/>
		</jsp:include>
		<div id="content">
			<h2>Checkout</h2>
			<p>Here's what you're ordering:</p>
			<div id="cart_contents"><img src="images/spinner.gif"/></div>
			<p><i>Not correct?</i> <a href="cart.oly">click here</a> to edit your cart.</p>
			<p>If that's right, let us get to know you better!</p>
			<form id="person_info" action="checkout.oly" method="post" onsubmit="return validateCheckout();">
				<label for="givenName">First Name <span style="color:red;">*</span></label>
				<input type="text" name="givenName" id="givenName"><br>
				<label for="surname">Last Name <span style="color:red;">*</span></label>
				<input type="text" name="surname" id="surname"><br>
				<label for="email">Email <span style="color:red;">*</span></label>
				<input type="text" name="email" id="email"><br>
				<label for="houseNo">House number <span style="color:red;">*</span></label>
				<input type="text" name="houseNo" id="houseNo"><br>
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
				<label for="ccNumber">Credit card number <span style="color:red;">*</span></label>
				<input type="text" name="ccNumber" id="ccNumber" maxlength="16"><br>
				<input class="button" type="submit" value="Continue"/>
			</form>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			var countryList = null;
			var countries = "";
			
			$(document).ready(function() {
				$.get("async/cartTable.oly?src=checkout", function(data) {
					$("#cart_contents").html(data);
				});
				
				$.getJSON('resources/json/countryList.json', function(data) {
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