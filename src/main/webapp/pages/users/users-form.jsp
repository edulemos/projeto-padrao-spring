<%@ include file="../template/header.jsp"%>



<div class="col-12">
	<section class="section">
		<div class="card">
			<div class="card-header alert alert-secondary">

				<c:if test="${user.id != null}">
					<h4 class="card-title">
						<b>ALTERAR</b>
					</h4>
				</c:if>

				<c:if test="${user.id == null}">
					<h4 class="card-title">
						<b>CADASTRAR</b>
					</h4>
				</c:if>

			</div>

			<div class="card-body">
				<form:form method="POST" modelAttribute="user" name="user" action="users-save">
					<form:hidden path="uuid" />

					<div class="row">
						<div class="col-md-6">

							<div class="form-group">
								<label for="username">Email</label>
								<form:input path="username" cssClass="form-control" readonly="${user.id != null}" id="username" />
								<form:errors path="username" cssClass="text-danger" />
								<div id="div_err_email" class="text-danger" ></div>
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

							<c:if test="${empty user.uuid}">
								<label for="password">Senha</label>
								<form:password path="password" cssClass="form-control" id="password" />
							</c:if>

							<div class="form-group">
								<label for="">Status</label>
								<form:select path="isAccountNonLocked" cssClass="form-control">
									<form:option value="true">ATIVO</form:option>
									<form:option value="false">BLOQUEADO</form:option>
								</form:select>
								<form:errors cssClass="text-danger" />
							</div>
							
							<div class="row">
								<div class="col">
									<div class="form-group">
										<label for="nome">Perfis Cadastrados</label>
										<form:select path="profiles" cssClass="form-select form-select-lg" id="roles">
											<form:options items="${user.profiles}" itemLabel="name" itemValue="id" />
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
										<label for="nome">Perfis disponíveis</label>
										<form:select path="avaliableProfiles" cssClass="form-select form-select-lg" id="avaliableRoles">
											<form:options items="${user.avaliableProfiles}" itemLabel="name" itemValue="id" />
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
									<a href="${contextPath}/users">
										<button type="button" class="btn btn-secondary btn-block  mt-5">VOLTAR</button>
									</a>
								</div>
								<div class="col-lg-6 col-12">
									<button class="btn btn-primary btn-block mt-5" type="button" id="submitBtn">GRAVAR</button>
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
</script>

<script type="text/javascript">
	$(document).ready(function () {
	
	    $("#tel").inputmask({
	        mask: ["(99) 9999-9999", "(99) 99999-9999", ],
	        keepStatic: true
	    });
	
	    $("#tel").inputmask({
	        removeMaskOnSubmit: true
	    });
	
	    $('#submitBtn').click(function (event) {
	
	        var uuid = 'uuid=' + $("#uuid").val();
	        var email = '&email=' + $("#username").val();
	        var url = "/check/email?" + uuid + email;
	
	        $.getJSON(url, function (result) {
	
	            if (result.inUse) {
	
	                $("#div_err_email").text("email já cadastrado");
	                
	
	            } else {
		            
	                $("#roles > option").prop("selected", "selected");
	                $("#user").submit();
	                
	            }
	
	        });
	
	    });
	
	})
</script>
