<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<c:choose>
		<c:when test="${career.editable}">
			<H2>Editar Carrera</H2>
		</c:when>
		<c:otherwise>
			<H2>Crear Carrera</H2>
		</c:otherwise>
	</c:choose>
	<form class="text-left form col-12" id="career-form" action="/SIA/CareerController" method="post">
		<c:choose>
			<c:when test="${career.editable}">
				<input type="hidden" value="${career.id}" name="careerId"/>
				<input type="hidden" value="update" name="option"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="create" name="option"/>
			</c:otherwise>
		</c:choose>
		<div class="form-group">
			<label for="name">Nombre:</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="Nombre" value="${career.name}" required>
		</div>
		<div class="form-group ">
			<label for="time">Tiempo:</label>
			<input type="number" id="time" name="time" class="form-control" placeholder="0" min="0" step="1" value="${career.time}" >	
		</div>
		<c:choose>
			<c:when test="${career.editable}">
				<input type="submit" class="btn btn-success" value="Aceptar"/>
			</c:when>
			<c:otherwise>
				<input type="submit" class="btn btn-primary" value="Registrar"/>
			</c:otherwise>
		</c:choose>
	</form>
</div>