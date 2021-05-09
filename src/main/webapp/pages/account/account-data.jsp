<%@ include file="../template/header.jsp"%>



<div class="col-12">
	<section class="section">
		<div class="card">
			<div class="card-header alert alert-secondary">

				<h4 class="card-title">
					<b>ALTERAR DADOS USUÁRIO</b>
				</h4>

			</div>

			<div class="card-body">
				<form:form method="POST" modelAttribute="user" name="user" action="account-data-save">
					<form:hidden path="uuid" />

					<div class="row">
						<div class="col-md-4">

							<div class="form-group">
								<label for="username">Email</label>
								<form:input path="username" cssClass="form-control" readonly="true" id="username" />
								<form:errors path="username" cssClass="text-danger" />
							</div>

							<div class="form-group">
								<label for="name">Nome</label>
								<form:input path="name" cssClass="form-control" id="name" />
								<form:errors path="name" cssClass="text-danger" />
							</div>

							<div class="form-group">
								<label for="name">Telefone</label>
								<form:input path="tel" cssClass="form-control" id="tel" />
								<form:errors path="tel" cssClass="text-danger" />
							</div>

							<div class="row">
								<div class="col-lg-6 col-12">
									<button class="btn btn-primary btn-block mt-5" type="submit">GRAVAR</button>
								</div>

							</div>


						</div>
					</div>

				</form:form>
			</div>
		</div>
	</section>
</div>


<%@ include file="../template/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$("#tel").inputmask({
			mask : [ "(99) 9999-9999", "(99) 99999-9999", ],
			keepStatic : true
		});

		$("#tel").inputmask({
			removeMaskOnSubmit : true
		});

	});
</script>

