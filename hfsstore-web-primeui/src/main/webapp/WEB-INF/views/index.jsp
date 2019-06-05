<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
	<jsp:attribute name="header">
	  <title><spring:message code="main.app.title" /></title>
	</jsp:attribute>
	<jsp:body>
		<p style="padding: 20px;"><spring:message code="main.app.welcome" /></p>
	</jsp:body>	
</t:layout>

