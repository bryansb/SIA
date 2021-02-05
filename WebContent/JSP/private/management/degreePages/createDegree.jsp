<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-6">
	<c:choose>
		<c:when test="${deg.editable}">
			<H2>Editar Titulo</H2>
		</c:when>
		<c:otherwise>
			<H2>Crear Titulo</H2>
		</c:otherwise>
	</c:choose>
	<form class="text-left col-12" id="degree-form" action="/SIA/DegreeController" method="post">
		<c:choose>
			<c:when test="${deg.editable}">
				<input type="hidden" value="${deg.id}" name="degreeId"/>
				<input type="hidden" value="update" name="option"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="create" name="option"/>
			</c:otherwise>
		</c:choose>
		<div class="form-group ">
			<label for="name">Nombre:</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="Nombre" value="${deg.name}" required/>	
		</div>
		<c:choose>
			<c:when test="${deg.editable}">
				<input type="submit" class="btn btn-success" value="Aceptar"/>
			</c:when>
			<c:otherwise>
				<input type="submit" class="btn btn-primary" value="Registrar"/>
			</c:otherwise>
		</c:choose>
	</form>
</div>