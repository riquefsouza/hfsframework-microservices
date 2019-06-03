<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="col-md-3 form-group">		
	<label for="cmbReportType"><spring:message code="panelReport.cmbReportType" /></label>
	<select class="form-control" id="cmbReportType">
	    <c:forEach var="group" items="${listReportType}">
		 	<optgroup label="${group.group}">
		 		<c:forEach var="option" items="${group.types}">
			 		<option value="${option}">${option.description}</option>
				</c:forEach> 		
		    </optgroup>
	    </c:forEach>
	</select>
</div>
<div class="col-md-3 form-group">
	<label class="checkbox-inline" for="forceDownload">
		<input type="checkbox" id="forceDownload">&nbsp;<spring:message code="panelReport.forceDownload" />
	</label>
</div>