<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	<jsp:body>
		<div class="row justify-content-center" id="pdf-content">
			<div class="col-12 text-center">
				<p>${enrollment.date.time}</p>
				<p>Estudiante: ${enrollment.inscription.student.fullName}</p>
				<p>Cédula: ${enrollment.inscription.student.dni}</p>
				<p>Periodo: ${enrollment.academicPeriod}</p>
				<table>
					<thead>
						<tr>
							<th>Descripción</th>
							<th>Cantidad</th>
							<th>Precio Unitario</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="detail" items="${enrollment.billHead.billDetailList}">
							<tr>
								<td>${detail.description}</td>
								<td>${detail.quantity}</td>
								<td>${detail.unitPrice}</td>
								<td>${detail.total}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<p>Subtotal: ${enrollment.billHead.subtotal}</p>
				<p>Impuestos: ${enrollment.billHead.taxes}</p>
				<p>IVA: ${enrollment.billHead.vat}</p>
				<p>Total: ${enrollment.billHead.total}</p>
			</div>
		</div>
	</jsp:body>
</t:genericpage>
