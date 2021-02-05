<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div class="col-12" id="tableStudent">
	<div id="table-student">
		<H2>Lista de Estudiantes</H2>
		<table class="table display" id="table-content">
			<thead>
				<tr>
					<th scope="col">Nombre y Apellido</th>
					<th scope="col">Email</th>
					<th scope="col">Cedula</th>
					<th scope="col">Gestionar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableStudent" scope="request" value="${students}" />
		    	<c:forEach var="stu" items="${tableStudent}">
					<tr>
						<td>${stu.fullName}</td>
						<td>${stu.email}</td>
						<td>${stu.dni}</td>
						<td>
						<c:choose>
							<c:when test="${stu.deleted}">
								<button type="button" class="btn btn-success" onclick="deleteStudent(${stu.id});">Restaurar</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-danger" onclick="deleteStudent(${stu.id});">Eliminar</button>
							</c:otherwise>
						</c:choose>
							<button class="btn btn-secondary" onclick="readStudent(${stu.id});">Editar</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">loadFunction();</script>
	</div>
</div>