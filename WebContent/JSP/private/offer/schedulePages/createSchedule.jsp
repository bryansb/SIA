<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<a href="/SIA/GroupController">
		<button class="btn btn-secondary">Regresar</button>
	</a>
	<c:choose>
		<c:when test="${schedule.editable}">
			<h2>Editar Horario</h2>
		</c:when>
		<c:otherwise>
			<h2>Crear Horario</h2>
		</c:otherwise>
	</c:choose>
	<form class="text-left form col-12" id="schedule-form" action="/SIA/ScheduleController" method="post">
		<input type="hidden" value="${groupId}" name="groupId"/>
		<c:choose>
			<c:when test="${schedule.editable}">
				<input type="hidden" value="${schedule.id}" name="scheduleId"/>
				<input type="hidden" value="update" name="option"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="create" name="option"/>
			</c:otherwise>
		</c:choose>
		
		<div class="form-group">
			<label for="day">Día:</label>
			<input type="text" id="day" name="day" class="form-control" placeholder="Día" value="${schedule.day}" required/>
		</div>
		<div class="form-group ">
			<label for=startTime>Hora de Inicio:</label>
			<input type="text" id="startTime" name="startTime" class="form-control" placeholder="Hora Inicio" value="${schedule.startTime}" required/>
		</div>
		<div class="form-group ">
			<label for=endTime>Hora Fin:</label>
			<input type="text" id="endTime" name="endTime" class="form-control" placeholder="Hora Fin" value="${schedule.endTime}" required/>
		</div>
		<c:choose>
			<c:when test="${schedule.editable}">
				<input type="submit" class="btn btn-success" value="Aceptar"/>
			</c:when>
			<c:otherwise>
				<input type="submit" class="btn btn-primary" value="Agregar"/>
			</c:otherwise>
		</c:choose>
	</form>
</div>