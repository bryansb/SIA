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
			<c:forEach var="subject" items="${subjectList}">
			<div class="row my-4">
				<div class="col-12">
					<h3>${subject.name}</h3>
				</div>
			</div>
			<div class="row">
				<table class="table table-hover col-12" id="subject-list">
					<thead class="thead-dark">
						<tr>
							<th>Periodo</th>
							<th>Espacio Físico</th>
							<th>Cupo</th>
							<th>Horario</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="group" items="${subject.groupList}">
							<tr>
								<td>
									<div class="form-check">
										<label class="form-check-label w-100" for="${group.id}">
										 	<input class="form-check-input groupId" type="radio" name="groupId-${subject.id}" 
										 	value="${group.id}" id="${group.id}" />
										 	${group.academicPeriod}
										</label>
									</div>
							 	</td>
							 	<td>
							 		${group.physicalSpace}
						 		</td>
							 	<td>
							 		${group.quota}
							 	</td>
							 	<td>
									Horario Pendiente, hacer un método
								</td>
					  		</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</c:forEach>
		</div>
	</div>
</div>