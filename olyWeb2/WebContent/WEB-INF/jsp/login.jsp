<%@include file="include/include.jsp"%>
		<jsp:include page="include/header.jsp">
			<jsp:param name="title" value="Admin Login"/>
		</jsp:include>
		<div id="content">
			<h2>Admin login</h2>
			<form id="login_form" action="j_spring_security_check" method="post">
				<p>
					<label for="j_username">Username</label>
					<input id="j_username" name="j_username" type="text" />
					<br/>
					<label for="j_password">Password</label>
					<input id="j_password" name="j_password" type="password" />
					<br/>
					<input type="submit" value="Login" />
				</p>
			</form>
		</div>
		<%@include file="include/footer.html"%>
	</body>
</html>