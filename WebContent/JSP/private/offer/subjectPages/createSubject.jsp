<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-6">
	<c:choose>
		<c:when test="${subject.editable}">
			<H2>Editar Materia</H2>
		</c:when>
		<c:otherwise>
			<H2>Crear Materia</H2>
		</c:otherwise>
	</c:choose>
	<form class="text-left form col-12" id="subject-form" action="/SIA/SubjectController" method="post">
		<c:choose>
			<c:when test="${subject.editable}">
				<input type="hidden" value="${subject.id}" name="subjectId"/>
				<input type="hidden" value="update" name="option"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="create" name="option"/>
			</c:otherwise>
		</c:choose>
		<div class="form-group ">
			<label for="name">Nombre:</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="Nombre" value="${subject.name}" required>
		</div>
		<div class="form-group ">
			<label for="credits">Número de Creditos:</label>
			<input type="number" id="credits" name="credits" class="form-control" placeholder="0" min="0" step="1" value="${subject.credits}" required>	
		</div>
		<div class="form-group ">
			<label for="cost">Precio:</label>
			<input type="number" id="cost" name="cost" class="form-control" placeholder="0.00" min="0.00" step="any" value="${subject.cost}" required>	
		</div>
		<div class="form-group ">
			<label for="hours">Número de Horas:</label>
			<input type="number" id="hours" name="hours" class="form-control" placeholder="0" min="0" step="1" value="${subject.hours}" required>	
		</div>
		<div class="form-group ">
			<label for="level">Nivel:</label>
			<input type="number" id="level" name="level" class="form-control" placeholder="0" min="0" step="1" value="${subject.level}" required>	
		</div>
		
		<div class="form-group ">
			<label for="careerId">Carreras:</label>
			 <select name="careerId" class="form-control" required>
			 	<c:choose>
			 		<c:when test="${!subject.editable}">
			 			<option value="NaN" selected>Seleccione</option>
			 		</c:when>
			 		<c:otherwise>
			 			<option value="${subject.career.id}">${subject.career.name}</option>
			 		</c:otherwise>
			 	</c:choose>
		    	<c:forEach var="career" items="${careers}">
	    			<option value="${career.id}">${career.name}</option>
				</c:forEach>
			</select>
		</div>
		<c:choose>
			<c:when test="${subject.editable}">
				<input type="submit" class="btn btn-success" value="Aceptar"/>
			</c:when>
			<c:otherwise>
				<input type="submit" class="btn btn-primary" value="Registrar"/>
			</c:otherwise>
		</c:choose>
	</form>
</div>