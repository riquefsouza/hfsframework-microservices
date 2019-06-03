<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

	<security:authorize access="isAuthenticated()">
		<button type="button" id="sidebarCollapse" class="btn btn-info">
			<i class="fas fa-align-left"></i>
		</button>
		<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
			type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-align-justify"></i>
		</button>
	</security:authorize>

	<div class="container-fluid">

		<a class="navbar-brand" id="anchorHomePage"
			href="<c:url value="/index.html" />"> <span><spring:message
					code="main.app.title" /></span>
		</a>

		<security:authorize access="isAuthenticated()">

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="nav navbar-nav mr-auto">
	
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"><spring:message
								code="menu.settings" /></a>
						<div class="dropdown-menu">
							<a class="dropdown-item"
								href="<c:url value="/private/changePassword" />"><spring:message
									code="menu.changePassword" /></a> 
							<a class="dropdown-item"
								href="<c:url value="/private/about" />"><spring:message
									code="menu.about" /></a> 
						</div></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/logout" />"> <img class="inverted-image"
							src="<c:url value="/img/sair.png" />"> <spring:message
								code="menu.exit" />
					</a></li>
	
				</ul>
	
	
				<div class="form-inline my-2 my-lg-0">
	
					<small> <span><spring:message code="lang.change" /></span>:
						<select id="locales">
							<option value=""></option>
							<option value="en_US"><spring:message code="lang.en_US" /></option>
							<option value="pt_BR"><spring:message code="lang.pt_BR" /></option>
					</select> <span id="infos-user" style="color: lightgray"> <i
							class="fas fa-user fa-sm"></i> <a data-toggle="modal"
							data-target="#infoUserDialog"
							style="cursor: pointer; cursor: hand; text-decoration: none;">
									<security:authentication property="principal.username" />
								
						</a>
					</span>
					</small>
	
				</div>
	
			</div>
		
		</security:authorize>
		
	</div>
</nav>

<div class="web-content" style="margin: 5px 0;">
	<%@include file="../fragments/alertMessages.jsp"%>
</div>

<%@include file="../fragments/infoUser.jsp"%>

