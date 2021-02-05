<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:genericpage>
	
	<jsp:attribute name="js">
		<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>
		<script src="/SIA/resources/js/accounting/diary_book.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>
	</jsp:attribute>
	
	<jsp:attribute name="title">
	Ingresos y Egresos
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="left">
		<jsp:include page="/WEB-INF/templates/secretary_menu.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	
	<jsp:body>
		<div class="row my-4">
			<div class="col-12">
				<h2>Ingresos y Egresos</h2>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-12">
				<div class="row justify-content-center">
					<div class="col-2 form-group">
						<label for="start">Fecha Inicial</label>
						<input class="form-control" type="date" name="start" id="start"/>
					</div>
					<div class="col-2 form-group">
						<label for="end">Fecha Final</label>
						<input class="form-control" type="date" name="end" id="end"/>
					</div>
					<div class="col-4">
						<div class="form-group">
						    <label for="accountId">Tipo de Cuenta</label>
						    <select class="form-control" id="accountId">
						    	<c:forEach var="account" items="${accountList}">
						    		<option value="${account.id}">${account.name}</option>
						    	</c:forEach>
						    </select>
				  		</div>
					</div>
					<div class="col-2 align-self-center">
						<button type="button" class="btn btn-primary" onclick="getDiaryBook();">
							<i class="fas fa-filter"></i>&ensp;Filtrar
						</button>
					</div>
				</div>
			</div>
			<div class="col-12">
				<div id="master">
					<div id="slave">
						<script>
							loadDataTable();
						</script>
						<table class="table display" id="amount-list">
							<thead class="thead-dark">
								<tr>
									<th>Fecha</th>
									<th>Descripción</th>
									<th>Precio U.</th>
									<th>Total</th>
									<th>Tipo</th>
									<th>Balance</th>
									<c:if test="${account.name eq 'CAJA CONTABLE'}">
										<th>Balance C. Contable</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:set var="balanceAccount" value="${account.balance}"/>
								<c:forEach var="amount" items="${account.amountList}">
									<tr>
										<td>${amount.date.time}</td>
										<td>${amount.description}</td>
										<td>${amount.unitPrice}</td>
										<td>${amount.total}</td>
										<td>
										<c:choose>
											<c:when test="${amount.type eq 'I'.charAt(0)}">
												<span class="text-success"><i class="fas fa-plus"></i></span>
											</c:when>
											<c:otherwise>
												<span class="text-warning"><i class="fas fa-minus"></i></span>
											</c:otherwise>
										</c:choose>
										</td>
										<td>${amount.balance}</td>
										<c:if test="${account.name eq 'CAJA CONTABLE'}">
											
												<c:choose>
													<c:when test="${amount.type eq 'I'.charAt(0)}">
														<c:set var="balanceAccountCurrent" value="${balanceAccount + amount.balance}"/>
													</c:when>
													<c:otherwise>
														<c:set var="balanceAccountCurrent" value="${balanceAccount - amount.balance}"/>
													</c:otherwise>
												</c:choose>
											<td>
												<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" 
												value="${balanceAccountCurrent}"/>
											</td>
											
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</jsp:body>
	
</t:genericpage>
