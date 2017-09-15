
<div class="sidebar-nav">
	<ul>

		    <!--MENU ADMIN-->  	
			<sec:authorize access="hasAnyRole('MANTER_USUARIOS, MANTER_PERFIS')">
		
			<li><a href="#" data-target=".admin-menu" class="nav-header collapsed" data-toggle="collapse">
			<i class="fa fa-cog"></i><spring:message code="menu.admin"/><i class="fa fa-collapse"></i></a></li>
			<li>		
				<ul class="admin-menu nav nav-list collapse">
				
				<!--USUARIOS -->
			  	<sec:authorize access="hasRole('MANTER_USUARIOS')">
					<li><a href="<c:url value="/usuarios/list"/>"><spring:message code="menu.admin.usuarios.sistema"/></a></li>
				</sec:authorize>
					
				<!--PERFIS -->
			  	<sec:authorize access="hasRole('MANTER_PERFIS')">
					<li><a href="<c:url value="/perfis/list"/>"><spring:message code="menu.admin.perfis.sistema"/></a></li>
				</sec:authorize>
				
				</ul>			
			</li>
			  
		   </sec:authorize>
		
 		<!--FIM MENU ADMIN-->
		

		<!-- CADASTROS -->
		<li><a href="#" data-target=".user-menu" class="nav-header collapsed" data-toggle="collapse">
		<i class="fa fa-child"></i><spring:message code="menu.cadastros"/><i class="fa fa-collapse"></i></a></li>
		<li>
			<ul class="user-menu nav nav-list collapse">
				<li><a href="<c:url value="/cadastros/produto"/>"><span class="fa fa-caret-right"></span><spring:message code="menu.cadastros.produtos"/></a></li>
			</ul>
		</li>
		
		
		<!-- RELATORIOS -->
		<li><a href="#" data-target=".report-menu" class="nav-header collapsed" data-toggle="collapse">
		<i class="fa fa-bar-chart"></i>Relatórios<i class="fa fa-collapse"></i></a></li>
		<li>
			<ul class="report-menu nav nav-list collapse">
				<li><a href="<c:url value="/relatorio/usuarios"/>" target="_blank"><span class="fa fa-caret-right"></span>Usuários Sistema</a></li>
			</ul>
		</li>

		<li><a href="<c:url value="/logout"/>" data-target=".sair" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-power-off"></i><spring:message code="menu.sair"/></a></li>

	</ul>
</div>
