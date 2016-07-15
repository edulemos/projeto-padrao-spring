<%@ include file="/template/header-sistema.jsp"%>


<fieldset>
	<legend>${perfil.id != null ? 'Editar' : 'Cadastrar'} <spring:message code="admin-perfil-form.label.perfil" /></legend>

	<form:form modelAttribute="admin-perfil-form" servletRelativeAction="/admin/perfil/save" onsubmit="return validateFormAdminPerfil(${perfil.id})">
		<input type="hidden" name="id" value="${perfil.id}" />

		<div class="row">

			<div class="form-group col-md-12">
				<label><spring:message code="admin-perfil-form.label.nome" /></label> 
				<input type="text" name="nome" class="form-control" value="${perfil.nome}" />
			</div>

		</div>
				
		<c:if test="${perfil.id != null}">

			<div class="row">

				<div class="form-group col-md-6">
					<label><spring:message code="admin-perfil-form.label.roles.assoc" />:</label>
					<div class="list-group">
						<c:forEach items="${perfil.roles}" var="role">
							<a href="${pageContext.request.contextPath}/admin/perfil/${perfil.id}/delete/role/${role.id}" class="list-group-item"
								onclick="return confirm('<spring:message code="admin-perfil-form.label.excluirole" /> ${role.descricao}?')"><i class="fa fa-minus-square"></i>&nbsp;&nbsp;${role.descricao}</a>
						</c:forEach>
					</div>
				</div>

				<div class="form-group col-md-6">
					<label><spring:message code="admin-perfil-form.label.roles.disp" />:</label>
					<div class="list-group">
						<c:forEach items="${roles}" var="role">
							<a href="${pageContext.request.contextPath}/admin/perfil/${perfil.id}/add/role/${role.id}" class="list-group-item"
								onclick="return confirm('<spring:message code="admin-perfil-form.label.adicionarole" /> ${role.descricao}?')"><i class="fa fa-plus-square"></i>&nbsp;&nbsp;${role.descricao}</a>
						</c:forEach>
					</div>
				</div>

			</div>

		</c:if>

	&nbsp;&nbsp;
	
	<a href="${pageContext.request.contextPath}/admin/perfil/list">
		<input type="button" value="<spring:message code="admin-perfil-form.btn.voltar" />" class="btn btn-primary" />
	</a>
	
	<input type="submit" value="<spring:message code="admin-perfil-form.btn.enviar" />" class="btn btn-primary" />

	</form:form>

</fieldset>

<%@ include file="/template/footer-sistema.jsp"%>