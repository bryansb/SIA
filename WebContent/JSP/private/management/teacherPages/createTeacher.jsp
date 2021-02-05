<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div class="col-5">
	<c:choose>
		<c:when test="${teacher.editable}">
			<h2>Editar Profesor</h2>
		</c:when>
		<c:otherwise>
			<h2>Crear Profesor</h2>
		</c:otherwise>
	</c:choose>
	<form class="text-left form col-12" id="teacher-form" action="/SIA/TeacherController" method="post">
		<c:choose>
			<c:when test="${teacher.editable}">
				<input type="hidden" value="${teacher.id}" name="teacherId"/>
				<input type="hidden" value="update" name="option"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="create" name="option"/>
			</c:otherwise>
		</c:choose>
		<div class="form-group ">
			<label for="fullName">Nombre:</label>
			<input type="text" id="fullName" name="fullName" class="form-control" placeholder="Nombre" value="${tea.fullName}" required/>	
		</div>
		<div class="form-group ">
			<label for="email">Email:</label>
			<input type="email" id="email" name="email" class="form-control" placeholder="Email" value="${tea.email}"required/>	
		</div>
		<div class="form-group ">
			<label for="dni">Cedula:</label>
			<input type="text" id="dni" name="dni" class="form-control" placeholder="Cedula" value="${tea.dni}" required/>	
		</div>
		<div class="form-group ">
			<label for="phone">Telefono:</label>
			<input type="text" id="phone" name="phone" class="form-control" placeholder="Telefono" value="${tea.phone}" required/>	
		</div>
		<div class="form-group ">
			<label for="address">Direccion:</label>
			<input type="text" id="address" name="address" class="form-control" placeholder="Direccion" value="${tea.address}" required/>	
		</div>
		<div class="form-group ">
			<label for="address">Salario:</label>
			<input type="text" id="salary" name="salary" class="form-control" placeholder="Salario" value="${tea.salary}" required/>	
		</div>
		<c:choose>
			<c:when test="${teacher.editable}">
				<input type="submit" class="btn btn-success" value="Aceptar"/>
			</c:when>
			<c:otherwise>
				<input type="submit" class="btn btn-primary" value="Registrar"/>
			</c:otherwise>
		</c:choose>
	</form>
</div>