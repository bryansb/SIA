<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div class="col-12">
	<form class="mx-2 my-auto d-inline w-50" style="min-width: 100px" action="/SIA/StudentController" method="post">
   		<div class="input-group">
   			<input type="hidden" value="search" name="option"/>
       		<input id="search-student" name="studentName" class="form-control my-search-input" 
       			type="search" placeholder="Buscar Estudiante" aria-label="Search"/>
        	<button type="submit" value="" >
        		<i class="fa fa-search"></i> 
        	</button>
       	</div>
    </form>
	<h2>Estudiante</h2>
	<div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Nombre y Apellido</th>
					<th scope="col">Email</th>
					<th scope="col">Contraseña</th>
					<th scope="col">Cedula</th>
					<th scope="col">Telefono</th>
					<th scope="col">Direccion</th>
					<th scope="col">Fecha de Nacimiento</th>
					<th scope="col">Genero</th>
					<th scope="col">Gestionar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="car" scope="request" value="${student}" />
				<tr>
					<td>${stu.fullName}</td>
						<td>${stu.email}</td>
						<td>${stu.password}</td>
						<td>${stu.dni}</td>
						<td>${stu.phone}</td>
						<td>${stu.address}</td>
						<td>${stu.birthdate}</td>
						<td>${stu.gender}</td>
					<td>
						<button class="btn btn-secondary">Editar</button>
						<button class="btn btn-danger">Eliminar</button>
					</td>
				</tr>
			</tbody>
			
		</table>
	</div>
</div>