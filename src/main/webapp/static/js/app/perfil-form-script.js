var app = angular.module("app", []);

app.controller('ctrl', function($scope, roleService) {

	$scope.rolesAssociadas = [];
	$scope.rolesDisponiveis = [];
	$scope.role = {};

	roleService.rolesDisponiveis(idPerfil).success(function(data) {
		$scope.rolesDisponiveis = data;
	});

	roleService.rolesAssociadas(idPerfil).success(function(data) {
		$scope.rolesAssociadas = data;
	});

	$scope.adicionarRole = function(role) {
		
		roleService.adicionarRole(role.id, idPerfil).success(function(data) {
			console.log(data);
			addMessage(data.message);
			
			roleService.rolesDisponiveis(idPerfil).success(function(data) {
				$scope.rolesDisponiveis = data;
			});

			roleService.rolesAssociadas(idPerfil).success(function(data) {
				$scope.rolesAssociadas = data;
			});
			
		});
		
	}

	$scope.excluirRole = function(role) {
		
		roleService.excluirRole(role.id, idPerfil).success(function(data) {
			console.log(data);			
			addMessage(data.message);
			
			roleService.rolesDisponiveis(idPerfil).success(function(data) {
				$scope.rolesDisponiveis = data;
			});

			roleService.rolesAssociadas(idPerfil).success(function(data) {
				$scope.rolesAssociadas = data;
			});
			
		});
		
	}

	$scope.excluirTodasRoles = function(idPerfil) {
		
		roleService.excluirTodasRoles(idPerfil).success(function(data) {
			console.log(data);
			addMessage(data.message);
			
			roleService.rolesDisponiveis(idPerfil).success(function(data) {
				$scope.rolesDisponiveis = data;
			});

			roleService.rolesAssociadas(idPerfil).success(function(data) {
				$scope.rolesAssociadas = data;
			});
			
		});
	}

	$scope.adicionarTodasRoles = function(idPerfil) {
		
		roleService.adicionarTodasRoles(idPerfil).success(function(data) {
			console.log(data);
			addMessage(data.message);
			
			roleService.rolesDisponiveis(idPerfil).success(function(data) {
				$scope.rolesDisponiveis = data;
			});

			roleService.rolesAssociadas(idPerfil).success(function(data) {
				$scope.rolesAssociadas = data;
			});
			
		});
		
		
	}

});

app.service("roleService", function($http, config) {

	this.rolesDisponiveis = function(id) {
		return $http.get(config.baseUrl + "/perfis/rolesdiponiveis/perfil/" + id);
	};

	this.rolesAssociadas = function(id) {
		return $http.get(config.baseUrl + "/perfis/rolesAssociadas/perfil/" + id);
	};
	
	this.adicionarRole = function(roleId, idPerfil) {
		return $http.get(config.baseUrl + "/perfis/"+ idPerfil + "/add/role/" + roleId);
	};
	
	this.excluirRole = function(roleId, idPerfil) {
		return $http.get(config.baseUrl + "/perfis/"+ idPerfil + "/delete/role/" + roleId);
	};
	
	this.excluirTodasRoles = function(idPerfil) {
		return $http.get(config.baseUrl + "/perfis/"+ idPerfil + "/delete/todasroles");
	};
	
	this.adicionarTodasRoles = function(idPerfil) {
		return $http.get(config.baseUrl + "/perfis/"+ idPerfil + "/add/todasroles");
	};

});

app.value("config", {
	baseUrl : '/spring'
});


app.directive('bsTooltip', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			$(element).hover(function() {
				$(element).tooltip('show');
			}, function() {
				$(element).tooltip('hide');
			});
		}
	};
});


function addMessage(msg){
	$(".msgs").hide();
	$("<div style=\"font-weight: bold; font-size: medium;text-transform:uppercase;\" class=\"msgs alert alert-info fade in\">&nbsp;<span class=\"glyphicon glyphicon-info-sign\"></span>&nbsp;" + msg + "</div>").insertAfter("#legend2");
	$(".alert").fadeTo(3000, 0).slideUp(1000, function(){
		$(this).remove(); 
	});
}


