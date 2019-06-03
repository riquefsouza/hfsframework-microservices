<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav id="sidebar">
	<div id="dismiss">
		<i class="fas fa-arrow-left"></i>
	</div>

	<div class="sidebar-header">

		<a class="navbar-brand" id="anchorSidebarHomePage"
			href="<c:url value="/index.html" />"
			style="float: left; height: 50px; padding: 5px 5px; font-size: 22px; text-decoration: none; color: white;">
			<span><spring:message code="main.framework" /></span>
		</a>

	</div>
	<br>

	<ul class="list-unstyled components">

		<security:authorize access="hasRole('ROLE_ADMIN')">
			<li class="nav-item"><a href="#adminSubmenu"
				data-toggle="collapse" aria-expanded="false"
				class="nav-link dropdown-toggle" style="color: black;"> <spring:message
						code="menu.administrative" />
			</a>
				<ul class="collapse list-unstyled" id="adminSubmenu">
					<li><a class="dropdown-item"
						href="<c:url value="/private/roleView" />"><spring:message
								code="menu.roles" /></a></li>
					<li><a class="dropdown-item"
						href="<c:url value="/private/userView" />"><spring:message
								code="menu.users" /></a></li>
				</ul></li>
		</security:authorize>

		<li class="nav-item"><a href="#settingsSubmenu"
			data-toggle="collapse" aria-expanded="false"
			class="nav-link dropdown-toggle" style="color: black;"> <spring:message
					code="menu.settings" />
		</a>
			<ul class="collapse list-unstyled" id="settingsSubmenu">
				<li><a class="dropdown-item"
					href="<c:url value="/private/changePassword" />"><spring:message
							code="menu.changePassword" /></a></li>
				<li><a class="dropdown-item"
					href="<c:url value="/private/about" />"><spring:message
							code="menu.about" /></a></li>
			</ul></li>
		<li><a class="dropdown-item" href="<c:url value="/logout" />">
				<img class="inverted-image" src="<c:url value="/img/sair.png" />" />
				<spring:message code="menu.exit" />
		</a></li>

	</ul>

</nav>

<script src="<c:url value="/vendor/sidebar/sidebar.js" />"></script>
