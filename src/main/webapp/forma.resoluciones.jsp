<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<md-dialog aria-label="Formulario Resolucion" ng-cloak>
<form>
	<md-toolbar>
	<div class="md-toolbar-tools">
		<h2>Formulario Resoluciones</h2>
		<span flex></span>
		<md-button class="md-icon-button" ng-click="cancel()"> <md-icon
			md-svg-src="../img/icons/ic_close_24px.svg" aria-label="Close dialog"></md-icon>
		</md-button>
	</div>
	</md-toolbar>
	<md-dialog-content>
	<div class="md-dialog-content">
		<div layout-gt-xs="row">
			<md-input-container class="md-block"> <label>Codigo</label>
			<input ng-model="resolucion.codigo" id="codigo"> </md-input-container>
		</div>
		<div layout-gt-xs="row">
			<md-input-container class="md-block"> <label>Fecha</label>
			<input ng-model="resolucion.fecha" id="fecha"> </md-input-container>
		</div>
		<div layout-gt-xs="row">
			<md-input-container class="md-block"> <label>Dependencia</label>
			<input ng-model="resolucion.dependencia" id="dependencia"> </md-input-container>
		</div>
		<div layout-gt-xs="row">
			<md-input-container class="md-block"> <label>Descripcion</label>
			<input ng-model="resolucion.descripcion" id="descripcion"> </md-input-container>
		</div>
		<div layout-gt-xs="row">
			<md-input-container class="md-block"> <label>Numero</label>
			<input ng-model="resolucion.numero" id="numero"> </md-input-container>
		</div>
	</md-dialog-content>
	<md-dialog-actions layout="row"> <span flex></span>
	<md-button ng-click="answer('ok')"> {{ accion }} </md-button> <md-button
		ng-click="answer('cancelar')" style="margin-right:20px;">
	Cancelar </md-button> </md-dialog-actions>
</form>
</md-dialog>