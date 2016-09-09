var app = angular.module("app", []);

app.controller('ctrl', function($scope, perfilService) {

	$scope.perfisAssociados = [];
	$scope.perfisDisponiveis = [];
	$scope.perfil = {};

	perfilService.perfisDisponiveis(id).success(function(data) {
		$scope.perfisDisponiveis = data;
	});

	perfilService.perfisAssociados(id).success(function(data) {
		$scope.perfisAssociados = data;
	});

	$scope.adicionarPerfil = function(perfil) {
		
		perfilService.adicionarPerfil(perfil.id, id).success(function(data) {
			console.log(data);
			addMessage(data.message);
			
			perfilService.perfisDisponiveis(id).success(function(data) {
				$scope.perfisDisponiveis = data;
			});

			perfilService.perfisAssociados(id).success(function(data) {
				$scope.perfisAssociados = data;
			});
			
		});
		
	}

	$scope.excluirPerfil = function(perfil) {
		
		perfilService.excluirPerfil(perfil.id, id).success(function(data) {
			console.log(data);			
			addMessage(data.message);
			
			perfilService.perfisDisponiveis(id).success(function(data) {
				$scope.perfisDisponiveis = data;
			});

			perfilService.perfisAssociados(id).success(function(data) {
				$scope.perfisAssociados = data;
			});
			
		});
		
	}

	$scope.excluirTodosPerfis = function(userID) {
		
		perfilService.excluirTodosPerfis(userID).success(function(data) {
			console.log(data);
			addMessage(data.message);
			
			perfilService.perfisDisponiveis(userID).success(function(data) {
				$scope.perfisDisponiveis = data;
			});

			perfilService.perfisAssociados(userID).success(function(data) {
				$scope.perfisAssociados = data;
			});
			
		});
	}

	$scope.adicionarTodosPerfis = function(userID) {
		
		perfilService.adicionarTodosPerfis(userID).success(function(data) {
			console.log(data);
			addMessage(data.message);
			
			perfilService.perfisDisponiveis(userID).success(function(data) {
				$scope.perfisDisponiveis = data;
			});

			perfilService.perfisAssociados(userID).success(function(data) {
				$scope.perfisAssociados = data;
			});
			
		});
		
		
	}

});

app.service("perfilService", function($http, config) {

	this.perfisDisponiveis = function(id) {
		return $http.get(config.baseUrl + "/usuarios/perfisdiponiveis/user/" + id);
	};

	this.perfisAssociados = function(id) {
		return $http.get(config.baseUrl + "/usuarios/perfisassociados/user/" + id);
	};
	
	this.adicionarPerfil = function(perfilId, userID) {
		return $http.get(config.baseUrl + "/usuarios/"+ userID + "/add/perfil/" + perfilId);
	};
	
	this.excluirPerfil = function(perfilId, userID) {
		return $http.get(config.baseUrl + "/usuarios/"+ userID + "/delete/perfil/" + perfilId);
	};
	
	this.excluirTodosPerfis = function(userID) {
		return $http.get(config.baseUrl + "/usuarios/"+ userID + "/delete/todosperfis");
	};
	
	this.adicionarTodosPerfis = function(userID) {
		return $http.get(config.baseUrl + "/usuarios/"+ userID + "/add/todosperfis");
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


