<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12" id="tableSchedule">
	<div id="table-schedule">
		<H2>Horario Asignado</H2>
		<table class="table display" id="table-content-schedule">
			<thead>
				<tr>
					<th scope="col">Día</th>
					<th scope="col">Hora de Inicio</th>
					<th scope="col">Hora Fin</th>
					<th scope="col">Gestionar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableSchedule" scope="request" value="${scheduleList}" />
		    	<c:forEach var="sch" items="${tableSchedule}">
					<tr>
						<td>${sch.day}</td>
						<td>${sch.startTime}</td>
						<td>${sch.endTime}</td>
						<td>
							<button class="btn btn-secondary" onclick="readSchedule(${sch.id},${groupId});">Editar</button>
							<button class="btn btn-danger" onclick="deleteSchedule(${sch.id},${groupId});">Eliminar</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">loadFunctionSchedule();</script>
	</div>
</div>