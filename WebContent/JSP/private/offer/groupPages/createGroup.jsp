<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<c:choose>
		<c:when test="${group.editable}">
			<h2>Editar Grupo</h2>
		</c:when>
		<c:otherwise>
			<h2>Crear Grupo</h2>
		</c:otherwise>
	</c:choose>
	<form class="text-left form col-12" id="group-form" action="/SIA/GroupController" method="post">
		<c:choose>
			<c:when test="${group.editable}">
				<input type="hidden" value="${group.id}" name="groupId"/>
				<input type="hidden" value="update" name="option"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="create" name="option"/>
			</c:otherwise>
		</c:choose>
		<div class="form-group ">
			<label for="academicPeriod">Periodo Academico:</label>
			<input type="text" id="academicPeriod" name="academicPeriod" class="form-control" placeholder="Periodo Academico" value="${group.academicPeriod}" required>
		</div>
		<div class="form-group ">
			<label for="physicalSpace">Espacio Físico:</label>
			<input type="text" id="physicalSpace" name="physicalSpace" class="form-control" placeholder="Espacio Físico" value="${group.physicalSpace}" required>
		</div>
		<div class="form-group ">
			<label for="quota">Cuota:</label>
			<input type="number" id="quota" name="quota" class="form-control" placeholder="0" min="0" step="1" value="${group.quota}" required>	
		</div>
		<div class="form-group ">
			<label for="subjectId">Materias:</label>
			 <select name="subjectId" class="form-control">
			 	<option value="NaN" selected>Seleccione</option>
		    	<c:forEach var="subject" items="${subjects}">
	    			<option value="${subject.id}">${subject.name}</option>
				</c:forEach>
			</select>
		</div>
		<c:choose>
			<c:when test="${group.editable}">
				<input type="submit" class="btn btn-success" value="Aceptar"/>
			</c:when>
			<c:otherwise>
				<input type="submit" class="btn btn-primary" value="Registrar"/>
			</c:otherwise>
		</c:choose>
	</form>
</div>