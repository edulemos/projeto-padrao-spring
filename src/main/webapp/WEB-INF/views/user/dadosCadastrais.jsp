<%@ include file="/template/header-sistema.jsp"%>


<fieldset>
	<legend>Dados Cadastrais</legend>

<form:form modelAttribute="userForm" servletRelativeAction="/user/dadosCadastrais/save" onsubmit="return validateFormUser()">

	<div class="row">
		<div class="form-group col-md-6">
			<label>Nome</label> <input type="text" name="name" class="form-control" value="${user.name}" />
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-md-6">
			<label>Email</label> <input type="text" name="email" class="form-control" value="${user.email}" />
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