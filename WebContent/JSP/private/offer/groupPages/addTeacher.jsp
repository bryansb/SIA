<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	
	<jsp:attribute name="js">
		<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>
		<script src="/SIA/resources/js/offer/crud_group.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>
	</jsp:attribute>
	
	<jsp:attribute name="title">
		Agregar Docentes
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="left">
		<jsp:include page="/WEB-INF/templates/secretary_menu.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	
	
	<jsp:body>
		<div class="col-8" id="teacherForm">
			<a href="/SIA/GroupController">
				<button class="btn btn-secondary">Regresar</button>
			</a>
			<h4>Docentes</h4>
			<form class="text-left form col-12" id="teacher-form" action="/SIA/GroupController" method="post">
				<div class="form-teacher">
					<input type="hidden" value="${groupId}" name="groupId"/>
					<input type="hidden" value="addTeacher" name="option"/>
					
					<div class="form-group">
						<label for="teacherId">Profesor:</label>
							<select name="teacherId" size="10" class="custom-select custom-select-md">
						 	<option value="NaN" selected>Seleccione</option>
					    	<c:forEach var="teacher" items="${teachers}">
					   			<option value="${teacher.id}">${teacher.fullName}</option>
							</c:forEach>
						</select>
					</div>
					 
					<input type="submit" class="btn btn-primary" value="Registrar"/>
				</div>
			</form>
			<div id="tableTeacher">
				<div id="table-teacher">
					<H2>Lista de Profesores Asignados a este grupo</H2>
					<table class="table display" id="table-content-teacher">
						<thead>
							<tr>
								<th scope="col">Nombre</th>
								<th scope="col">Cédula</th>
								<th scope="col">Correo</th>
								<th scope="col">Gestionar</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="tableTeacher" scope="request" value="${teacherList}" />
					    	<c:forEach var="tea" items="${tableTeacher}">
								<tr>
									<td>${tea.fullName}</td>
									<td>${tea.dni}</td>
									<td>${tea.email}</td>
									<td>
										<button type="button" class="btn btn-danger" onclick="deleteTeacher(${tea.id},${groupId});">Eliminar</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<script type="text/javascript">loadFunctionTeacher();</script>
		</div>
	</jsp:body>
</t:genericpage>