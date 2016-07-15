
<div class="sidebar-nav">
	<ul>

		<li><a href="#" data-target=".admin-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-cog"></i><spring:message code="menu.admin"/><i
				class="fa fa-collapse"></i></a></li>
		<li>
			<ul class="admin-menu nav nav-list collapse">
				<li><a href="<c:url value="/admin/user/list"/>"><spring:message code="menu.admin.usuarios.sistema"/></a></li>
				<li><a href="<c:url value="/admin/perfil/list"/>"><spring:message code="menu.admin.perfis.sistema"/></a></li>
			</ul>
		</li>

		<li><a href="#" data-target=".user-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-child"></i><spring:message code="menu.cadastros"/>
		<i class="fa fa-collapse"></i></a></li>
		<li>
			<ul class="user-menu nav nav-list collapse">
				<li><a href="<c:url value="/cadastros/produto"/>"><span class="fa fa-caret-right"></span><spring:message code="menu.cadastros.produtos"/></a></li>
			</ul>
		</li>

		<li><a href="<c:url value="/logout"/>" data-target=".sair" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-power-off"></i><spring:message code="menu.sair"/></a></li>

	</ul>
</div>
