<%@ include file="/template/header-sistema.jsp"%>


<fieldset>
	<legend><spring:message code="dados-cadastro.titulo"/></legend>

<form:form modelAttribute="userForm" servletRelativeAction="/user/dadosCadastrais/save" onsubmit="return validateFormUser()">
	<input type="hidden" name="id" value="${user.id}" />
	<input type="hidden" name="password" value="${user.password}" />

	<div class="row">
		<div class="form-group col-md-6">
			<label><spring:message code="dados-cadastro.label.nome"/></label> <input type="text" name="name" class="form-control" value="${user.name}" />
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<label><spring:message code="dados-cadastro.label.email"/></label> <input type="text" name="email" class="form-control" value="${user.email}" />
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<input type="submit" value="<spring:message code="dados-cadastro.btn.enviar"/>" class="btn btn-primary" />
		</div>
	</div>
	
</form:form>
	

</fieldset>



<%@ include file="/template/footer-sistema.jsp"%>