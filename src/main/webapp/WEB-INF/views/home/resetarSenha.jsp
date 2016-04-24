<%@ include file="/template/header.jsp"%>

<div class="dialog">
	<div class="panel panel-default">
		<p class="panel-heading no-collapse">
			<b>CADASTRAR NOVA SENHA</b>
		</p>
		
		<div class="panel-body">
			<form:form servletRelativeAction="/login/resetarSenha/salvar" method="POST" onsubmit="return validateFormResetSenha()">
			<input type="hidden" name="email" value="${email}"/>
			<input type="hidden" name="key" value="${key}"/>
				<div class="form-group">
					<label>Nova Senha</label> <input type="password" name="senha" class="form-control">
				</div>				
				<div class="form-group">
					<label>Confirma Nova Senha</label> <input type="password" name="confirmaSenha" class="form-control">
				</div>				
				<input type="submit" value="ENVIAR" class="btn btn-primary"/>				
			</form:form>
		</div>
	</div>
	
<%@ include file="/template/footer.jsp"%>