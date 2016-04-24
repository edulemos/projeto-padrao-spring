<%@ include file="/template/header-sistema.jsp"%>

<fieldset>
	<legend>Alterar Senha</legend>

<form:form modelAttribute="userPasswordForm" servletRelativeAction="/user/alterarSenha/save" onsubmit="return validateFormSenhaUser()">

	<div class="row">
		<div class="form-group col-md-6">
			<label>Senha Atual</label> <input type="password" name="senha" class="form-control"/>
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<label>Nova Senha</label> <input type="password" name="novaSenha" class="form-control"/>
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<label>Confirma Nova Senha</label> <input type="password" name=confirmaSenha class="form-control"/>
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<input type="submit" value="GRAVAR" class="btn btn-primary" />
		</div>
	</div>
	
</form:form>

</fieldset>


<%@ include file="/template/footer-sistema.jsp"%>