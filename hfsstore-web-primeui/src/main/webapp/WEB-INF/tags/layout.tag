<!DOCTYPE html>
<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<html>
<head>
	<jsp:invoke fragment="header" />
</head>
<body>
	<div class="web-content">
		<jsp:doBody />
	</div>
	<jsp:invoke fragment="footer" />
</body>
</html>
