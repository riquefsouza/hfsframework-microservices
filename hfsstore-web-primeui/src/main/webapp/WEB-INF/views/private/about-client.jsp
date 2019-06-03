<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<t:layout>
<jsp:attribute name="header">
  <title><spring:message code="about.title" /></title>
</jsp:attribute>
<jsp:body>    
	<hr>

	<h1><spring:message code="main.framework" />&nbsp;<spring:message code="main.app.title" /></h1>
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

	
</jsp:body>
</t:layout>
