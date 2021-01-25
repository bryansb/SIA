<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<H2>Crear Carrera</H2>
	<form class="text-left form col-12" id="career-form" action="/SIA/CareerController" method="post">
		<input type="hidden" value="create" name="option"/>
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
		<input type="submit" class="btn btn-primary" value="Agregar"/>
		<!--
		<c:choose>
			<c:when test="${empty career.id}">
				<input type="button" id="register" class="btn btn-primary" value="Registrar" onclick="createCareer('career-form')">
			</c:when>
			<c:otherwise>
				<input type="button" id="accept"  class="btn btn-primary" value="Aceptar" onclick="updateCareer('career-form', ${currentPage})">
			</c:otherwise>
		</c:choose>
		-->
	</form>
</div>