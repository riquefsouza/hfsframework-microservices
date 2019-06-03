<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form id="infoUserForm">

	<div id="infoUserDialog" class="modal fade" role="dialog">
	  <div class="modal-dialog modal-sm">

		<div class="modal-content">
		  <div class="modal-header">			
			<h4 class="modal-title"><spring:message code="infoUser.title" /></h4>
			<button type="button" class="close" data-dismiss="modal">&times;</button>
		  </div>
		  <div class="modal-body">

			<table class="table table-bordered table-condensed">
				<tbody>
					<tr>
						<td style="font-weight: bold;"><spring:message code="login.username" /></td>
						<td class="infoUser-username"><c:out value="${sessionScope.userLogged.username}"/></td>
					</tr>
					<tr>
						<td style="font-weight: bold;"><spring:message code="infoUser.email" /></td>
						<td class="infoUser-email"><c:out value="${sessionScope.userLogged}"/></td>
					</tr>
					<tr>
						<td style="font-weight: bold;"><spring:message code="infoUser.roles" /></td>
						<td class="infoUser-roles" ><c:out value="${sessionScope.userLogged.authorities}"/></td>
					</tr>
				</tbody>
			</table>

		  
		  </div>
		</div>

	  </div>
	</div>

</form>

