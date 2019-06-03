<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" id="anchorHomePage" th:href="@{'/index.html'}"  
    style="float: left; height: 50px; padding: 5px 5px; font-size: 14px; text-decoration:none">
  	<span><spring:message code="main.framework" /></span><br>
  	<span><spring:message code="main.app.title" /></span>
  </a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navb">
    <ul class="navbar-nav mr-auto">

	  <li class="nav-item dropdown" sec:authorize="hasRole('ADMIN')">
		<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="menu.administrative" /></a>
		<div class="dropdown-menu">
		  <a class="dropdown-item" href="<c:url value="/private/roleView" />"><spring:message code="menu.roles" /></a>
		  <a class="dropdown-item" href="<c:url value="/private/userView" />"><spring:message code="menu.users" /></a> 
		</div>
	  </li>
	  
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
	  		<img class="inverted-image" th:src="@{/img/sair.png}" />
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
					<security:authentication="name">
				</a>
			</span>
		</small>

	</div>
  </div>

</nav>
 <!-- 
<div class="web-content" style="margin:5px 0;">
	<@include file="fragments/alertMessages.jsp" %>
</div>
 -->
<%@include file="fragments/infoUser.jsp" %>

 