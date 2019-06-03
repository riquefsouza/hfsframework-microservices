<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><spring:message code="accessDenied.title" /></title>
</head>
<body>

<div class="web-content">
	<h2><spring:message code="accessDenied.text" /></h2>	
	<a href="<c:url value="/index.html" />"><spring:message code="accessDenied.backHome" /></a>
</div>

</body>
</html>
