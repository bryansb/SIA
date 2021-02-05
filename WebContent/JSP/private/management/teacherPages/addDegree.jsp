<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	<jsp:attribute name="js">
		<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>
		<script src="/SIA/resources/js/management/crud_teacher.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>
	</jsp:attribute>
	
	<jsp:attribute name="title">
		Agregar Titulos
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
		<div class="col-8" id="degreeForm">
			<a href="/SIA/TeacherController">
				<button class="btn btn-secondary">Regresar</button>
			</a>
			<h4>Docentes</h4>
			<form class="text-left form col-12" id="degree-form" action="/SIA/TeacherController" method="post">
				<div class="form-degree">
					<input type="hidden" value="${teacherId}" name="teacherId"/>
					<input type="hidden" value="addDegree" name="option"/>
					
					<div class="form-group">
						<label for="degreeId">Titulos:</label>
							<select name="degreeId" size="10" class="custom-select custom-select-md">
						 	<option value="Ing" selected>Seleccione</option>
					    	<c:forEach var="deg" items="${degrees}">
					   			<option value="${deg.id}">${deg.name}</option>
							</c:forEach>
						</select>
					</div>
					<input type="submit" class="btn btn-primary" value="Registrar"/>
				</div>
			</form>
			<div id="tableDegree">
				<div id="table-degree">
					<H2>Lista de Titulos asignados a este profesor</H2>
					<table class="table display" id="table-content-degree">
						<thead>
							<tr>
								<th scope="col">Nombre</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="tableDegree" scope="request" value="${degreeList}" />
					    	<c:forEach var="deg" items="${tableDegree}">
								<tr>
									<td>${deg.name}</td>
									<td>
										<button type="button" class="btn btn-danger" onclick="deleteDegree(${deg.id},${teacherId});">Eliminar</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<script type="text/javascript">loadFunctionDegree();</script>
		</div>
	</jsp:body>
</t:genericpage>