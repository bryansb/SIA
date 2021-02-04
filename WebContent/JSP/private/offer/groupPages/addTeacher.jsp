<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<h4>Profesores</h4>
	<form class="text-left form col-12" id="teacher-form" action="/SIA/GroupController" method="post">
		<div class="form-teacher">
			<input type="hidden" value="addTeacher" name="option"/>
			<label for="teacherId">Profesor:</label>
			 <select name="teacherId" class="form-control">
			 	<option value="NaN" selected>Seleccione</option>
		    	<c:forEach var="teacher" items="${teachers}">
		   			<option value="${teacher.id}">${teacher.name}</option>
				</c:forEach>
			</select>
		</div>
	</form>
	<div id="table-career">
		<H2>Lista de Profesores Asignados a este grupo</H2>
		<table class="table display" id="table-content">
			<thead>
				<tr>
					<th scope="col">Nombre</th>
					<th scope="col">Cédula</th>
					<th scope="col">Correo</th>
					<th scope="col">Gestionar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableTeacher" scope="request" value="${teachers}" />
		    	<c:forEach var="tea" items="${tableTeacher}">
					<tr>
						<td>${tea.name}</td>
						<td>${tea.time}</td>
						<td>
							<button class="btn btn-secondary" onclick="readCareer(${car.id});">Editar</button>
							<button type="button" class="btn btn-danger" onclick="deleteCareer(${car.id});">Eliminar</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">loadFunction();</script>
	</div>
</div>

