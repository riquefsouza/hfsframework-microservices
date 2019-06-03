<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<title><spring:message code="about.title" /></title>
</head>
<body>

<div class="web-content">
	<hr>

	<h1>HFS Framework OAuth Authorization Client Web</h1>
	<br>
	<h2><spring:message code="about.developedBy" />&nbsp;Henrique Figueiredo de Souza</h2>
	<br>
	<h2>
		<spring:message code="about.version" />&nbsp;
		<spring:message code="build.version" />&nbsp;
		<spring:message code="build.timestamp" />
	</h2>
	<br>

	<hr>
		
	<p style="padding: 20px;">
		<spring:message code="main.app.welcome" />
	</p>
	
	<h1>This is the homepage for the user</h1>

	<security:authorize access="hasRole('ROLE_USER')">
		This text is only visible to a user
		<br />
	</security:authorize>

	<security:authorize access="hasRole('ROLE_ADMIN')">
		This text is only visible to an admin
		<br />
	</security:authorize>

	<a href="<c:url value="/logout" />">Logout</a>
	
</div>
	
</body>
</html>
