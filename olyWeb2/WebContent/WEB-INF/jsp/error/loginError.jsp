<%@include file="../include/include.jsp"%>
		<jsp:include page="../include/header.jsp">
			<jsp:param name="title" value="Login Failed"/>
		</jsp:include>
		<div id="content">
			<h2 style="color: red;">Login failure</h2>
			<p>Login failed. Please check your credentials and try again.</p>
		</div>
		<%@include file="../include/footer.html"%>
	</body>
</html>