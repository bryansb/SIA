<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<H2>Lista de Carreras</H2>
	<div id="tableCareer">
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Nombre</th>
					<th scope="col">#Creditos</th>
					<th scope="col">Precio</th>
					<th scope="col">Horas</th>
					<th scope="col">Nivel</th>
					<th scope="col">Editar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableSubject" scope="request" value="${subjects}" />
		    	<c:forEach var="sub" items="${tableSubject}">
					<tr>
						<td>${sub.name}</td>
						<td>${sub.credits}</td>
						<td>${sub.cost}</td>
						<td>${sub.hours}</td>
						<td>${sub.level}</td>
						<td><button class="btn btn-secondary">Editar</button></td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
	</div>
</div>
