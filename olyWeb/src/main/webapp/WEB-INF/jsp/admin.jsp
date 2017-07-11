<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Admin page"/>
		</jsp:include>
		<div id="content">
			<h2>Admin page</h2>
			<p>This is the admin page</p>
			<a href="<c:url value="admin/getOutstanding"/>">View outstanding orders</a>
			<br/>
			<form action="<c:url value="admin/updateOrder"/>" method="post" onsubmit="return validateUpdateOrder();">
				<label for="order_number">Or update this order: </label>
				<input type="text" id="order_number" name="order_number"/>
				<input id="updateIndividualSubmit" type="submit" value="Go"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
			<p><button class="button" id="logoutBtn">Log out</button></p>
		</div>
		<%@include file="include/footer.html"%>
	</body>
</html>