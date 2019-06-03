<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

<div th:fragment="forgotPassword" id="dlgForgotPassword" title="<spring:message code="login.forgotPassword" />" style="display: none;">

	<div class="container-fluid" style="margin: 0 auto; width:350px; position: float;">
		    
		<div class="form-group">
			<label for="dlgForgotPassword_username">
				<span id="dlgForgotPassword_label_username"><spring:message code="login.username" /></span>
			</label>
			<input type="text" class="form-control" id="dlgForgotPassword_username" placeholder="<spring:message code="login.username.placeholder" />">
		</div>

		<div class="form-group">
			<div style="margin: 0 auto; width:40%">
				<button type="button" class="btn btn-success btn-icon-split" id="dlgForgotPassword_btnSend">
					<span class="icon text-white-50">
						<i class="fa fa-sign-in" aria-hidden="true"></i>
					</span>
					<span class="text"><spring:message code="button.send" /></span>
				</button>
			</div>
		</div>
	
	</div>
	
	<script src="<c:url value="/js/hfsframework/hfsframework-forgotPassword.js" />"></script>
	    
</div>

</body>
</html>
