<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

<div th:fragment="becomeMember" id="dlgBecomeMember" title="<spring:message code="dlgBecomeMember.title" />" style="display: none;" >
	    
    <div class="container-fluid" style="margin: 0 auto; width:350px; position: float;">
		
		<div class="form-group">
			<label for="dlgBecomeMember_username">
				<span id="dlgBecomeMember_label_username"><spring:message code="login.username" /></span>
			</label>
			<input type="text" class="form-control" id="dlgBecomeMember_username" placeholder="<spring:message code="login.username.placeholder" />">
		</div>
		
	
		<div class="form-group">
			<label for="dlgBecomeMember_email">
				<span id="dlgBecomeMember_label_email"><spring:message code="dlgBecomeMember.email" /></span>
			</label>
			<input type="email" class="form-control" id="dlgBecomeMember_email">
		</div>
	
		<div class="form-group">
			<label for="dlgBecomeMember_newPassword">
				<span id="dlgBecomeMember_label_newPassword"><spring:message code="login.password" /></span>
			</label>
			<input type="password" class="form-control" id="dlgBecomeMember_newPassword" placeholder="<spring:message code="login.password.placeholder" />" autocomplete="off">
		</div>
	
		<div class="form-group">
			<label for="dlgBecomeMember_confirmNewPassword">
				<span id="dlgBecomeMember_label_confirmNewPassword"><spring:message code="changePassword.confirmNewPassword" /></span>
			</label>
			<input type="password" class="form-control" id="dlgBecomeMember_confirmNewPassword">
		</div>
	
		<div class="form-group">
			<div style="margin: 0 auto; width:40%">
				<button type="button" class="btn btn-primary btn-icon-split" id="dlgBecomeMember_btnSignUp">
					<span class="icon text-white-50">
						<i class="fa fa-sign-in" aria-hidden="true"></i>
					</span>
					<span class="text"><spring:message code="login.becomeMember" /></span>
				</button>
			</div>
		</div>
					
    </div>

	<script src="<c:url value="/js/hfsframework/hfsframework-becomeMember.js" />"></script>
    
</div>

</body>
</html>
