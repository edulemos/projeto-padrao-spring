

<div class="sidebar-menu">

	<form id="logoutForm" method="POST" action="${contextPath}/logout"></form>

	<ul class="menu">

		<sec:authorize access="hasRole('ROLE_USERS_LIST')">
			<li class="sidebar-item"><a class='sidebar-link' href="${contextPath}/users"><i class="bi bi-people-fill"></i> <span>USUÁRIOS</span></a></li>
		</sec:authorize>


		<sec:authorize access="hasRole('ROLE_PROFILES_LIST')">
			<li class="sidebar-item"><a class='sidebar-link' href="${contextPath}/profiles"><i class="bi bi-shield-check"></i> <span>PERFIS</span></a></li>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_FUNCIONALIDADE_LIST')">
			<li class="sidebar-item"><a class='sidebar-link' href="${contextPath}/funcionalidades"><i class="bi bi-ui-checks-grid"></i> <span>FUNCIONALIDADES</span></a></li>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_PARAMETER_LIST')">
			<li class="sidebar-item"><a class='sidebar-link' href="${contextPath}/parametros"><i class="bi bi-ui-checks"></i> <span>PARAMETROS</span></a></li>
		</sec:authorize>

		<li class="sidebar-item"><a class='sidebar-link' href="${contextPath}/account-data"><i class="bi bi-person-circle"></i><span>DADOS
					CADASTRO</span></a></li>
		<li class="sidebar-item"><a class='sidebar-link' href="${contextPath}/account-pwd"><i class="bi bi-shield-lock"></i> <span>ALTERAR
					SENHA</a></span</li>

		<li class="sidebar-item"><a class='sidebar-link' href="${contextPath}/buscacep"> <i class="bi bi-house-fill"></i><span>CONSULTACEP</span></a></li>

		<li class="sidebar-item"><a class='sidebar-link' href="${contextPath}/h2-console" target="_blank"> <i class="bi bi-archive-fill"></i><span>DATABASE</span></a></li>

		<li class="sidebar-item"><a class='sidebar-link' onclick="document.forms['logoutForm'].submit()"> <i class="bi bi-box-arrow-left"></i> <span>SAIR</span></a></li>

	</ul>

</div>
