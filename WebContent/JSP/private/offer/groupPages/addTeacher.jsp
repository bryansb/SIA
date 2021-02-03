<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="form-group ">
	<label for="teacherId">Profesor:</label>
	 <select name="teacherId" class="form-control">
	 	<option value="NaN" selected>Seleccione</option>
    	<c:forEach var="teacher" items="${teachers}">
   			<option value="${teacher.id}">${teacher.name}</option>
		</c:forEach>
	</select>
</div>
<h4>Agregar Horario</h4>
<div class="form-group ">
	<label for="day">Día:</label>
	<input type="text" id="day" name="day" class="form-control" placeholder="Día" required>
</div>
<div class="form-group ">
	<label for=startTime>Hora de Inicio:</label>
	<input type="text" id="startTime" name="startTime" class="form-control" placeholder="Hora Inicio" required>
</div>
<div class="form-group ">
	<label for=endTime>Hora Fin:</label>
	<input type="text" id="endTime" name="endTime" class="form-control" placeholder="Hora Fin" required>
</div>