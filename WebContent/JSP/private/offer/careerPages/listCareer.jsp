<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12" id="tableCareer">
	<div id="table-career">
		<H2>Lista de Carreras</H2>
		<table class="table display" id="table-content">
			<thead>
				<tr>
					<th scope="col">Nombre</th>
					<th scope="col">Tiempo</th>
					<th scope="col">Gestionar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableCareer" scope="request" value="${careers}" />
		    	<c:forEach var="car" items="${tableCareer}">
					<tr>
						<td>${car.name}</td>
						<td>${car.time}</td>
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
