<%@include file="WEB-INF/jsp/include/include.jsp"%>
		<jsp:include page="WEB-INF/jsp/include/header.jsp">
			<jsp:param name="title" value="Olympics Shop home"/>
		</jsp:include>
		<div id="content">
			<h2>Welcome!</h2>
			<p>Welcome to what is probably the best ever Olympics shop you've ever encountered in your entire life. Head over to the <a href="<c:url value="/oly?action=products"/>">All products</a> page in order to start your foray into the best shopping experience of your entire life, and arguably the best this side of the millennium.</p>
			<p>Our company, Bandwagon, Inc. specialises in selling specialised merchandise for whatever it is that might be hot right now. Who can forget our Y2K merchandise clearance sale? Or the forever-ingrained-in-our-minds Torino 2006 Winter Olympics store special? These deals are simply too good to be true (and sort of are).</p>
			<p><a id="all_products" href="<c:url value="/oly?action=products"/>">All products</a></p>
		</div>
		<div id="aside">
			<h3><span style="background-color: yellow;">Documentation</span></h3>
			<p>Feel free to browse the documentation for this assignment <a href="docs/index.html">here</a></p>
		</div>
		<%@include file="WEB-INF/jsp/include/footer.html"%>
		<script type="text/javascript">
			$("#all_products").button({
				icons: {primary:'ui-icon-cart'}
			});
		</script>
	</body>
</html>