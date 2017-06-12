<%@include file="../include/include.jsp"%>
		<jsp:include page="../include/header.jsp">
			<jsp:param name="title" value="Admin Logout"/>
		</jsp:include>
		<div id="content">
			<h2>Admin logout</h2>
			<p>User <i>${userName}</i> has been successfully logged out.</p>
		</div>
		<%@include file="../include/footer.html"%>
	</body>
</html>