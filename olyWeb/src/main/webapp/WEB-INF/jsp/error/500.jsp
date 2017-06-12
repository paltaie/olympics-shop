<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<jsp:directive.page isErrorPage="true"/>
		<jsp:include page="/WEB-INF/jsp/include/header.jsp">
			<jsp:param name="title" value="Error"/>
		</jsp:include>
		<div id="content">
			<h2>Internal Server Error</h2>
			<p>We've encountered a problem on our side. 
			Don't worry, no information was lost (or given to scary pirate hackers with flamethrowers for hands). 
			Please go to the <a href="index.jsp">main page</a> and try again.</p>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/footer.html"/>
	</body>
</html>