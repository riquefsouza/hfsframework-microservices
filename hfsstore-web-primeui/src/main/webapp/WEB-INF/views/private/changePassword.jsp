<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

<%@include file="private/menu.jsp" %>

<div class="web-content">
	<form:form id="formChangePassword" style="max-width: 650px; margin-top: 10px;"
	 action="<c:url value="/private/changePassword" />" method="POST" modelAttribute="user">
	 
		<div class="card">
			<div class="card-header" style="font-weight: bold;font-size: large;">
				<spring:message code="changePassword.title" />
			</div>
			<div class="card-body">
			
				<div class="form-actions">
					<button type="button" class="btn btn-success btn-icon-split" id="btnSave">
						<span class="icon text-white-50">
							<i class="fa fa-check-circle"></i>
						</span>
						<span class="text"><spring:message code="button.save" /></span>
					</button>
								
					<button type="reset" class="btn btn-light btn-icon-split" id="btnReset">
	                    <span class="icon text-gray-600">
	                      <i class="fa fa-eraser"></i>
	                    </span>
						<span class="text"><spring:message code="button.reset" /></span>
					</button>

					<button type="button" class="btn btn-primary btn-icon-split" id="btnCancel">
						<span class="icon text-white-50"> <i
							class="fa fa-times-circle"></i>
						</span> 
						<span class="text"><spring:message code="button.cancel" /></span>
					</button>
				</div>

				<div class="row">
					<div class="col-md-6 form-group">
					
						<form:input type="hidden" path="id" />
						<form:input type="hidden" path="username" />
						<form:input type="hidden" path="password" />
						<form:input type="hidden" path="email" />
						<form:input type="hidden" path="urlPhoto" />
					
						<form:label for="currentPassword" path="currentPassword"><spring:message code="changePassword.currentPassword" /></form:label>
						<form:input type="password" class="form-control" id="currentPassword" required="required" maxlength="64" autocomplete="off" path="currentPassword" />
						<form:errors cssStyle="color: red;" path="currentPassword" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<form:label for="newPassword"><spring:message code="changePassword.newPassword" /></form:label>
						<form:input type="password" class="form-control" id="newPassword" required="required" maxlength="64" autocomplete="off" path="newPassword" />
						<form:errors cssStyle="color: red;" path="newPassword" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<form:label for="confirmNewPassword"><spring:message code="changePassword.confirmNewPassword" /></form:label>
						<form:input type="password" class="form-control" id="confirmNewPassword" required="required" maxlength="64" autocomplete="off" path="confirmNewPassword" />
						<form:errors cssStyle="color: red;" path="confirmNewPassword" />
					</div>
				</div>
				
			</div>
		</div>
		
		<div id="dlgChangePassword" title="<spring:message code="dlgChangePassword.title" />" style="display: none;">
		    <p><spring:message code="changePassword.dlgChangePassword" /></p>
		</div>
		
	</form:form>
</div>

<script src="<c:url value="/js/hfsframework/hfsframework-changePassword.js" />"></script>

</body>
</html>
