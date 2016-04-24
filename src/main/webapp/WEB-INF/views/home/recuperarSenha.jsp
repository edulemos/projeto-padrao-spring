<%@ include file="/template/header.jsp"%>

<div class="dialog">
	<div class="panel panel-default">
		<p class="panel-heading no-collapse">
			<b>RECUPERAR SENHA</b>
		</p>
		
		<div class="panel-body">
			<form:form servletRelativeAction="/login/recuperarSenha/enviarEmail" method="POST" onsubmit="return validateFormRecuperarSenha()">
				<div class="form-group">
					<label>Informe o email cadastrado</label> <input type="text" name="email" class="form-control">
				</div>				
				<input type="submit" value="ENVIAR" class="btn btn-primary"/>				
			</form:form>
		</div>
	</div>
	
	<%@ include file="/template/footer.jsp"%>