<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<H2>Lista de Carreras</H2>
	<div id="tableCareer">
		<TABLE class="table table-striped">
			<THEAD>
				<TR>
					<TH scope="col">Nombre</TH>
					<TH scope="col">Tiempo</TH>
					<TH scope="col">Editar</TH>
				</TR>
			</THEAD>
			<TBODY>
				<c:set var="tableCareer" scope="request" value="${careers}" />
		    	<c:forEach var="car" items="${tableCareer}">
					<TR>
						<TD>${car.name}</TD>
						<TD>${car.time}</TD>
						<TD><BUTTON class="btn btn-secondary">editar</BUTTON></TD>
					</TR>
				</c:forEach>
			</TBODY>
			
		</TABLE>
	</div>
</div>
