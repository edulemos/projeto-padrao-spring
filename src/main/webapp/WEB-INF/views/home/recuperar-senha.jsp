<%@ include file="/template/header.jsp"%>

<div class="dialog">
	<div class="panel panel-default">
		<p class="panel-heading no-collapse">
			<b><span class="upper-text"><spring:message  code="recuperar-senha.titulo"/></span> </b>
		</p>
		
		<div class="panel-body">
			<form:form servletRelativeAction="/login/recuperarSenha/enviarEmail" method="POST" onsubmit="return validateFormRecuperarSenha()">
				<div class="form-group">
					<label><spring:message code="recuperar-senha.label"/></label>
					<input type="text" name="email" class="form-control">
				</div>				
				<input type="submit" value="<spring:message code="recuperar-senha.btn.enviar"/>" class="btn btn-primary"/>				
			</form:form>
		</div>
	</div>
	
	<%@ include file="/template/footer.jsp"%>