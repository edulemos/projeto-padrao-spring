<%@ include file="/template/header.jsp"%>

<div class="dialog">
	<div class="panel panel-default">

		<p class="panel-heading no-collapse">
			<b>FORMULÁRIO DE CADASTRO</b>
		</p>

		<spring:hasBindErrors name="cadastroForm">
		    <br>
			<div class="alert alert-danger div-alert">
				<b>VERIFIQUE OS ERROS ABAIXO</b>
			</div>
		</spring:hasBindErrors>
		

		<div class="panel-body">
			<form:form modelAttribute="cadastroForm" servletRelativeAction="/cadastro/save" onsubmit="return validateFormCadastro()">

				<div class="form-group">
					<label>Nome:</label> <input type="text" class="form-control" name="nome" value="${form.nome}"/>
					<form:errors path="nome" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label>Email:</label> <input type="text" class="form-control" name="email" value="${form.email}" />
					<form:errors path="email" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label>Confirma Email:</label> <input type="text" class="form-control" name="confirmaEmail" value="${form.confirmaEmail}"/>
					<form:errors path="confirmaEmail" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label>Senha:</label> <input type="password" class="form-control" name="senha" value="${form.senha}"/>
					<form:errors path="senha" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label>Confirma Senha:</label> <input  type="password" class="form-control" name="confirmaSenha" value="${form.confirmaSenha}" />
					<form:errors path="confirmaSenha" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<label>Perfil:</label>
					 <select name="role" class="form-control">
						<option value="">selecione</option>
						<c:forEach items="${roles}" var="r">
							<option value="${r.name}">${r.label}</option>
						</c:forEach>
					</select>
					<form:errors path="role" cssClass="input-erro" />
				</div>

				<div class="form-group">
					<input type="submit" value="ENVIAR" class="btn btn-primary" />
				</div>

			</form:form>
		</div>
	</div>
</div>

<script>
	$('select').val('${form.role}');
</script>

<%@ include file="/template/footer.jsp"%>


