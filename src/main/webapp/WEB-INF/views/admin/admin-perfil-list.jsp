<%@ include file="/template/header-sistema.jsp"%>

<fieldset>
	<legend><spring:message code="admin-perfil-list.titulo"/></legend>

<table class="table table-striped table-bordered" id="dataTable">
	<thead>
		<tr>
			<th><spring:message code="admin-perfil-list.label.nome" /></th>			
			<th><spring:message code="admin-perfil-list.label.role" /></th>			
			<th><spring:message code="admin-user-list.label.acoes" /></th>			
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
					<a href="${pageContext.request.contextPath}/admin/perfil/${perfil.id}"><i class="fa fa-pencil-square-o fa-2x"></i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


</fieldset>
<%@ include file="/template/footer-sistema.jsp"%>