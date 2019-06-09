<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<title>HFS Framework</title>
<meta charset="utf-8" />
<!-- <meta http-equiv="refresh" content="5" />	-->
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes" />

<meta name="URL-AUTH-SERVER" content="${urlAuthServer}"/>
<meta name="URL-RESOURCE-SERVER" content="${urlResourceServer}"/>
<meta name="X-AUTH-TOKEN" content="${authToken}"/>	

<!-- Custom fonts for this template-->
<link href="<c:url value="/vendor/fontawesome-free/css/all.min.css" />" rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="<c:url value="/css/sb-admin-2.min.css" />" rel="stylesheet">
 
<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/vendor/jquery/jquery.min.js" />" ></script>
<script src="<c:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/vendor/jquery-easing/jquery.easing.min.js" />"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/js/sb-admin-2.min.js" />"></script>

<link rel="stylesheet" type="text/css" href="<c:url value="/primeui/themes/omega/theme.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/primeui/site.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/primeui/jquery-ui.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/primeui/perfect-scrollbar.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/primeui/icons/css/font-awesome.css" />" />
<script type="text/javascript" src="<c:url value="/primeui/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/primeui/jquery-ui.js" />"></script>
<script type="text/javascript" src="<c:url value="/primeui/perfect-scrollbar.js" />"></script>

<!-- Dependencies of some widgets -->
<script type="text/javascript" src="<c:url value="/primeui/plugins/plugins-all.js" />"></script>

<!-- Mustache for templating support -->
<script type="text/javascript" src="<c:url value="/primeui/plugins/mustache.min.js" />"></script>

<link rel="stylesheet" type="text/css" href="<c:url value="/primeui/primeui.min.css" />" />
<script type="text/javascript" src="<c:url value="/primeui/primeui.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/primeui/x-tag-core.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/primeui/primeelements.js" />"></script>
	
<link rel="stylesheet" href="<c:url value="/vendor/sidebar/sidebar.css" />" />
<link rel="stylesheet" href="<c:url value="/vendor/sidebar/jquery.mCustomScrollbar.min.css" />" />
<script src="<c:url value="/vendor/popper/popper.min.js" />"></script>
<script src="<c:url value="/vendor/sidebar/jquery.mCustomScrollbar.concat.min.js" />"></script>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/system.css" />" />

<script src="<c:url value="/js/hfsframework/hfsframework-system-util.js" />"></script>
