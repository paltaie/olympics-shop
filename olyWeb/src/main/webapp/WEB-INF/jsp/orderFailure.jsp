<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Error"/>
		</jsp:include>
		<div id="content">
			<h2>Internal Server Error</h2>
			<p>There was a problem submitting your request. Your cart has not been lost. Please <a href="<c:url value="checkout"/>">click here</a> to try the checkout process again</p>
		</div>
		<%@include file="include/footer.html"%>
	</body>
</html>