<%@ include file="template/header.jsp"%>

<c:if test="${not empty sessionDto.nomeUser}">
	<div class="alert alert-light text-uppercase fw-bold" role="alert" >bem vindo ao sistema: ${sessionDto.nomeUser}</div>
</c:if>

<%@ include file="template/footer.jsp"%>
