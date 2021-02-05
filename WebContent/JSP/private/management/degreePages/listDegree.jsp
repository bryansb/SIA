<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12" id="tableDegree">
	<div id="table-degree">
		<H2>Lista de Titulos</H2>
		<table class="table display" id="table-content">
			<thead>
				<tr>
					<th scope="col">Nombre</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="tableDegree" scope="request" value="${degrees}" />
		    	<c:forEach var="deg" items="${tableDegree}">
					<tr>
						<td>${deg.name}</td>
						<td>
							<button class="btn btn-secondary" onclick="readDegree(${deg.id});">Editar</button>
							<button type="button" class="btn btn-danger" onclick="deleteDegree(${deg.id});">Eliminar</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript">loadFunction();</script>
	</div>
</div>
