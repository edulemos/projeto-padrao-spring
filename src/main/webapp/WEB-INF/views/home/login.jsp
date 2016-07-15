<%@ include file="/template/header.jsp"%>

<div class="dialog">
	<div class="panel panel-default">
		<p class="panel-heading no-collapse">
			<b><span class="upper-text"><spring:message code="login.titulo"/></span></b>
		</p>

		<div class="panel-body">
			<c:if test="${param.error != null}">
				<div class="alert alert-danger">
					<b><spring:message  code="login.msg.dados.invalidos" /></b>
				</div>
			</c:if>

			<form:form servletRelativeAction="/login" onsubmit="return validateFormLogin()">
				<div class="form-group">
					<label><spring:message code="login.label.email" /></label> <input type="text" id="username" name="username" class="form-control">
				</div>
				<div class="form-group">
					<label><spring:message code="login.label.senha" /></label> <input type="password" id="password" name="password" class="form-control" />
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<input type="submit" value="<spring:message code="login.btn.enviar" />" class="btn btn-primary form-control" />
					</div>
					<div class="col-md-8 text-right">
						<a href="<c:url value="/login/recuperarSenha"/>" style="margin-top: 10px;"><spring:message code="login.label.recuperarsenha" /></a>
					</div>
				</div>
		</div>

		</form:form>
	</div>
</div>

<script>
	$('#username').val('admin@email.com');
	$('#password').val('123456');
</script>


<%@ include file="/template/footer.jsp"%>