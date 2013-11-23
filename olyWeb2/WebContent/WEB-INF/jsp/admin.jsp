<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Admin page"/>
		</jsp:include>
		<div id="content">
			<h2>Admin page</h2>
			<p>This is the admin page</p>
			<a href="<c:url value="/admin/getOutstanding.oly"/>">View outstanding orders</a>
			<br/>
			<form action="<c:url value="/admin/updateOrder.oly"/>" method="post">
				<label for="order_id">Or update this order: </label>
				<input type="text" id="order_id" name="order_id"/>
				<input type="submit" value="Go"/>
			</form>
			<p><a class="button" href="<c:url value="/admin/logout.oly"/>">Log out</a></p>
		</div>
		<%@include file="include/footer.html"%>
		<script type="text/javascript">
			$(".button").button();
		</script>
	</body>
</html>