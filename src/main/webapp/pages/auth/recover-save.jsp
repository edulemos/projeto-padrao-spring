<%@ include file="../template/header-external.jsp"%>


<div class="row h-100">
	<div class="col-lg-5 col-12">
		<div id="auth-left">
			<div class="auth-logo alert alert-secondary display-5 fw-bold text-center text-muted">
					BASE PROJECT
			</div>
			<p class="auth-subtitle mb-5">
				<b>GRAVAR NOVA SENHA</b>
			</p>

			<form:form method="POST" modelAttribute="recover" name="recover" action="recover-save">
				<form:hidden path="uuid" />


				<div class="form-group position-relative has-icon-left mb-4">
					<label for="password">DIGITE A NOVA SENHA</label>
					<form:password path="password" cssClass="form-control" id="password" />
					<form:errors path="password" cssClass="text-danger" />
				</div>

				<div class="form-group position-relative has-icon-left mb-4">
					<label for="password_confirm">CONFIRME A NOVA SENHA</label>
					<form:password path="passwordConfirm" cssClass="form-control" id="password_confirm" oninput="check(this)" />
					<form:errors path="passwordConfirm" cssClass="text-danger" />
				</div>

				<div class="row">
					<div class="col-lg-6 col-12">
						<a href="${contextPath}/login">
							<button type="button" class="btn btn-secondary btn-block mt-5">VOLTAR</button>
						</a>
					</div>
					<div class="col-lg-6 col-12">
						<button type="submit" class="btn btn-primary btn-block mt-5">CONFIRMAR</button>
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

<script language='javascript' type='text/javascript'>
    function check(input) {
        if (input.value != document.getElementById('password').value) {
            input.setCustomValidity('as senhas devem ser iguais');
        } else {
            // input is valid -- reset the error message
            input.setCustomValidity('');
        }
    }
</script>

<%@ include file="../template/footer-external.jsp"%>
