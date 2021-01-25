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
			<label for="name">Nombre:</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="Nombre" required>
		</div>
		<div class="form-group ">
			<label for="time">Tiempo:</label>
			<input type="number" id="time" name="time" class="form-control" placeholder="0" min="0" step="1" >	
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