<%@ include file="/template/header-sistema.jsp"%>

<script type="text/javascript" src="<c:url value='/static/js/app/perfil-form-script.js'/>"></script>

<fieldset>
	<legend>${perfil.id != null ? 'Editar' : 'Cadastrar'}
		<spring:message code="perfil-form.label.perfil" />
	</legend>

	<form:form modelAttribute="perfil-form" servletRelativeAction="/perfis/save" onsubmit="return validateFormAdminPerfil(${perfil.id})">
		<input type="hidden" name="id" value="${perfil.id}" />

		<div class="row">

			<div class="form-group col-md-6">
				<label><spring:message code="perfil-form.label.nome" /></label> <input type="text" name="nome" class="form-control" value="${perfil.nome}" />
			</div>

		</div>

		<c:if test="${perfil.id != null}">

			<fieldset ng-app="app" ng-controller="ctrl">
				<legend id="legend2">Permiss&otilde;es</legend>

				<div class="row">
					<div class="form-group col-md-6">
						<input type="text" ng-model="busca1" class="form-control" placeholder="digite para pesquisar" />
					</div>
					<div class="form-group col-md-6">
						<input type="text" ng-model="busca2" class="form-control" placeholder="digite para pesquisar" />
					</div>
				</div>
				<div class="row">


					<div class="form-group col-md-6">

						<table class="table table-striped table-bordered">
							<tr>
								<th><spring:message code="perfil-form.label.roles.assoc" /></th>
								<th style="text-align: center;"><a href="#" data-placement="left" title="Remover Todas Permiss&otilde;es" ng-click="excluirTodasRoles(${perfil.id})"
									bs-tooltip><i class="fa fa-arrow-right" aria-hidden="true"></i></a></th>
							</tr>
							<tr ng-repeat="role in rolesAssociadas | filter:busca1">
								<td>{{role.descricao}}</td>
								<td style="text-align: center;"><a href="#" data-placement="left" title="Remover {{role.descricao}}" ng-click="excluirRole(role)" bs-tooltip><i
										class="fa fa-arrow-right" aria-hidden="true"></i></td>
							</tr>
						</table>

					</div>

					<div class="form-group col-md-6">

						<table class="table table-striped table-bordered">
							<tr>
								<th style="text-align: center;"><a href="#" data-placement="left" title="Adicionar Todas Permiss&otilde;es"
									ng-click="adicionarTodasRoles(${perfil.id})" bs-tooltip><i class="fa fa-arrow-left" aria-hidden="true"></i></a></th>
								<th><spring:message code="perfil-form.label.roles.disp" /></th>
							</tr>
							<tr ng-repeat="role in rolesDisponiveis | filter:busca2">
								<td style="text-align: center;"><a href="#" data-placement="left" title="Adicionar {{role.descricao}}" ng-click="adicionarRole(role)"
									bs-tooltip><i class="fa fa-arrow-left" aria-hidden="true"></i></a></td>
								<td>{{role.descricao}}</td>
							</tr>
						</table>

					</div>
				</div>


			</fieldset>


		</c:if>

	&nbsp;&nbsp;
	
	<a href="${pageContext.request.contextPath}/perfis/list"> <input type="button" value="<spring:message code="perfil-form.btn.voltar" />"
			class="btn btn-primary" />
		</a>
		<input type="submit" value="<spring:message code="perfil-form.btn.enviar" />" class="btn btn-primary" />

	</form:form>

	<script>
		var idPerfil = '${perfil.id}';
	</script>

</fieldset>

<%@ include file="/template/footer-sistema.jsp"%>