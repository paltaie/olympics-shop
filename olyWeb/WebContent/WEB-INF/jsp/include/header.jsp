<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<link href="<c:url value="/style/main.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/style/custom-theme/jquery-ui-1.8.23.custom.css"/>" rel="stylesheet" type="text/css"/>
		<link id="page_favicon" href="<c:url value="/favicon.ico"/>" rel="icon" type="image/x-icon" />
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
		<script type="text/javascript" src="<c:url value="/js/validate.js"/>"></script>
		<title>${param.title}</title>
	</head>
	<body>
		<script type="text/javascript">
			$(document).ready(function() {
				$.getJSON("<c:url value="/async"/>?type=cartSize", function(data) {
					var cartSizeString = "(" + data.cartSize + " item";
					if (data.cartSize != 1) {
						cartSizeString += "s";
					}
					cartSizeString += ")";
					$("#cart_size").text(cartSizeString);
				});
			});
		</script>
		<div id="head-container">
			<div id="header">
				<h1>Olympics Shop</h1>
			</div>
		</div>
		<div id="navigation-container">
			<div id="navigation">
				<ul>
					<li><a href="<c:url value="/"/>">Home</a></li>
					<li><a href="<c:url value="/oly?action=products"/>">Products</a></li>
					<li><a href="<c:url value="/oly?action=cart"/>">Cart <span id="cart_size"></span></a></li>
					<li><a href="<c:url value="/oly?action=track"/>">Track Order</a></li>
					<li><a href="<c:url value="/oly/admin?action=admin"/>">Admin</a></li>
				</ul>
			</div>
		</div>
				<div id="content-container">
					<div id="content-container2">
						<div id="content-container3">