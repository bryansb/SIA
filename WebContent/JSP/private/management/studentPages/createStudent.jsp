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
	<form class="text-left col-12" id="student-form" action="/SIA/StudentController" method="post">
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
			<label for="fullName">Nombre:</label>
			<input type="text" id="fullName" name="fullName" class="form-control" placeholder="Nombre" value="${studentRead.fullName}" required/>	
		</div>
		<div class="form-group ">
			<label for="email">Email:</label>
			<input type="email" id="email" name="email" class="form-control" placeholder="Email" value="${studentRead.email}"required/>	
		</div>
		<div class="form-group ">
			<label for="dni">Cedula:</label>
			<input type="text" id="dni" name="dni" class="form-control" placeholder="Cedula" value="${studentRead.dni}" required/>	
		</div>
		<div class="form-group ">
			<label for="phone">Telefono:</label>
			<input type="text" id="phone" name="phone" class="form-control" placeholder="Telefono" value="${studentRead.phone}" required/>	
		</div>
		<div class="form-group ">
			<label for="address">Direccion:</label>
			<input type="text" id="address" name="address" class="form-control" placeholder="Direccion" value="${studentRead.address}" required/>	
		</div>
		<div class="form-group ">
			<label for="birthdate">Fecha de Nacimiento:</label>
			<input type="text" id="birthdate" name="birthdate" class="form-control" placeholder="Fecha de Nacimiento" value="${studentRead.birthdate}" required/>	
		</div>
		<div class="form-group ">
			<label for="gender">Genero:</label>
			<input type="text" id="gender" name="gender" class="form-control" placeholder="Genero" value="${studentRead.gender}" required/>	
		</div>
		<input type="submit" class="btn btn-primary" value="Registrar"/>
	</form>
</div>