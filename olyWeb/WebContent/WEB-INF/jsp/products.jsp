<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Products"/>
		</jsp:include>
		<div id="content">
			<h2>Listing all products <c:if test="${not empty category}">under category "${category}"</c:if></h2>
			<c:if test="${empty products}">
				<i>No products here</i>
			</c:if>
			<table id="product_table">
				<tr>
					<c:forEach items="${products}" var="product" varStatus="status">
						<td><p><img id="product_preview" src="<c:url value="${product.imgPath}"/>"/></p><p><a href="oly?action=product&id=${product.productCode}">${product.title}</a></p></td>
						<c:if test="${status.count mod 4 eq 0}">
							</tr><tr>
						</c:if>
					</c:forEach>
				</tr>
			</table>
		</div>
		<div id="aside">
			<h3>Categories</h3>
			<c:forEach items="${categories}" var="category">
				<a class="category" href="oly?action=products&cat=${category.categoryName}">${category.categoryName}</a><br/>
			</c:forEach>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			$(".category").button({
				icons: {primary:'ui-icon-triangle-1-e'}
			});
		</script>
	</body>
</html>