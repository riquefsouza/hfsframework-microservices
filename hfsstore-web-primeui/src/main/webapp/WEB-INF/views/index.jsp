<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <jsp:attribute name="header">
      <%@include file="fragments/header.jsp" %>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%@include file="fragments/footer.jsp" %>
    </jsp:attribute>    
    <jsp:body>
	
	    <div class="wrapper">
	        <!-- Sidebar  -->
	        <nav id="sidebar">
	            <div id="dismiss">
	                <i class="fas fa-arrow-left"></i>
	            </div>
	
	            <div class="sidebar-header">
                  <a class="navbar-brand" id="anchorHomePage" href="<c:url value="/index.html" />" 
				    style="float: left; height: 50px; padding: 5px 5px; font-size: 14px; text-decoration:none; color: white; ">
				  	<span><spring:message code="main.framework" /></span><br>
				  	<span><spring:message code="main.app.title" /></span><br>
				  </a>
	            </div>
	            <br>
	
				<ul class="list-unstyled components">

				   <security:authorize access="hasRole('ROLE_ADMIN')">
					  <li class="nav-item">
						<a href="#adminSubmenu" data-toggle="collapse" aria-expanded="false" class="nav-link dropdown-toggle">
							<spring:message code="menu.administrative" />
						</a>
						<ul class="collapse list-unstyled" id="adminSubmenu">
						  <li><a class="dropdown-item" href="<c:url value="/private/roleView" />"><spring:message code="menu.roles" /></a></li>
						  <li><a class="dropdown-item" href="<c:url value="/private/userView" />"><spring:message code="menu.users" /></a></li> 
						</ul>
					  </li>
				  </security:authorize>
				  
				  <li class="nav-item">
					<a href="#settingsSubmenu" data-toggle="collapse" aria-expanded="false" class="nav-link dropdown-toggle">
						<spring:message code="menu.settings" />
					</a>
					<ul class="collapse list-unstyled" id="settingsSubmenu">
					  <li><a class="dropdown-item" href="<c:url value="/private/changePassword" />"><spring:message code="menu.changePassword" /></a></li>
					  <li><a class="dropdown-item" href="<c:url value="/private/about" />"><spring:message code="menu.about" /></a></li>
					  <li><a class="dropdown-item" href="<c:url value="/public/ldap/list" />">Ldap</a></li>
					</ul>
				  </li>	  
				  <li>
				  	<a class="dropdown-item" href="<c:url value="/logout" />">
				  		<img class="inverted-image" src="<c:url value="/img/sair.png" />" />
				  		<spring:message code="menu.exit" />
				  	</a>
				  </li>
				  	
			    </ul>
    	
	        </nav>
	
	        <!-- Page Content  -->
	        <div id="content">
	
	            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	                <div class="container-fluid">
	
	                    <button type="button" id="sidebarCollapse" class="btn btn-info">
	                        <i class="fas fa-align-left"></i>
	                    </button>
	                    <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	                        <i class="fas fa-align-justify"></i>
	                    </button>

	                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	                        <ul class="nav navbar-nav mr-auto">

							  <li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="menu.settings" /></a>
								<div class="dropdown-menu">
								  <a class="dropdown-item" href="<c:url value="/private/changePassword" />"><spring:message code="menu.changePassword" /></a>
								  <a class="dropdown-item" href="<c:url value="/private/about" />"><spring:message code="menu.about" /></a>
								  <a class="dropdown-item" href="<c:url value="/public/ldap/list" />">Ldap</a>
								</div>
							  </li>	  
							  <li class="nav-item">
							  	<a class="nav-link" href="<c:url value="/logout" />">
							  		<img class="inverted-image" src="<c:url value="/img/sair.png" />">
							  		<spring:message code="menu.exit" />
							  	</a>
							  </li>
							  
	                        </ul>
	                        
	                        
	                        	<div class="form-inline my-2 my-lg-0">

									<small>
										<span><spring:message code="lang.change" /></span>:
										<select id="locales">
										    <option value=""></option>
										    <option value="en_US" ><spring:message code="lang.en_US" /></option>
										    <option value="pt_BR" ><spring:message code="lang.pt_BR" /></option>
										</select>	
									 
										<span id="infos-user" style="color: lightgray"> 				
											<i class="fas fa-user fa-sm"></i> 
											<a data-toggle="modal" data-target="#infoUserDialog" style="cursor: pointer; cursor: hand; text-decoration: none;">
												<security:authorize access="isAuthenticated()">
												    <security:authentication property="principal.username" /> 
												</security:authorize>
											</a>
										</span>
									</small>
							
								</div>
	                        
	                    </div>
	                </div>
	            </nav>
	
	            <p style="padding: 20px;"><spring:message code="main.app.welcome" /></p>
	            
	        </div>
	    </div>
	
	    <div class="overlay"></div>

	    <script src="<c:url value="/vendor/sidebar/sidebar.js" />"></script>

    </jsp:body>

</t:layout>

