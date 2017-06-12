<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Now viewing: ${product.title}"/>
		</jsp:include>
		<div id="content">
			<c:choose>
			<c:when test="${empty product}">
				<p>Could not find product with that ID.</p>
			</c:when>
			<c:when test="${not empty product}">
			<h2>${product.title}</h2>
			<p><img src="<c:url value="/resources/${product.imgPath}"/>"/></p>
			<p id="product_summary">${product.description}</p>
			<table id="product_desc">
				<tr>
					<td class="key">ID/SKU</td>
					<td class="value">${product.productCode}</td>
				</tr>
				<tr>
					<td class="key">Category</td>
					<td class="value">${product.category.categoryName}</td>
				</tr>
				<tr>
					<td class="key">Price (AUD)</td>
					<td class="value">$${product.price}</td>
				</tr>
			</table>
			</c:when>
			</c:choose>
		</div>
		<div id="aside">
			<h3>Order me!</h3>
			<form id="orderform_inner" action="addToCart" method="post" onsubmit="return validateAddToCart();">
				<p>
					<label for="qty">Please send me </label>
					<input type="text" class="qty" id="qty" name="qty" maxlength="3" size="3"/>
					of these things!
				</p>
				<p>
					<input type="hidden" id="product_code" name="product_code" value="${product.productCode}"/>
					<input type="submit" id="add_to_cart" value="Add to Cart" />
				</p>
			</form>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			$("#add_to_cart").button({
				icons: {primary: 'ui-icon-cart'}
			});
		</script>
	</body>
</html>