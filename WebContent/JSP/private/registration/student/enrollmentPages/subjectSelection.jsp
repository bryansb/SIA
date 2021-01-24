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
		<table class="table table-hover" id="subject-list">
			<thead class="thead-dark">
				<tr>
					<th>Asignaturas Disponibles</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="subject" items="${subjectList}">
					<tr>
						<td>
						<div class="form-check">
							<label class="form-check-label w-100" for="${subject.id}">
							 	<input class="form-check-input" type="checkbox" name="subjectId" 
							 	value="${subject.id}" id="${subject.id}" />
							 	${subject.name}
							</label>
						</div>
					 	</td>
			  		</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
