<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<H2>Crear Grupo</H2>
	<form class="text-left form col-12" id="subject-form" action="/SIA/GroupController" method="post">
		<input type="hidden" value="create" name="option"/>
		<div class="form-group ">
			<label for="academicPeriod">Periodo Academico:</label>
			<input type="text" id="academicPeriod" name="academicPeriod" class="form-control" placeholder="Periodo Academico" required>
		</div>
		<div class="form-group ">
			<label for="physicalSpace">Espacio Físico:</label>
			<input type="text" id="physicalSpace" name="physicalSpace" class="form-control" placeholder="Espacio Físico" required>
		</div>
		<div class="form-group ">
			<label for="quota">Cuota:</label>
			<input type="number" id="quota" name="quota" class="form-control" placeholder="0" min="0" step="1" >	
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
		<div class="form-group ">
			<label for="teacherId">Profesor:</label>
			 <select name="teacherId" class="form-control">
			 	<option value="NaN" selected>Seleccione</option>
		    	<c:forEach var="teacher" items="${teachers}">
	    			<option value="${teacher.id}">${teacher.name}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group ">
			<label for="scheduleId">Horario:</label>
			 <select name="teacherId" class="form-control">
			 	<option value="NaN" selected>Seleccione</option>
		    	<c:forEach var="teacher" items="${teachers}">
	    			<option value="${teacher.id}">${teacher.name}</option>
				</c:forEach>
			</select>
			<button>Agregar Horario</button>
		</div>
		<input type="submit" class="btn btn-primary" value="Registrar"/>
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