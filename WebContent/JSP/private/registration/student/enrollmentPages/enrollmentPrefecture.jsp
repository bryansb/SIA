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
			<h3>Prefactura</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<ul class="list-group list-group-flush">
				<li class="list-group-item">Estudiante: Est. ${inscription.student.fullName}</li>
				<li class="list-group-item">C.I.: ${inscription.student.dni}</li>
				<li class="list-group-item">Carrera: ${inscription.career.name}</li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="row">
				<table class="table col-12" id="subject-list">
					<thead class="thead-dark">
						<tr>
							<th>Descripción</th>
							<th>Cantidad</th>
							<th>Precio Unitario</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="billDetail" items="${enrollment.billHead.billDetailList}">
							<tr>
								<td>
									${billDetail.description}
							 	</td>
							 	<td>
							 		${billDetail.quantity}
							 	</td>
							 	<td>
									${billDetail.unitPrice}
								</td>
							 	<td>
									${billDetail.total}
								</td>
					  		</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row text-right">
		<div class="col-12">
			<ul class="list-group list-group-flush">
				<li class="list-group-item">Subtotal: ${enrollment.billHead.subtotal}</li>
				<li class="list-group-item">Impuestos: ${enrollment.billHead.taxes}</li>
				<li class="list-group-item">IVA: ${enrollment.billHead.vat}</li>
				<li class="list-group-item">Total: ${enrollment.billHead.total}</li>
			</ul>
		</div>
	</div>
</div>