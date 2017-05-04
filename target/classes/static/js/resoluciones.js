var table;
var aplicacionTitulo = "Gestión de Resoluciones";
angular.module('firstApplication', [ 'ngMaterial', 'ngResource' ]).controller(
		'AppCtrl',
		function($scope, $http, $mdDialog, $mdMedia, $resource) {
			$scope.status = '  ';
			$scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');

			var url = 'resoluciones/';
			var resolucionResource = $resource(url, {
				codigo : '@codigo'
			}, {
				adicionar : {
					url : url,
					method : 'POST',
					params : {}
				},
				modificar : {
					url : url + '/:codigo',
					method : 'PUT',
					params : {}
				},
				eliminar : {
					url : url + '/:codigo',
					method : 'DELETE',
					params : {}
				}
			});

			function DialogController($scope, $http, $mdDialog, accion, items) {
				$scope.accion = accion;
				$scope.resolucion = items;
				$scope.hide = function() {
					$mdDialog.hide();
				};
				$scope.cancel = function() {
					$mdDialog.cancel();
				};
				$scope.answer = function(answer) {
					$mdDialog.hide(answer);
				};
			}

			function mostrarRespuesta(data) {
				table.ajax.reload();
				$mdDialog.show($mdDialog.alert().title(aplicacionTitulo)
						.textContent(data.mensaje).ariaLabel(aplicacionTitulo)
						.ok('Aceptar'));
			}

			$scope.editar = function(accion) {
				var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))
						&& $scope.customFullscreen;

				if (accion == "Adicionar") {
					$scope.resolucion = {};
				} else {
					if (accion != "Adicionar") {
						$scope.resolucion = table.row('.selected').data();
						if ($scope.resolucion.codigo == undefined) {
							alert("abortar");
						}
					}
				}
				$mdDialog.show({
					controller : DialogController,
					templateUrl : 'forma.resoluciones.jsp',
					parent : angular.element(document.body),
					fullscreen : useFullScreen,
					locals : {
						items : $scope.resolucion,
						accion : accion
					}
				}).then(
						function(answer) {
							console.log("Respuesta:" + accion);
							if (answer == "ok") {
								if (accion == "Adicionar") {
									resolucionResource.adicionar({},
											$scope.resolucion, function(data) {
												mostrarRespuesta(data);
											})
								}
								if (accion == "Modificar") {
									resolucionResource.modificar({},
											$scope.resolucion, function(data) {
												mostrarRespuesta(data);
											})
								}
								if (accion == "Eliminar") {
									resolucionResource.eliminar({},
											$scope.resolucion, function(data) {
												mostrarRespuesta(data);
											})
								}
							}
						});
			};
		});

$(document).ready(function() {
	table = $('#example').DataTable({
		"processing" : true,
		"serverSide" : true,
		"ajax" : "resoluciones",
		"columns" : [ {
			"data" : "codigo"
		}, {
			"data" : "fecha"
		}, {
			"data" : "dependencia"
		}, {
			"data" : "descripcion"
		}, {
			"data" : "numero"
		} ]
	});

	$('#example tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

});