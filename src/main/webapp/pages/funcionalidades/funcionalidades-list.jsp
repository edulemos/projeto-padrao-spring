<%@ include file="../template/header.jsp"%>

<div class="col-12">
	<div class="card">

		<div class="card-header alert alert-secondary">
			<h4 class="card-title">
				<b>CADASTRO DE FUNCIONALIDADES </b>
			</h4>
		</div>

		<div class="card-content">
			<div class="card-body">

				<%@ include file="funcionalidades-search.jsp"%>
				<div class="table-responsive">

					<table class="table table-striped" id="funcionalidades-table">
						<thead>
							<tr>
								<th>NOME</th>
								<th>DESCRIÇÃO</th>
								<th>AÇÕES</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="f">

								<tr>
									<td class="text-bold-500">${f.nome}</td>
									<td class="text-bold-500">${f.descricao}</td>
									<td class="text-bold-500"><a href="${contextPath}/funcionalidades-form?u=${f.uuid}">
											<button class="btn btn-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="EDITAR">
												<i style="font-size: 1rem;" class="bi bi-pencil"></i>
											</button>
									</a> <a href="${contextPath}/funcionalidades-delete-form?u=${f.uuid}">
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
