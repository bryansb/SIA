<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<H2>Crear Materia</H2>
	<form class="text-left form col-12" id="subject-form" action="/SIA/SubjectController" method="post">
		<input type="hidden" value="create" name="option"/>
		<div class="form-group ">
			<label for="name">Nombre:</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="Nombre" required>
		</div>
		<div class="form-group ">
			<label for="credits">Número de Creditos:</label>
			<input type="number" id="credits" name="credits" class="form-control" placeholder="0" min="0" step="1" >	
		</div>
		<div class="form-group ">
			<label for="cost">Precio:</label>
			<input type="number" id="cost" name="cost" class="form-control" placeholder="0.00" min="0.00" step="any">	
		</div>
		<div class="form-group ">
			<label for="hours">Número de Horas:</label>
			<input type="number" id="hours" name="hours" class="form-control" placeholder="0" min="0" step="1" >	
		</div>
		<div class="form-group ">
			<label for="level">Nivel:</label>
			<input type="number" id="level" name="level" class="form-control" placeholder="0" min="0" step="1" >	
		</div>
		
		<div class="form-group ">
			<label for="careerId">Carreras:</label>
			 <select name="careerId" class="form-control">
			 	<option value="NaN" selected>Seleccione</option>
		    	<c:forEach var="career" items="${careers}">
	    			<option value="${career.id}">${career.name}</option>
				</c:forEach>
			</select>
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