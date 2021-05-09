<%@ include file="template/header-external.jsp"%>

<div class="row h-100">
	<div class="col-lg-5 col-12">
		<div id="auth-left">
			<div class="auth-logo alert alert-secondary display-5 fw-bold text-center text-muted">
					BASE PROJECT
			</div>
			<h3 class="auth-title">LOGIN</h3>

			<form method="POST" action="${contextPath}/login">

				<div class="form-group position-relative has-icon-left mb-4">
					<input name="username" type="text" class="form-control form-control-xl" placeholder="Email" required>
					<div class="form-control-icon">
						<i class="bi bi-person"></i>
					</div>
				</div>

				<div class="form-group position-relative has-icon-left mb-4">
					<input name="password" type="password" class="form-control form-control-xl" placeholder="Senha" required> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="form-control-icon">
						<i class="bi bi-shield-lock"></i>
					</div>
				</div>
				<button class="btn btn-primary btn-block btn-lg shadow-lg mt-5" type="submit">ENTRAR</button>
			</form>
			<div class="text-center mt-5 text-lg fs-4">
				<p>
					<a class="font-bold" href="${contextPath}/recover">Esqueceu sua senha?</a>
				</p>
				<p>
					<a class="font-bold" href="${contextPath}/register">Cadastre-se!</a>
				</p>
			</div>
		</div>
	</div>
	<div class="col-lg-7 d-none d-lg-block">
		<div id="auth-right">
		</div>
	</div>
</div>

<%@ include file="template/footer-external.jsp"%>
