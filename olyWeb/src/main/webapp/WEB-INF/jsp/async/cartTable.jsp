<%@include file="../include/include.jsp"%>
<table class="lined_table">
	<tr>
		<th>Category</th>
		<th>Code</th>
		<th>Product</th>
		<th>Price</th>
		<th>Quantity</th>
		<th>Total</th>
		<c:if test="${param.src == 'cart'}">
			<th>Remove</th>
		</c:if>
	</tr>
	<c:forEach items="${cart.orderProducts}" var="orderProduct">
		<c:set var="grandTotal" value="${grandTotal + (orderProduct.product.price * orderProduct.qty)}"/>
		<tr>
			<td>${orderProduct.product.category.categoryName}</td>
			<td>${orderProduct.product.productCode}</td>
			<td>${orderProduct.product.title}</td>
			<td><fmt:formatNumber currencySymbol="$" value="${orderProduct.product.price}" type="currency" maxFractionDigits="2"/></td>
			<td>
				<c:choose>
					<c:when test="${param.src == 'cart'}">
						<input type="text" class="qty" name="qty_${orderProduct.product.productCode}" id="qty_${orderProduct.product.productCode}" value="${orderProduct.qty}" maxlength="3" size="3"/>
					</c:when>
					<c:otherwise>
						${orderProduct.qty}
					</c:otherwise>
				</c:choose>
			</td>
			<td><fmt:formatNumber currencySymbol="$" value="${orderProduct.product.price * orderProduct.qty}" type="currency" maxFractionDigits="2"/></td>
			<c:if test="${param.src == 'cart'}">
				<td><input type="checkbox" id="remove_${orderProduct.product.productCode}" name="remove_${orderProduct.product.productCode}"/></td>
			</c:if>
		</tr>
	</c:forEach>
</table>