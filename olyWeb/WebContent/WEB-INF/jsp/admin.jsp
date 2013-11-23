<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Admin page"/>
		</jsp:include>
		<div id="content">
			<h2>Admin page</h2>
			<p>This is the admin page</p>
			<a href="<c:url value="/oly/admin?action=getOutstanding"/>">View outstanding orders</a>
			<br/>
			<form action="<c:url value="/oly/admin?action=updateOrder"/>" method="post" onsubmit="return validateUpdateOrder();">
				<label for="order_number">Or update this order: </label>
				<input type="text" id="order_number" name="order_number"/>
				<input type="submit" value="Go"/>
			</form>
			<p><a class="button" href="<c:url value="/oly/admin?action=logout"/>">Log out</a></p>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			$(".button").button();
		</script>
	</body>
</html>