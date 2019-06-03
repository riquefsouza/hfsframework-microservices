<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>	
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:layout>
	<jsp:attribute name="header">
	  <title><spring:message code="main.framework" /></title>
	</jsp:attribute>
	<jsp:body>    
	    <c:if test="${not empty loginError}" >
	    	<div class="alert alert-danger alert-dismissible">
	  			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	  			<spring:message code="login.wrongUser" />
			</div>    
	    </c:if>
	    
		<div class="container-fluid" style="margin: 0 auto; width:400px; position: float;">
			<div id="tela-login" style="margin-top: 20px;">
				<div class="card" id="tela-login">
					<div class="card-header" style="font-weight: normal;"><spring:message code="login.title" /></div>
					<div class="card-body">
					
						<form name="formLogin" action="login" method="POST">
									
							<div class="form-group">
								<label for="username"><spring:message code="login.username" /></label>
								<div class="input-group">
									<div class="input-group-append">
										<button class="btn btn-primary" type="button" disabled="disabled">
											<i class="fas fa-user fa-sm"></i>
										</button>
									</div>	
									<input type="text" class="form-control" name="username" id="username" autofocus required="required" 
										placeholder="<spring:message code="login.username.placeholder" />" />
								</div>
							</div>
							<div class="form-group">
								<label for="password"><spring:message code="login.password" /></label>
								<div class="input-group">
									<div class="input-group-append">
										<button class="btn btn-primary" type="button" disabled="disabled">
											<i class="fas fa-lock fa-sm"></i>
										</button>	
									</div>	
									<input type="password" class="form-control" name="password" id="password" required="required"
										placeholder="<spring:message code="login.password.placeholder" />" autocomplete="off" />
								</div>
							</div>
							<div class="form-group">
								<label for="remember-me"><spring:message code="login.rememberme" /></label>						
								<input type="checkbox" name="remember-me" id="remember-me" />
							</div>
							<div class="form-group">
								<div style="margin: 0 auto; width:40%">
									<button type="submit" class="btn btn-success btn-icon-split" id="btnLogin">
										<span class="icon text-white-50">
											<i class="fa fa-sign-in" aria-hidden="true"></i>
										</span>
										<span class="text"><spring:message code="login.button" /></span>
									</button>
								</div>
							</div>
						
						</form>	
						
					</div>
					<div class="card-footer">
						<a id="anchorForgotPassword" href="#" style="float: left;"><span class="text"><spring:message code="login.forgotPassword" /></span></a>
						
						<button type="button" class="btn btn-primary btn-icon-split" id="btnBecomeMember" style="float: right;">
							<span class="text"><spring:message code="login.becomeMember" /></span>								
						</button>
				    </div>						
				</div>
			</div>	
		</div>
	
		<script src="<c:url value="/js/hfsframework/hfsframework-login.js" />"></script>
	
		<%@include file="fragments/becomeMember.jsp"%>
		
		<%@include file="fragments/forgotPassword.jsp"%>
	
    </jsp:body>

</t:layout>
