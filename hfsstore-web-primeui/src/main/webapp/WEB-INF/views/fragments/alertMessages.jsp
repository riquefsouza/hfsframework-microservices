<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

	<span id="message-select-table" style="display: none"><spring:message code="message.select.table" /></span>
	
	<span id="message-alert-success" style="display: none"><spring:message code="alert.success" /></span>
	<span id="message-alert-danger" style="display: none"><spring:message code="alert.danger" /></span>
	<span id="message-alert-warning" style="display: none"><spring:message code="alert.warning" /></span>
	<span id="message-alert-info" style="display: none"><spring:message code="alert.info" /></span>
	
	<span id="message-button-yes" style="display: none"><spring:message code="button.yes" /></span>
	<span id="message-button-no" style="display: none"><spring:message code="button.no" /></span>
	<span id="validator-emptyStringValidator" style="display: none"><spring:message code="validator.EmptyStringValidator" /></span>
	

	<div class="alert alert-primary alert-dismissible fade show" id="alert-primary" style="display: none">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong><span id="text-alert-primary"></span></strong>
	</div>
	<div class="alert alert-secondary alert-dismissible fade show" id="alert-secondary" style="display: none">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong><span id="text-alert-secondary"></span></strong>
	</div>
	<div class="alert alert-success alert-dismissible fade show" id="alert-success" style="display: none">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong><span id="text-alert-success"></span></strong>
	</div>
	<div class="alert alert-danger alert-dismissible fade show" id="alert-danger" style="display: none">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong><span id="text-alert-danger"></span></strong>
	</div>
	<div class="alert alert-warning alert-dismissible fade show" id="alert-warning" style="display: none">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong><span id="text-alert-warning"></span></strong>
	</div>
	<div class="alert alert-info alert-dismissible fade show" id="alert-info" style="display: none">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong><span id="text-alert-info"></span></strong>
	</div>
	<div class="alert alert-light alert-dismissible fade show" id="alert-light" style="display: none">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong><span id="text-alert-light"></span></strong>
	</div>
	<div class="alert alert-dark alert-dismissible fade show" id="alert-dark" style="display: none">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong><span id="text-alert-dark"></span></strong>
	</div>
	
	
	<c:if test="${not empty primaryMessage}" >
		<div class="alert alert-primary alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span><spring:message code="primaryMessage" /></span></strong>
		</div>
	</c:if>		
	<c:if test="${not empty secondaryMessage}" >
		<div class="alert alert-secondary alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span><spring:message code="secondaryMessage" /></span></strong>
		</div>	
	</c:if>		
	<c:if test="${not empty successMessage}" >
		<div class="alert alert-success alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span><spring:message code="successMessage" /></span></strong>
		</div>
	</c:if>		
	<c:if test="${not empty dangerMessage}" >
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span><spring:message code="dangerMessage" /></span></strong>
		</div>
	</c:if>		
	<c:if test="${not empty warningMessage}" >
		<div class="alert alert-warning alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span><spring:message code="warningMessage" /></span></strong>
		</div>
	</c:if>		
	<c:if test="${not empty infoMessage}" >
		<div class="alert alert-info alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span><spring:message code="infoMessage" /></span></strong>
		</div>
	</c:if>		
	<c:if test="${not empty lightMessage}" >
		<div class="alert alert-light alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span><spring:message code="lightMessage" /></span></strong>
		</div>
	</c:if>
	<c:if test="${not empty darkMessage}" >
		<div class="alert alert-dark alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><span><spring:message code="darkMessage" /></span></strong>
		</div>
	</c:if>
	
	<div id="dlgAlertMessage" title="<spring:message code="dlgAlertMessage.title" />" style="display: none;">
	    <p><span id="dlgAlertMessage-text" ><spring:message code="dlgAlertMessage.text" /></span></p>
	</div>
	
</body>
</html>
