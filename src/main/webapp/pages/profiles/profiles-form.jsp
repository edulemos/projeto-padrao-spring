<%@ include file="../template/header.jsp"%>



<div class="col-12">
	<section class="section">
		<div class="card">
			<div class="card-header alert alert-secondary">

				<c:if test="${p.id != null}">
					<h4 class="card-title">
						<b>ALTERAR</b>
					</h4>
				</c:if>

				<c:if test="${p.id == null}">
					<h4 class="card-title">
						<b>CADASTRAR</b>
					</h4>
				</c:if>

			</div>

			<div class="card-body">
				<form:form method="POST" modelAttribute="p" name="p" action="profiles-save">
					<form:hidden path="uuid" />

					<div class="row">
						<div class="col-md-6">

							<div class="form-group">
								<label for="nome">Nome</label>
								<form:input path="name" cssClass="form-control" id="name" />
								<form:errors path="name" cssClass="text-danger" />
							</div>
							
							<div class="form-group">
								<label for="description">Descrição</label>
								<form:input path="description" cssClass="form-control" id="description" />
								<form:errors path="description" cssClass="text-danger" />
							</div>

							<div class="row">
								<div class="col">
									<div class="form-group">
										<label for="nome">Permissões Cadastradas</label>
										<form:select path="roles" cssClass="form-select form-select-lg" id="roles">
											<form:options items="${p.roles}" itemLabel="description" itemValue="id" />
										</form:select>
										<div class="row mt-1">
											<div class="col">
												<button onclick="copyAllLeft()" type="button" class="btn btn-primary   btn-block">
													<b><i class="bi bi-dash-circle-fill"></i> &nbsp;<i style="font-size: 1rem;" class="bi bi-chevron-double-right"></i></b>
												</button>
											</div>
											<div class="col">
												<button onclick="copyOneLeft()" type="button" class="btn btn-primary   btn-block">
													<b><i class="bi bi-dash-circle-fill"></i>&nbsp;<i style="font-size: 1rem;" class="bi bi-chevron-right"></i></b>
												</button>
											</div>
										</div>

									</div>

								</div>
								<div class="col">
									<div class="form-group">
										<label for="nome">Permissões disponíveis</label>
										<form:select path="avaliableRoles" cssClass="form-select form-select-lg" id="avaliableRoles">
											<form:options items="${p.avaliableRoles}" itemLabel="description" itemValue="id" />
										</form:select>
										<div class="row mt-1">
											<div class="col">
												<button onclick="copyOneRight()" type="button" class="btn btn-primary   btn-block">
													<b><i style="font-size: 1rem;" class="bi bi-chevron-left"></i>&nbsp;<i class="bi bi-plus-circle-fill"></i></b>
												</button>
											</div>
											<div class="col">
												<button onclick="copyAllRight()" type="button" class="btn btn-primary   btn-block">
													<b><i style="font-size: 1rem;" class="bi bi-chevron-double-left">&nbsp;<i class="bi bi-plus-circle-fill"></i></i></b>
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-6 col-12">
									<a href="${contextPath}/profiles">
										<button type="button" class="btn btn-secondary btn-block  mt-5">VOLTAR</button>
									</a>
								</div>
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
	function copyAllLeft() {
		$('#roles option').remove().appendTo('#avaliableRoles');
	}

	function copyOneLeft() {
		$('#roles option:selected').remove().appendTo('#avaliableRoles');
	}

	function copyAllRight() {
		$('#avaliableRoles option').remove().appendTo('#roles');
	}

	function copyOneRight() {
		$('#avaliableRoles option:selected').remove().appendTo('#roles');

	}

	$('#p').submit(function() {
		$("#roles > option").prop("selected", "selected");
	});
</script>


