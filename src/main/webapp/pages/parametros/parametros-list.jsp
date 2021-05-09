<%@ include file="../template/header.jsp"%>

<div class="col-12">
	<div class="card">

		<div class="card-header alert alert-secondary">
			<h4 class="card-title">
				<b>CADASTRO DE PARÂMETROS </b>
			</h4>
		</div>

		<div class="card-content">
			<div class="card-body">

				<%@ include file="parametros-search.jsp"%>

				<div class="table-responsive">
					<form method="get" action="${contextPath}/parametros">
						<input type="hidden" name="fieldName" value="funcionalidade">
						<div class="form-group">
							<select id="idSelctFunc" class="form-control" name="fieldValue" style="width: 25%" onchange="this.form.submit()">
								<option value="">filtro funcionalidade...</option>
								<c:forEach items="${funcionalidades}" var="f">
									<option value="${f.id}">${f.nome}</option>
								</c:forEach>
							</select>
						</div>
					</form>

					<table class="table table-striped" id="parametros-table">
						<thead>
							<tr>
								<th>NOME</th>
								<th>VALOR</th>
								<th>FUNCIONALIDADE</th>
								<th>AÇÕES</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="p">

								<tr>
									<td>${p.nome}</td>
									<td>${p.valor}</td>
									<td>${p.funcionalidade.nome}</td>
									<td><a href="${contextPath}/parametros-form?u=${p.uuid}">
											<button class="btn btn-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="EDITAR">
												<i style="font-size: 1rem;" class="bi bi-pencil"></i>
											</button>
									</a> <a href="${contextPath}/parametros-delete-form?u=${p.uuid}">
											<button class="btn btn-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="EXCLUIR">
												<i style="font-size: 1rem;" class="bi bi-trash"></i>
											</button>
									</a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

			</div>

		</div>
	</div>
</div>




<%@ include file="../template/footer.jsp"%>
<script type="text/javascript">
	$( document ).ready(function() {
		$('#idSelctFunc').val(${idFunc}).attr("selected", "selected");
	});
</script>

