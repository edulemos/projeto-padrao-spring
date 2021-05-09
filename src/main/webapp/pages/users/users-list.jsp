<%@ include file="../template/header.jsp"%>

<div class="col-12">
	<div class="card">

		<div class="card-header alert alert-secondary">
			<h4 class="card-title">
				<b>CADASTRO DE USUÁRIOS</b>
			</h4>
		</div>

		<div class="card-content">
			<div class="card-body">

				<%@ include file="users-search.jsp"%>
				<div class="table-responsive">

					<table class="table table-striped" id="users-table">
						<thead>
							<tr>
								<th>NOME</th>
								<th>EMAIL</th>
								<th>STATUS</th>
								<th>AÇÕES</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="user">

								<tr>
									<td class="text-bold-500">${user.name}</td>
									<td class="text-bold-500">${user.username}</td>
									<td class="text-bold-500">
										<c:if test = "${null != user.isAccountNonLocked && !user.isAccountNonLocked}">
											<span class="badge bg-danger">BLOQUEADO</span>
										</c:if>
										<c:if test = "${null == user.isAccountNonLocked || user.isAccountNonLocked}">
											<span class="badge bg-primary">&nbsp;ATIVO&nbsp;</span>
										</c:if>
									</td>
									<td class="text-bold-500"><a href="${contextPath}/users-form?u=${user.uuid}">
											<button class="btn btn-primary" data-bs-toggle="tooltip" data-bs-placement="top" title="EDITAR">
												<i style="font-size: 1rem;" class="bi bi-pencil"></i>
											</button>
									</a> <a href="${contextPath}/users-delete-form?u=${user.uuid}">
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
