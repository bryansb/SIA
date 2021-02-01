<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="col-12">
	<form class="mx-2 my-auto d-inline w-50" style="min-width: 100px" action="/SIA/CareerController" method="post">
   		<div class="input-group">
   			<input type="hidden" value="search" name="option"/>
       		<input id="search-career" name="careerName" class="form-control my-search-input" 
       			type="search" placeholder="Buscar Carrera" aria-label="Search"/>
        	<button type="submit" value="" >
        		<i class="fa fa-search"></i> 
        	</button>
       	</div>
    </form>
	<h2>Carrera</h2>
	<div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Nombre</th>
					<th scope="col">Tiempo</th>
					<th scope="col">Gestionar</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="car" scope="request" value="${career}" />
				<tr>
					<td>${car.name}</TD>
					<td>${car.time}</TD>
					<td>
						<button class="btn btn-secondary">Editar</button>
						<button class="btn btn-danger">Eliminar</button>
					</td>
				</tr>
			</tbody>
			
		</table>
	</div>
</div>
