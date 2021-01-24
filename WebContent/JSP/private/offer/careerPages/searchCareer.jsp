<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<form class="mx-2 my-auto d-inline w-50" style="min-width: 100px" action="/SIA/CareerController" method="get">
   		<div class="input-group">
   			<input type="hidden" value="search" name="option"/>
       		<input id="search-career" name="careerName" class="form-control my-search-input" type="search" placeholder="Buscar Carrera" aria-label="Search"/>
        	<input type="submit" value=""/>
       	</div>
    </form>
	<H2>Lista de Carreras</H2>
	<div id="tableCareer">
		<TABLE class="table table-striped">
			<THEAD>
				<TR>
					<TH scope="col">Nombre</TH>
					<TH scope="col">Tiempo</TH>
					<TH scope="col"></TH>
				</TR>
			</THEAD>
			<TBODY>
				<c:set var="car" scope="request" value="${career}" />
				<TR>
					<TD>${car.name}</TD>
					<TD>${car.time}</TD>
					<TD>a</TD>
				</TR>
			</TBODY>
			
		</TABLE>
	</div>
</div>
