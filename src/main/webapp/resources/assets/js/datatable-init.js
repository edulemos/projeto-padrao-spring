$(document).ready(function () {

	let tableUserId = 'users-table';
	let tableFuncionalidadesId = 'funcionalidades-table';
	let tableParametrosId = 'parametros-table';
	let tableProfilesId = 'profiles-table';
	let tableId = '';
	let formUrl = '';

	if ($('#' + tableParametrosId).length) {
		tableId = tableParametrosId;
		formUrl = 'parametros-form';
	}

	if ($('#' + tableUserId).length) {
		tableId = tableUserId;
		formUrl = 'users-form';
	}

	if ($('#' + tableFuncionalidadesId).length) {
		tableId = tableFuncionalidadesId;
		formUrl = 'funcionalidades-form';
	}

	if ($('#' + tableProfilesId).length) {
		tableId = tableProfilesId;
		formUrl = 'profiles-form';
	}


	let btns = [{
			text: '<i style="font-size: 1.0rem;" class="bi bi-search"></i>',
			titleAttr: 'PESQUISAR',
			className: 'btn btn-primary',
			action: function (e, dt, button, config) {
				var myModal = new bootstrap.Modal(document.getElementById('search-modal'), {
					keyboard: false
				})
				myModal.show();

			}
		},
		{
			text: '<i style="font-size: 1.0rem;font-weight: bold;"  class="bi bi-plus-circle-fill"></i>',
			titleAttr: 'CADASTRAR',
			className: 'btn btn-primary',
			action: function (e, dt, button, config) {
				window.location = formUrl;
			}
		}
	];

	if (tableId) {
		$('#' + tableId).DataTable({
			"language": ptbr,
			"paging": true,
			"ordering": false,
			"info": false,
			"dom": "Bfrtip",
			buttons: btns
		});
	}

});



var ptbr = {
	"sEmptyTable": "Nenhum registro encontrado",
	"sLoadingRecords": "A carregar...",
	"sProcessing": "A processar...",
	"sLengthMenu": "Mostrar _MENU_ registos",
	"sZeroRecords": "Nenhum registro encontrado",
	"sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registos",
	"sInfoEmpty": "Mostrando de 0 até 0 de 0 registos",
	"sInfoFiltered": "(filtrado de _MAX_ registos no total)",
	"sSearch": "FILTRAR:",
	"oPaginate": {
		"sFirst": "Primeiro",
		"sPrevious": "Anterior",
		"sNext": "Seguinte",
		"sLast": "Ultimo"
	},
	"oAria": {
		"sSortAscending": ": Ordenar colunas de forma ascendente",
		"sSortDescending": ": Ordenar colunas de forma descendente"
	}
};