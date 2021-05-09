<%@ include file="../template/header.jsp"%>



<div class="col-12">
	<section class="section">
		<div class="card">
			<div class="card-header  alert alert-secondary">
				<h4 class="card-title">EXCLUIR</h4>
			</div>

			<div class="card-body">
				<form:form method="POST" modelAttribute="f" name="f" action="funcionalidades-delete">

					<div class="row">
						<div class="col-md-4">

							<h5 style="margin-top: 2rem;">
								CONFIRMA EXCLUSÃO DA FUNCIONALIDADE: <span style="text-transform: uppercase; font-weight: bold; color: red;">${f.nome}</span>?
							</h5>
							<div class="row">
								<form:hidden path="uuid" />
								<form:hidden path="nome" />
								<div class="col-lg-6 col-12">
									<a href="${contextPath}/funcionalidades">
										<button type="button" class="btn btn-secondary btn-block mt-5">VOLTAR</button>
									</a>
								</div>
								<div class="col-lg-6 col-12">
									<button class="btn btn-danger btn-block  mt-5" type="submit">CONFIRMAR</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 mt-5">
							<form:errors path="nome" cssClass="text-danger align-middle" cssStyle="text-transform: uppercase;font-weight: bold;" />
						</div>
					</div>
				</form:form>

			</div>

		</div>
</div>
</section>
</div>


<%@ include file="../template/footer.jsp"%>

