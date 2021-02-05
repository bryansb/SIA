<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="js">
		<script src="/SIA/resources/js/accounting/account_crud.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="title">
	Gestión de Cuentas
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
				<h2>Gestión de Cuentas</h2>
			</div>
		</div>
		<div id="master">
		<div id="slave">
		<div class="row justify-content-center">
		
			<div class="col-6" id="master-form" >
				<div id="slave-form">
					<form onsubmit="createAccount(); return false;" id="account-form">
						<c:choose>
							<c:when test="${empty accountRead}">
								<input type="hidden" value="create" name="option"/>
							</c:when>
							<c:otherwise>
								<input type="hidden" value="update" name="option"/>
								<input type="hidden" value="${accountRead.id}" name="accountId"/>
							</c:otherwise>
						</c:choose>
						<div class="form-group">
							<label for="name">Nombre de Cuenta:</label>
	    					<input type="text" class="form-control" id="name" name="name" placeholder="Nombre de Cuenta" 
	    					value="${accountRead.name}" required>
						</div>
						<div class="form-group">
							<label for="balance">Balance:</label>
	    					<input type="number" class="form-control" id="balance" name="balance" placeholder="Balance"  
	    					value="${accountRead.balance}" required>
						</div>
						<div class="form-group">
							<label for="accountTypeId">Tipo de Cuenta:</label>
							<select class="custom-select custom-select-md" size="10" id="accountTypeId" name="accountTypeId" required>
								<c:forEach items="${accountTypeList}" var="at">
									<c:choose>
										<c:when test="${accountRead.accountType.id == at.id}">
											<option value="${at.id}" selected>${at.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${at.id}">${at.name}</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
							<div class="invalid-feedback">Seleccione un tipo de cuenta</div>
						</div>
						<div class="form-group">
							<button class="btn btn-primary" type="submit">
								<i class="fas fa-plus-square"></i>&ensp;Enviar
							</button>
						</div>
					</form>
				</div>
			</div>
			<div class="col-6">
				<div class="table-responsive">
	  				<table class="table" >
	  					<thead class="thead-dark">
	  						<tr>
	  							<th>Nombre</th>
	  							<th>Balance</th>
	  							<th>Tipo de Cuenta</th>
	  							<th>Acción</th>
	  						</tr>
	  					</thead>
	  					<tbody>
	  					<c:forEach var="a" items="${accountList}">
	  						<tr>
	  							<td>${a.name}</td>
	  							<td>${a.balance}</td>
	  							<td>${a.accountType.name}</td>
	  							<td>
	  								<button class="btn btn-outline-info" type="button" onclick="editAccount(${a.id})">
										<i class="fas fa-plus-square"></i>&ensp;Editar
									</button>
	  							</td>
	  						</tr>
	  					</c:forEach>
	  					</tbody>
	  				</table>
  				</div>
			</div>
		 </div>
		</div>
		</div>
	</jsp:body>
	
</t:genericpage>
