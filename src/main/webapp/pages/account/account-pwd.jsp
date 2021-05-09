<%@ include file="../template/header.jsp"%>



<div class="col-12">
	<section class="section">
		<div class="card">
			<div class="card-header alert alert-secondary">


				<h4 class="card-title">
					<b>ALTERAR SENHA</b>
				</h4>

			</div>

			<div class="card-body">
				<form:form method="POST" modelAttribute="pwdDto" name="pwdDto" action="account-pwd-save">
					<form:hidden path="uuid" />


					<div class="row">
						<div class="col-md-4">

							<div class="form-group  mb-4">
								<label for="oldPassword">Senha atual</label>
								<form:password path="oldPassword" cssClass="form-control" id="oldPassword" />
								<form:errors path="oldPassword" cssClass="text-danger" />
							</div>

							<div class="form-group  mb-4">
								<label for="newPassword">Nova senha</label>
								<form:password path="newPassword" cssClass="form-control" id="newPassword" />
								<form:errors path="newPassword" cssClass="text-danger" />
							</div>

							<div class="form-group  mb-4">
								<label for="newPasswordConfirm">Confirmar Senha</label>
								<form:password path="newPasswordConfirm" cssClass="form-control" id="newPasswordConfirm" />
								<form:errors path="newPasswordConfirm" cssClass="text-danger" />
								<form:errors cssClass="text-danger" />

							</div>
							<div class="row">
								<div class="col-lg-6 col-12">
									<button class="btn btn-primary btn-block mt-5" type="submit">GRAVAR</button>
								</div>
							</div>


						</div>
					</div>

				</form:form>


				<div class="row">
					<div class="alert alert-secondary mt-5 col-4" role="alert">


						<ul>
							<li><b>REQUISITOS MÍNIMOS DA SENHA:</b>
								<ul>
									<li>conter de 5 a 15 caracteres</li>
									<li>conter números</li>
									<li>conter letras maiúsculas</li>
									<li>conter letras minusculas</li>
								</ul></li>
						</ul>

					</div>

				</div>

			</div>
		</div>
	</section>
</div>


<%@ include file="../template/footer.jsp"%>

