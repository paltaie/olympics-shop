<%@include file="../include/include.jsp"%>
<%@page isErrorPage="true" %>
		<jsp:include page="../include/header.jsp">
			<jsp:param name="title" value="Error"/>
		</jsp:include>
		<div id="content">
			<h2>File not found</h2>
			<p>The page you've tried to access can't be found. Please go to the <a href="index.jsp">main page</a> and try again.</p>
		</div>
		<%@include file="../include/footer.html"%>
	</body>
</html>