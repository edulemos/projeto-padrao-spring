<%@ include file="../template/header-external.jsp"%>


<div class="row h-100">
	<div class="col-lg-5 col-12">
		<div id="auth-left">
			<div class="auth-logo alert alert-secondary display-5 fw-bold text-center text-muted">
					BASE PROJECT
			</div>
			<p class="auth-subtitle mb-5">
				<b>Insira seu e-mail e enviaremos o link para redefenir sua senha.</b>
			</p>
			<form method="POST" action="${contextPath}/recover-email">

				<div class="form-group position-relative has-icon-left mb-4">
					<input type="email" name="email" class="form-control form-control-xl" placeholder="Email" value="${email}" required>
					<div class="form-control-icon">
						<i class="bi bi-envelope"></i>
					</div>
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
			</form>
		</div>
	</div>
	<div class="col-lg-7 d-none d-lg-block">
		<div id="auth-right"></div>
	</div>
</div>

<%@ include file="../template/footer-external.jsp"%>
