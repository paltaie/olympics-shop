<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Order Successful"/>
		</jsp:include>
		<div id="content">
			<h2>Order successful!</h2>
			<p>Thanks for placing an order with us! Your order ID is</p>
			<p style="font-size: x-large; color: red; font-weight: bold;">${cart.order.orderNumber}</p>
			<p>Please keep this ID handy, as you can use it for tracking on <a href="oly?action=track">this page</a>.</p>
			<a id="home" href="<c:url value="/"/>">Home</a>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			$("#home").button();
		</script>
	</body>
</html>