<%@include file="../include/include.jsp"%>
<jsp:directive.page isErrorPage="true"/>
		<jsp:include page="../include/header.jsp">
			<jsp:param name="title" value="Error"/>
		</jsp:include>
		<div id="content">
			<h2>Internal Server Error</h2>
			<p>We've encountered a problem on our side. Don't worry, no information was lost (or given to scary pirate hackers with flamethrowers for hands). Please go to the <a href="index.jsp">main page</a> and try again.</p>
		</div>
		<%@include file="../include/footer.html"%>
		<script type="text/javascript">
			$("#error_handle").click(function() {
				$("#error").toggle();
			});
		</script>
	</body>
</html>