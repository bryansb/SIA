<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<H2>Lista de Grupos</H2>
	<div id="tableCareer">
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Periodo Academico</th>
					<th scope="col">Espacio Físico</th>
					<th scope="col">Cuota</th>
					<th scope="col">Editar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableGroup" scope="request" value="${groups}" />
		    	<c:forEach var="gro" items="${tableGroup}">
					<tr>
						<td>${gro.academicPeriod}</td>
						<td>${gro.physicalSpace}</td>
						<td>${gro.quota}</td>
						<td><button class="btn btn-secondary">Editar</button></td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
	</div>
</div>
