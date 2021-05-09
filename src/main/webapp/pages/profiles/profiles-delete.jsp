<%@ include file="../template/header.jsp"%>



<div class="col-12">
	<section class="section">
		<div class="card">
			<div class="card-header  alert alert-secondary">
				<h4 class="card-title">EXCLUIR</h4>
			</div>

			<div class="card-body">
				<form:form method="POST" modelAttribute="p" name="p" action="profiles-delete">
					<form:hidden path="uuid" />
					<form:hidden path="name" />

					<div class="row">
						<div class="col-md-12">

							<h5 style="margin-top: 2rem;">
								CONFIRMA EXCLUSÃO DO PERFIL?
							</h5>

							<div class="alert alert-dark mb-4 text-uppercase fw-bold" role="alert">${p.name}</div>


							<div class="row">
								<div class="col-2">
									<a href="${contextPath}/profiles">
										<button type="button" class="btn btn-secondary btn-block mt-5">VOLTAR</button>
									</a>
								</div>
								<div class="col-2">
									<button class="btn btn-danger btn-block  mt-5" type="submit">CONFIRMAR</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 mt-5">
							<form:errors path="name" cssClass="text-danger align-middle" cssStyle="text-transform: uppercase;font-weight: bold;" />
						</div>
					</div>
				</form:form>

			</div>

		</div>
</div>
</section>
</div>


<%@ include file="../template/footer.jsp"%>

