<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Spring Base Project</title>

<link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/font-awesome.min.css'/>" rel="stylesheet">
<link href="<c:url value='/static/css/theme.css'/>" rel="stylesheet">
<link href="<c:url value='/static/css/style.css'/>" rel="stylesheet">

<script type="text/javascript" src="<c:url value='/static/js/jquery-1.11.1.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/bootstrap.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/form-validation.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/validation-util.js' />"></script>

</head>

<body class=" theme-blue">

	<style type="text/css">
.navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
	color: #fff;
}
</style>

	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<span class="navbar-brand"><span class="fa fa-cubes"></span>&nbsp;BASE PROJECT</span>
		</div>
		<div class="navbar-collapse collapse" style="height: 1px;">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/cadastro"/>">CADASTRO</a></li>
				<li><a href="<c:url value="/login"/>">LOGIN</a></li>
			</ul>
<!-- 			<ul class="nav navbar-nav navbar-right"> -->
<%-- 				<li><a href="<c:url value="/console"/>"><i class="fa fa-database"></i>&nbsp;DATABASE</a></li> --%>
<!-- 			</ul> -->
		</div>
	</div>

	<c:if test="${error != null}">
		<div class="alert alert-danger" role="alert" style="text-transform: uppercase;">
			<b><i class="fa fa-info-circle"></i>&nbsp;</i>${error}</b>
		</div>
	</c:if>

	<c:if test="${msg != null}">
		<div class="alert alert-info" role="alert" style="text-transform: uppercase;">
			<b><i class="fa fa-info-circle"></i>&nbsp;</i>${msg}</b>
		</div>
	</c:if>