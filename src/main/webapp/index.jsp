<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<link rel="stylesheet"
	href="node_modules/angular-material/angular-material.min.css">
<script src="node_modules/angular/angular.min.js"></script>
<script src="node_modules/angular-animate/angular-animate.min.js"></script>
<script src="node_modules/angular-aria/angular-aria.min.js"></script>
<script src="node_modules/angular-messages/angular-messages.min.js"></script>
<script src="node_modules/angular-resource/angular-resource.js"></script>
<script src="node_modules/angular-material/angular-material.min.js"></script>

<link rel="stylesheet"
	href="node_modules/datatables/media/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.1.0/css/responsive.dataTables.min.css">
<script src="node_modules/jquery/dist/jquery.min.js"></script>
<script src="node_modules/datatables/media/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/responsive/2.1.0/js/dataTables.responsive.min.js"></script>
<script src="js/resoluciones.js"></script>

</head>
<body ng-app="firstApplication" ng-cloak>
	<div id="dialogContainer" ng-controller="AppCtrl">
		<md-toolbar class="md-warn">
		<div class="md-toolbar-tools">
			<h2 class="md-flex">USCO - LCMS</h2>
		</div>
		</md-toolbar>

		<md-content flex layout-padding>

		<div layout-gt-xs="row">
			<span flex></span>
			<md-button class="md-primary md-raised"
				ng-click="editar('Adicionar')"> Adicionar </md-button>
			<md-button class="md-primary md-raised"
				ng-click="editar('Modificar')"> Modificar </md-button>
			<md-button class="md-primary md-raised" ng-click="editar('Eliminar')">
			Eliminar </md-button>
		</div>

		<table id="example" class="display responsive nowrap" cellspacing="0"
			width="100%">
			<thead>
				<tr>
					<th>Codigo</th>
					<th>fecha</th>
					<th>dependencia</th>
					<th>Descripcion</th>
					<th>Numero</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Codigo</th>
					<th>fecha</th>
					<th>dependencia</th>
					<th>Descripcion</th>
					<th>Numero</th>
				</tr>
			</tfoot>
		</table>

		</md-content>
	</div>

</body>
</html>