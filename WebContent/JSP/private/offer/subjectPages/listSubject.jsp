<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12" id="tableSubject">
	<div id="table-subject">
		<H2>Lista de Materias</H2>
		<table class="table display" id="table-content">
			<thead>
				<tr>
					<th scope="col">Nombre</th>
					<th scope="col">#Creditos</th>
					<th scope="col">Precio</th>
					<th scope="col">Horas</th>
					<th scope="col">Nivel</th>
					<th scope="col">Carrera</th>
					<th scope="col">Gestionar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableSubject" scope="request" value="${subjects}" />
		    	<c:forEach var="sub" items="${tableSubject}">
					<tr>
						<td>${sub.name}</td>
						<td>${sub.credits}</td>
						<td>${sub.cost}</td>
						<td>${sub.hours}</td>
						<td>${sub.level}</td>
						<td>${sub.career.name}</td>
						<td>
							<button class="btn btn-secondary" onclick="readSubject(${sub.id});">Editar</button>
							<c:choose>
								<c:when test="${sub.deleted}">
									<button type="button" class="btn btn-success" onclick="deleteSubject(${sub.id});">Restaurar</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-danger" onclick="deleteSubject(${sub.id});">Eliminar</button>
								</c:otherwise>
							</c:choose>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">loadFunction();</script>
	</div>
</div>
