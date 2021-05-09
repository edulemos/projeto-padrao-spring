<%@ include file="../template/header-external.jsp"%>


<div class="row h-100">
	<div class="col-lg-5 col-12">
		<div id="auth-left">
			<div class="auth-logo alert alert-secondary display-5 fw-bold text-center text-muted">
					BASE PROJECT
			</div>
			<p class="auth-subtitle mb-3">
				<b>CADASTRAR USUÁRIO</b>
			</p>

			<form:form method="POST" modelAttribute="dto" name="dto" action="register-save">

				<div class="form-group position-relative  mb-4">
					<label for="nome">NOME</label>
					<form:input path="nome" cssClass="form-control" />
					<form:errors path="nome" cssClass="text-danger" />
				</div>

				<div class="form-group position-relative  mb-4">
					<label for="tel">TELEFONE</label>
					<form:input path="tel" cssClass="form-control" id="tel" />
					<form:errors path="tel" cssClass="text-danger" />
				</div>

				<div class="form-group position-relative  mb-4">
					<label for="email">EMAIL</label>
					<form:input path="email" cssClass="form-control"/>
					<form:errors path="email" cssClass="text-danger" />
				</div>

				<div class="form-group position-relative  mb-4">
					<label for="password">DIGITE A SENHA</label>
					<form:password path="password" cssClass="form-control" id="password" />
					<form:errors path="password" cssClass="text-danger" />
				</div>

				<div class="form-group position-relative  mb-4">
					<label for="password_confirm">CONFIRME A SENHA</label>
					<form:password path="passwordConfirm" cssClass="form-control" id="password_confirm" />
					<form:errors path="passwordConfirm" cssClass="text-danger" />
					<form:errors cssClass="text-danger" />
				</div>

				<div class="row">
					<div class="col-lg-6 col-12">
						<a href="${contextPath}/login">
							<button type="button" class="btn btn-secondary btn-block mt-3">VOLTAR</button>
						</a>
					</div>
					<div class="col-lg-6 col-12">
						<button type="submit" class="btn btn-primary btn-block mt-3">CONFIRMAR</button>
					</div>
				</div>
			</form:form>

			<div class="alert alert-secondary mt-5" role="alert">
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
	<div class="col-lg-7 d-none d-lg-block">
		<div id="auth-right"></div>
	</div>
</div>

<%@ include file="../template/footer-external.jsp"%>

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
