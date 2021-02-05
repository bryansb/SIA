<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12" id="tableGroup">
	<div id="table-group">
		<H2>Lista de Grupos</H2>
		<table class="table display" id="table-content-group">
			<thead>
				<tr>
					<th scope="col">Periodo Academico</th>
					<th scope="col">Espacio Físico</th>
					<th scope="col">Cupos</th>
					<th scope="col">Materia</th>
					<th scope="col">Profesores</th>
					<th scope="col">Horario</th>
					<th scope="col">Gestionar Grupo</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableGroup" scope="request" value="${groups}" />
		    	<c:forEach var="gro" items="${tableGroup}">
					<tr>
						<td>${gro.academicPeriod}</td>
						<td>${gro.physicalSpace}</td>
						<td>${gro.quota}</td>
						<td>${gro.subject.name}</td>
						<td><button class="btn btn-secondary" onclick="addTeacher(${gro.id});">Revisar</button></td>
						<td>
							<a href="/SIA/ScheduleController?groupId=${gro.id}">
								<button class="btn btn-secondary">Revisar</button>
							</a>
						</td>
						<td>
							<button class="btn btn-secondary" onclick="readGroup(${gro.id});">Editar</button>
							<button class="btn btn-danger" onclick="deleteGroup(${gro.id});">Eliminar</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">loadFunction();</script>
	</div>
</div>
