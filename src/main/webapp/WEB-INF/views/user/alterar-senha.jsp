<%@ include file="/template/header-sistema.jsp"%>

<fieldset>
	<legend><spring:message code="alterar-senha.titulo"/></legend>

<form:form modelAttribute="userPasswordForm" servletRelativeAction="/user/alterarSenha/save" onsubmit="return validateFormSenhaUser()">

	<div class="row">
		<div class="form-group col-md-6">
			<label><spring:message code="alterar-senha.label.senhaatual"/></label> <input type="password" name="senha" class="form-control"/>
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<label><spring:message code="alterar-senha.label.novasenha"/></label> <input type="password" name="novaSenha" class="form-control"/>
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<label><spring:message code="alterar-senha.label.confirmanovasenha"/></label> <input type="password" name=confirmaSenha class="form-control"/>
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<input type="submit" value="<spring:message code="alterar-senha.btn.enviar"/>" class="btn btn-primary" />
		</div>
	</div>
	
</form:form>

</fieldset>


<%@ include file="/template/footer-sistema.jsp"%>