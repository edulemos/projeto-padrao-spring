<%@ include file="/template/header.jsp"%>

<div class="dialog">
	<div class="panel panel-default">

		<p class="panel-heading no-collapse">
			<b><span class="upper-text"><spring:message code="cadastro.titulo"/></span></b>
		</p>

		<spring:hasBindErrors name="cadastroForm">
			<br>
			<div class="alert alert-danger div-alert">
				<b><spring:message code="global.titulo.verificarerros"/></b>
			</div>
		</spring:hasBindErrors>


		<div class="panel-body">
			<form:form modelAttribute="cadastroForm" servletRelativeAction="/cadastro/save" onsubmit="return validateFormCadastro()">

				<div class="form-group">
					<label><spring:message code="cadastro.label.nome" /></label> <input type="text" class="form-control" name="nome" value="${form.nome}" />
					<form:errors path="nome" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label><spring:message code="cadastro.label.email" /></label> <input type="text" class="form-control" name="email" value="${form.email}" />
					<form:errors path="email" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label><spring:message code="cadastro.label.confirmaemail" /></label> <input type="text" class="form-control" name="confirmaEmail" value="${form.confirmaEmail}" />
					<form:errors path="confirmaEmail" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label><spring:message code="cadastro.label.senha" /></label> <input type="password" class="form-control" name="senha" value="${form.senha}" />
					<form:errors path="senha" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label><spring:message code="cadastro.label.confirmasenha" /></label> <input type="password" class="form-control" name="confirmaSenha" value="${form.confirmaSenha}" />
					<form:errors path="confirmaSenha" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<input type="submit" value="<spring:message code="cadastro.btn.enviar" />" class="btn btn-primary" />
				</div>

			</form:form>
		</div>
	</div>
</div>

<%@ include file="/template/footer.jsp"%>


