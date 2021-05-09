<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="pt-br">

<head>
<title>BASE PROJECT</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="${contextPath}/resources/assets/css/vendors/bootstrap.min.css">
<link rel="stylesheet" href="${contextPath}/resources/assets/css/vendors/dataTables.bootstrap5.min.css">
<link rel="stylesheet" href="${contextPath}/resources/assets/css/vendors/perfect-scrollbar.css" />
<link rel="stylesheet" href="${contextPath}/resources/assets/css/app.css">

</head>

<body id="main-body">
	<input type="hidden" name="contextPath" value="${contextPath}">
	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">
				<div class="sidebar-header">
					<div class="d-flex justify-content-between">
					<a href="${contextPath}/">
						<div class="auth-logo alert alert-secondary fw-bold text-center text-muted">BASE PROJECT</div>
					</a>
						<div class="toggler">
							<a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
						</div>
					</div>
				</div>

				<%@ include file="menu.jsp"%>

				<button class="sidebar-toggler btn x">
					<i data-feather="x"></i>
				</button>
			</div>
		</div>
		<div id="main">
			<header class="mb-3">
				<a href="#" class="burger-btn d-block d-xl-none"> <i class="bi bi-justify fs-3"></i>
				</a>
			</header>

			<div class="page-heading">



				<div class="page-title">
					<div class="row">

						<div class="col-12 col-md-6 order-md-1 order-last"></div>
					</div>
				</div>