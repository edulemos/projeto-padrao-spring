<%@ include file="/template/header-sistema.jsp"%>

<fieldset>
	<legend><spring:message code="perfil-list.titulo"/></legend>
	
<div class="btn-toolbar list-toolbar">
	<a href="${pageContext.request.contextPath}/perfis/form/0">
		<button class="btn btn-primary" style="margin-left: 5px;">
			<i class="fa fa-plus"></i>&nbsp;<spring:message code="perfil-list.btn.cadastrar"/> 
		</button></a>	
		<button class="btn btn-primary" style="margin-left: 5px;" data-toggle="modal" data-target="#findModal">
			<i class="fa fa-search"></i>&nbsp;<spring:message code="perfil-list.btn.pesquisar"/>
		</button>		
	<div class="btn-group"></div>
</div>	

<table class="table table-striped table-bordered" id="dataTable">
	<thead>
		<tr>
			<th><spring:message code="perfil-list.label.nome" /></th>			
			<th><spring:message code="perfil-list.label.role" /></th>			
			<th><spring:message code="usuarios-list.label.acoes" /></th>			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listaPerfis}" var="perfil">
			<tr>
				<td>${perfil.nome}</td>
				<td>
				<c:forEach  items="${perfil.roles}" var="role">
				   <ul>
				 	 <li>${role.descricao}</li>
				   </ul>
			    </c:forEach>		
				</td>				
				<td style="text-align: center;">				 
					<a href="${pageContext.request.contextPath}/perfis/delete/${perfil.id}" onclick="return confirm('<spring:message code="perfil-list.msg.excluir" /> ${perfil.nome}?')"><i class="fa fa-trash fa-2x"></i></a> &nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/perfis/form/${perfil.id}"><i class="fa fa-pencil-square-o fa-2x"></i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="modal fade" id="findModal" tabindex="-1" role="dialog" aria-labelledby="findModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><spring:message code="perfil-list.modal.titulo" /></h4>
      </div>
      <div class="modal-body">
      
        <form:form modelAttribute="adminPerfilSearch" servletRelativeAction="/perfis/pesquisar" method="post">
				
			<div class="row">	
				<div class="form-group col-md-12">
					<label><spring:message code="perfil-list.modal.label" />:</label> <input type="text" name="textotPesquisa" class="form-control"/>
				</div>				
			</div>
	        
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" data-dismiss="modal">&nbsp;<spring:message code="perfil-list.btn.fechar" />&nbsp;</button>
	        <button type="submit" class="btn btn-primary"><spring:message code="perfil-list.btn.pesquisar" /></button>
	      </div>
		</form:form>        
      </div>
    </div>
  </div>
</div>

</fieldset>
<%@ include file="/template/footer-sistema.jsp"%>