<%@ include file="../template/header.jsp"%>

<div class="col-12">
	<section class="section">
		<div class="card">
			<div class="card-header alert alert-secondary">

				<b>CONSULTA CEP</b>

			</div>

			<div class="card-body">


				<fieldset>
					<legend>Endere&ccedil;o</legend>

					<div class="row mb-2">

						<div class="col-2">
							<label for="cep">Cep</label>
						    <input class="form-control" type="text" name="cep" id="cep">
						</div>

						<div class="col-2">
							<label for="uf">UF</label>
						    <input class="form-control" type="text" name="uf" id="uf">
						</div>

						<div class="col-4">
							<label for="cidade">Cidade</label> 
							<input class="form-control" type="text" name="cidade" id="cidade">
						</div>

						<div class="col-4">
							<label for="bairro">Bairro</label> 
							<input class="form-control" type="text" name="bairro" id="bairro">
						</div>


					</div>

					<div class="row mb-4">

						<div class="col-5">
							<label for="endereco">Logradouro</label>
						    <input class="form-control" type="text" name="endereco" id="endereco">
						</div>

						<div class="col-2">
							<label for="numero">N&uacute;mero</label>
						    <input class="form-control" type="text" name="numero" id="numero">
						</div>

						<div class="col-5">
							<label for="complemento">Complemento</label> 
							<input class="form-control" type="text" name="complemento" id="complemento">
						</div>

					</div>


				</fieldset>
			</div>
	</section>
</div>


<%@ include file="../template/footer.jsp"%>
<script type="text/javascript">
			$(document).ready(function () {

				$("#cep").inputmask("99.999-999");

				$("#cep").inputmask({
					removeMaskOnSubmit: true
				});

				$("#cep").keypress(function () {
					var value = $(this).val().replace(/\D/g, "");
					var size = value.length;
					if (size == 8) {

						var url = "/cep/" + value;

						$.getJSON(url, function (result) {
							if (!result.localidade) {
								$(".toast-body").text("cep nao encontrado!");
								var toastElList = [].slice.call(document.querySelectorAll('.toast'))
								var toastList = toastElList.map(function (toastEl) {
									return new bootstrap.Toast(toastEl)
								});
								toastList.forEach(toast => toast.show());
							} else {
								$("#uf").val(result.uf);
								$("#cidade").val(result.localidade);
								$("#bairro").val(result.bairro);
								$("#endereco").val(result.logradouroDNEC);
								$("#numero").focus();
							}

						});

					}
				});
			});

		</script>