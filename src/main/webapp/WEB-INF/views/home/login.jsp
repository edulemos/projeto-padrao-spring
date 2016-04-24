<%@ include file="/template/header.jsp"%>

<div class="dialog">
	<div class="panel panel-default">
		<p class="panel-heading no-collapse">
			<b>ACESSO AO SISTEMA</b>
		</p>

		<div class="panel-body">
			<c:if test="${param.error != null}">
				<div class="alert alert-danger">
					<b><fmt:message key="erro.login.invalido" /></b>
				</div>
			</c:if>

			<form:form servletRelativeAction="/login" onsubmit="return validateFormLogin()">
				<div class="form-group">
					<label>Email</label> <input type="text" name="username" class="form-control">
				</div>
				<div class="form-group">
					<label>Senha</label> <input type="password" name="password" class="form-control" />
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<input type="submit" value="ENVIAR" class="btn btn-primary form-control" />
					</div>
					<div class="col-md-8 text-right">
						<a href="<c:url value="/login/recuperarSenha"/>" style="margin-top: 10px;">recuperar senha</a>
					</div>
				</div>
		</div>

		</form:form>
	</div>
</div>

<%@ include file="/template/footer.jsp"%>