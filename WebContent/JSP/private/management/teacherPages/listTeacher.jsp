<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12" id="tableTeacher">
	<div id="table-teacher">
		<H2>Lista de Grupos</H2>
		<table class="table display" id="table-content-teacher">
			<thead>
				<tr>
					<th scope="col">Nombre y Apellido</th>
					<th scope="col">Email</th>
					<th scope="col">Cedula</th>
					<th scope="col">Salary</th>
					<th scope="col">Titulos</th>
					<th scope="col">Gestionar Profesor</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableTeacher" scope="request" value="${teachers}" />
		    	<c:forEach var="tea" items="${tableTeacher}">
					<tr>
						<td>${tea.fullName}</td>
						<td>${tea.email}</td>
						<td>${tea.dni}</td>
						<td>${tea.salary}</td>
						<td><button class="btn btn-secondary" onclick="addDegree(${tea.id});">Revisar</button></td>
						<td>
							<button class="btn btn-secondary" onclick="readTeacher(${tea.id});">Editar</button>
							<button class="btn btn-danger" onclick="deleteTeacher(${tea.id});">Eliminar</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">loadFunction();</script>
	</div>
</div>
