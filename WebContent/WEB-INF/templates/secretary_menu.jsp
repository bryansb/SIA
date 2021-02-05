<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="row">
		<div class="col-12">
		    <div class="list-group" id="list-tab" role="tablist">
				<a class="list-group-item list-group-item-action" 
					 id="list-asignaturas-list" data-toggle="list" href="#list-asignaturas" role="tab" aria-controls="asignaturas">
					 <i class="fas fa-bars"></i>&ensp;Gestión de Asignaturas
				</a>
				<a class="list-group-item list-group-item-action" 
					 id="list-grupos-list" data-toggle="list" href="#list-grupos" role="tab" aria-controls="grupos">
					 <i class="fas fa-user-cog"></i>&ensp;Gestión de Grupos
				</a>
				<a class="list-group-item list-group-item-action" 
					 id="list-horarios-list" data-toggle="list" href="#list-horarios" role="tab" aria-controls="horarios">
					 <i class="far fa-calendar-alt"></i>&ensp;Gestión de Horarios
				</a>
				<a class="list-group-item list-group-item-action" 
					 href="/SIA/InscriptionController">
					 <i class="fas fa-address-card"></i>&ensp;Inscripciones
				</a>
				<a class="list-group-item list-group-item-action" 
					 href="/SIA/InscriptionController">
					 <i class="far fa-file-pdf"></i>&ensp;Emisión de Certificados
				</a>
		    </div>
  		</div>
	</div>
</div>