<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:layout>
    <jsp:attribute name="header">
      <%@include file="fragments/header.jsp" %>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%@include file="fragments/footer.jsp" %>
    </jsp:attribute>    
    <jsp:body>
    
		<h1>Login 2</h1>
	
		<form name="formLogin" action="login" method="POST">
	
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td>Remember Me:</td>
					<td><input type="checkbox" name="remember-me" /></td>
				</tr>
				<tr>
					<td><input name="submit" type="submit" value="submit" /></td>
				</tr>
			</table>
	
		</form>
		
    </jsp:body>

</t:layout>
