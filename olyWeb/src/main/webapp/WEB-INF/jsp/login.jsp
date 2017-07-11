<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Admin Login"/>
		</jsp:include>
		<div id="content">
			<h2>Admin login</h2>
			<form id="login_form" action="j_spring_security_check" method="post">
				<p>
					<label for="username">Username</label>
					<input id="username" name="username" type="text" />
					<br/>
					<label for="password">Password</label>
					<input id="password" name="password" type="password" />
					<br/>
					<input type="submit" id="login" value="Login" />
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</p>
			</form>
		</div>
		<%@include file="include/footer.html"%>
	</body>
</html>