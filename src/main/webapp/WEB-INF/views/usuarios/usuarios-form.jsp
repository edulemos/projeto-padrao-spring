<%@ include file="/template/header-sistema.jsp"%>

<script type="text/javascript" src="<c:url value='/static/js/usuarios-form-script.js'/>"></script>

<fieldset>
	<legend>${user.id != null ? 'Editar' : 'Cadastrar'}
		<spring:message code="usuarios-form.label.usuario" />
	</legend>

	<form:form modelAttribute="usuarios-form" servletRelativeAction="/usuarios/save" onsubmit="return validateFormAdminUser(${user.id})">
		<input type="hidden" id="id" name="id" value="${user.id}" />
		<input type="hidden" id="url" value="${pageContext.request.contextPath}" />

		<div class="row">

			<div class="form-group col-md-6">
				<label><spring:message code="usuarios-form.label.nome" /></label> <input type="text" name="name" class="form-control" value="${user.name}" />
			</div>

			<div class="form-group col-md-6">
				<label><spring:message code="usuarios-form.label.email" /></label> <input type="text" name="email" class="form-control" value="${user.email}" />
			</div>

		</div>


		<c:if test="${user.id == null}">
			<div class="row">

				<div class="form-group col-md-6">
					<label><spring:message code="usuarios-form.label.senha" /></label> <input type="password" name="password" class="form-control"
						value="${user.password}" />
				</div>

				<div class="form-group col-md-6">
					<label><spring:message code="usuarios-form.label.perfil" />:</label> <select name="perfis[0].id" class="form-control">
						<option value="">selecione</option>
						<c:forEach items="${perfis}" var="p">
							<option value="${p.id}">${p.nome}</option>
						</c:forEach>
					</select>
				</div>

			</div>
		</c:if>

		<c:if test="${user.id != null}">
		
		 <fieldset ng-app="app" ng-controller="ctrl">
			<legend id="legend2">Perfis de Acesso</legend>

			<input type="hidden" name="password" value="${user.password}" />

				<div class="row">
					<div class="form-group col-md-6"><input type="text" ng-model="busca1" class="form-control" placeholder="digite o perfil para pesquisar" /></div>
					<div class="form-group col-md-6"><input type="text" ng-model="busca2" class="form-control" placeholder="digite o perfil paraa pesquisar" /></div>
				</div>
				
				<div class="row">

				<div class="form-group col-md-6">

					<table class="table table-striped table-bordered">
						<tr>
							<th><spring:message code="usuarios-form.label.perfil.assoc" /></th>
							<th style="text-align: center;"><a href="#"  data-placement="left" title="Remover Todos Perfis" ng-click="excluirTodosPerfis(${user.id})" bs-tooltip><i class="fa fa-arrow-right" aria-hidden="true"></i></a></th>
						</tr>
						<tr ng-repeat="perfil in perfisAssociados | filter:busca1">
							<td>{{perfil.nome}}</td>
							<td style="text-align: center;"><a href="#" data-placement="left" title="Remover {{perfil.nome}}"  ng-click="excluirPerfil(perfil)" bs-tooltip><i class="fa fa-arrow-right" aria-hidden="true"></i></td>
						</tr>
					</table>

				</div>

				<div class="form-group col-md-6">

						<table class="table table-striped table-bordered">
							<tr>
								<th style="text-align: center;"><a href="#" data-placement="left" title="Adicionar Todos Perfis" ng-click="adicionarTodosPerfis(${user.id})" bs-tooltip><i class="fa fa-arrow-left" aria-hidden="true"></i></a></th>
								<th><spring:message code="usuarios-form.label.perfil.disp" /></th>
							</tr>
							<tr ng-repeat="perfil in perfisDisponiveis | filter:busca2">
								<td style="text-align: center;"><a href="#"  data-placement="left" title="Adicionar {{perfil.nome}}"  ng-click="adicionarPerfil(perfil)" bs-tooltip><i class="fa fa-arrow-left" aria-hidden="true"></i></a></td>
								<td>{{perfil.nome}}</td>
							</tr>
						</table>

					</div>

				</div>
				
				</fieldset>
		</c:if>

	&nbsp;&nbsp;
	
	<a href="${pageContext.request.contextPath}/usuarios/list"> <input type="button" value="<spring:message code="usuarios-form.btn.voltar" />"
			class="btn btn-primary" />
		</a>

		<input type="submit" value="<spring:message code="usuarios-form.btn.enviar" />" class="btn btn-primary" />

	</form:form>

	<script>
		var id =  $('#id').val(); 
	</script>

</fieldset>

<%@ include file="/template/footer-sistema.jsp"%>