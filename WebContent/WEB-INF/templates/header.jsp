<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark" >
		<div class="container-fluid col-11">
			<a class="navbar-brand" href="/SIA/home">SIA</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
		  		<ul class="navbar-nav ml-auto">
			      	<li class="nav-item">
			      		<c:choose>
			      			<c:when test="${empty  userLocal.fullName}">
			      				<a class="nav-link" href="/SIA/HTML/login.html"><i class="fas fa-sign-in-alt"></i> Iniciar Sesión</a>
			      			</c:when>
			      			<c:otherwise>
			      				<a class="nav-link" href="/SIA/Logout"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>
			      			</c:otherwise>
			      		</c:choose>
			      	</li>
		    	</ul>
			</div>
		</div>
	</nav>
</div>