<!DOCTYPE html>
<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:invoke fragment="header" />
	<jsp:include page="/WEB-INF/views/fragments/header.jsp" />
</head>
<body>
    <div class="wrapper">
		<jsp:include page="/WEB-INF/views/private/menu-sidebar.jsp" />
        <div id="content">
			<jsp:include page="/WEB-INF/views/private/menu-horizontal.jsp" />
            <jsp:doBody />	            
        </div>
    </div>

    <div class="overlay"></div>
	
</body>
</html>
