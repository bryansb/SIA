<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-12">
	<div class="row">
		<div class="col-12 my-4">
			<h2>Matriculación para ${inscription.career.name}</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<h3>Resumen</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="row">
				<table class="table col-12" id="subject-list">
					<thead class="thead-dark">
						<tr>
							<th>Asignatura</th>
							<th>Nivel</th>
							<th>Espacio Físico</th>
							<th>Horas</th>
							<th>Horario</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="grade" items="${enrollment.gradeList}">
							<tr>
								<td>
									${grade.group.subject.name}
							 	</td>
							 	<td>
							 		${grade.group.subject.level}
							 	</td>
							 	<td>
									${grade.group.physicalSpace}
								</td>
							 	<td>
									${grade.group.subject.hours}
								</td>
								<td>
									Hacer un método para el horario
								</td>
					  		</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>