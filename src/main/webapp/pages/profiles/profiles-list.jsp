<%@ include file="../template/header.jsp"%>

<div class="col-12">
	<div class="card">

		<div class="card-header alert alert-secondary">
			<h4 class="card-title">
				<b>CADASTRO DE PERFIS </b>
			</h4>
		</div>

		<div class="card-content">
			<div class="card-body">

				<%@ include file="profiles-search.jsp"%>
				<div class="table-responsive">

					<table class="table table-striped" id="profiles-table">
						<thead>
							<tr>
								<th>NOME</th>
								<th>PERMISSÕES</th>
								<th>AÇÕES</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="p">

								<tr>
									<td class="text-bold-500">${p.description}</td>
									<td class="text-bold-500">
										<ul>
											<c:forEach items="${p.roles}" var="r">
												<li>${r.description}</li>
											</c:forEach>
										</ul>
									</td>
									<td class="text-bold-500"><a href="${contextPath}/profiles-form?u=${p.uuid}">
											<button class="btn btn-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="EDITAR">
												<i style="font-size: 1rem;" class="bi bi-pencil"></i>
											</button>
									</a> <a href="${contextPath}/profiles-delete-form?u=${p.uuid}">
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
