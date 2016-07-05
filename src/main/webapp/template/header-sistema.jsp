<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="pt">

<head>
    <meta charset="utf-8">
    <title>Spring Base Project</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="<c:url value='/static/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/static/css/font-awesome.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/static/css/theme.css'/>" rel="stylesheet">
	<link href="<c:url value='/static/css/style.css'/>" rel="stylesheet">
	
	<script type="text/javascript" src="<c:url value='/static/js/jquery-1.11.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/static/js/bootstrap.min.js'/>"></script>
	
	<script type="text/javascript" src="<c:url value='/static/js/app-form-validation.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/validation-util.js' />"></script>
</head>

<body class=" theme-blue">

    <style scoped>
        .navbar-default .navbar-brand,
        .navbar-default .navbar-brand:hover {
            color: #fff;
        }
    </style>

    <!-- HEADER -->
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="" href="index.html"><span class="navbar-brand"><span class="fa fa-cubes"></span>&nbsp;BASE PROJECT</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;">
			<ul id="main-menu" class="nav navbar-nav navbar-right">
			<li class="dropdown hidden-xs">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-user padding-right-small" style="position: relative; top: 3px;"></span>
				 <sec:authentication property="principal.username" /> <i class="fa fa-caret-down"> </i>
				 
				</a>

					<ul class="dropdown-menu">
						<li><a href="<c:url value="/user/dadosCadastrais"/>">Dados Cadastrais</a></li>
						<li><a href="<c:url value="/user/alterarSenha"/>">Alterar Senha</a></li>
					</ul>
				</li>
				
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value="/logout"/>"><span class="fa fa-power-off"></span></a></li>
				</ul>
			</ul>

		</div>
	</div>

	<%@ include file="/template/menu-lateral.jsp"%>
	
	<div class="content">
	

