
<div class="sidebar-nav">
	<ul>

		<li><a href="#" data-target=".admin-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-cog"></i>ADMIN<i
				class="fa fa-collapse"></i></a></li>
		<li>
			<ul class="admin-menu nav nav-list collapse">
				<li><a href="<c:url value="/admin/user/list"/>">Usuários Sistema</a></li>
			</ul>
		</li>

		<li><a href="#" data-target=".user-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-child"></i>CADASTROS<i
				class="fa fa-collapse"></i></a></li>
		<li>
			<ul class="user-menu nav nav-list collapse">
				<li><a href="<c:url value="/cadastro/produto"/>"><span class="fa fa-caret-right"></span> Produto</a></li>
			</ul>
		</li>

		<li><a href="<c:url value="/logout"/>" data-target=".sair" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-power-off"></i>SAIR</a></li>

	</ul>
</div>
