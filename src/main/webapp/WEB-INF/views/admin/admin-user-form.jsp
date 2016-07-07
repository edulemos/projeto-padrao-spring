<%@ include file="/template/header-sistema.jsp"%>


<fieldset>
	<legend>${user.id != null ? 'Editar' : 'Cadastrar'} <spring:message code="admin-user-form.label.usuario" /></legend>

	<form:form modelAttribute="admin-user-form" servletRelativeAction="/admin/user/save" onsubmit="return validateFormAdminUser(${user.id})">
		<input type="hidden" name="id" value="${user.id}" />

		<div class="row">

			<div class="form-group col-md-6">
				<label><spring:message code="admin-user-form.label.nome" /></label> 
				<input type="text" name="name" class="form-control" value="${user.name}" />
			</div>

			<div class="form-group col-md-6">
				<label><spring:message code="admin-user-form.label.email" /></label>
				<input type="text" name="email" class="form-control" value="${user.email}" />
			</div>

		</div>


		<c:if test="${user.id == null}">
			<div class="row">

				<div class="form-group col-md-6">
					<label><spring:message code="admin-user-form.label.senha" /></label>
					<input type="text" name="password" class="form-control" value="${user.password}" />
				</div>

				<div class="form-group col-md-6">
					<label><spring:message code="admin-user-form.label.perfil" />:</label>
					<select name="authorities[0]" class="form-control">
						<option value="">selecione</option>
						<c:forEach items="${roles}" var="r">
							<option value="${r.name}">${r.label}</option>
						</c:forEach>
					</select>
				</div>

			</div>
		</c:if>
		
		<c:if test="${user.id != null}">

			<input type="hidden" name="password" value="${user.password}" />

			<div class="row">

				<div class="form-group col-md-6">
					<label><spring:message code="admin-user-form.label.perfil.assoc" />:</label>
					<div class="list-group">
						<c:forEach items="${user.authorities}" var="rule">
							<a href="${pageContext.request.contextPath}/admin/user/${user.id}/delete/role/${rule.id}" class="list-group-item"
								onclick="return confirm('<spring:message code="admin-user-form.label.excluiperfil" /> ${rule.name}?')"><i class="fa fa-minus-square"></i>&nbsp;&nbsp;${rule.name}</a>
						</c:forEach>
					</div>
				</div>

				<div class="form-group col-md-6">
					<label><spring:message code="admin-user-form.label.perfil.disp" />:</label>
					<div class="list-group">
						<c:forEach items="${roles}" var="r">
							<a href="${pageContext.request.contextPath}/admin/user/${user.id}/add/role/${r.name}" class="list-group-item"
								onclick="return confirm('<spring:message code="admin-user-form.label.adicionaperfil" /> ${r.name}?')"><i class="fa fa-plus-square"></i>&nbsp;&nbsp;${r.name}</a>
						</c:forEach>
					</div>
				</div>

			</div>

		</c:if>

	&nbsp;&nbsp;
	
	<a href="${pageContext.request.contextPath}/admin/user/list">
		<input type="button" value="<spring:message code="admin-user-form.btn.voltar" />" class="btn btn-primary" />
	</a>
	
	<input type="submit" value="<spring:message code="admin-user-form.btn.enviar" />" class="btn btn-primary" />

	</form:form>

</fieldset>

<%@ include file="/template/footer-sistema.jsp"%>