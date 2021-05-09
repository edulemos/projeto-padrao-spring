<%@ include file="../template/header.jsp"%>



<div class="col-12">
	<section class="section">
		<div class="card">
			<div class="card-header  alert alert-secondary">
				<h4 class="card-title">EXCLUIR USUÁRIO</h4>
			</div>

			<div class="card-body">

				<div class="row">
					<div class="col-md-12">

						<h5 style="margin-top: 2rem;">
							CONFIRMA EXCLUSÃO DO USUÁRIO?
						</h5>

						<div class="alert alert-dark mb-4 text-uppercase fw-bold" role="alert">${user.name}</div>


						<div class="row">


							<div class="col-2">
								<a href="${contextPath}/users">
									<button type="button" class="btn btn-secondary btn-block mt-5">VOLTAR</button>
								</a>
							</div>
							<div class="col-2">
								<a href="${contextPath}/users-delete?u=${user.uuid}">
									<button class="btn btn-danger btn-block  mt-5" type="button">CONFIRMAR</button>
								</a>
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
</div>


<%@ include file="../template/footer.jsp"%>

