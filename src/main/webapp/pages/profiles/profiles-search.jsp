<!-- search modal -->
<div id="search-modal" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<form method="GET" action="${contextPath}/profiles">
			<div class="modal-content">
				<div class="modal-header alert alert-secondary">
					<h5 class="modal-title">
						<b>PESQUISAR</b>
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="fieldName">Pesquisar por:</label> <select id="fieldName" class="form-select" name="fieldName">
							<option selected value="">selecione o filtro de pesquisa</option>
							<option value="name">Nome</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="fieldValue" placeholder="digite o texto a pesquisar">
					</div>
					<div class="row">
						<div class="col-lg-6 col-12">
								<button type="button" class="btn btn-secondary btn-block mt-3  q" data-bs-dismiss="modal" aria-label="Close">VOLTAR</button>
						</div>
						<div class="col-lg-6 col-12">
							<button class="btn btn-primary btn-block mt-3" type="submit">PESQUISAR</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

