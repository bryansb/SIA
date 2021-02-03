<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="title">
	Bienvenido
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	<jsp:body>
		<div class="row justify-content-center">
			<div class="col-12">

	   			<section class="jumbotron text-center">
		       		<div class="container">
			          	<h1 class="jumbotron-heading">Bienvenidos</h1>
			          	<p class="lead text-muted">
			          		Sistema Institucional Académico (SIA)
			          	</p>
			          	<p>
			          		<c:choose>
			          			<c:when test="${empty userLocal.fullName}">
			          				<a href="/SIA/HTML/login.html" class="btn btn-primary my-2">Iniciar Sesión</a>
			          			</c:when>
			          			<c:otherwise>
			          				<a href="/SIA/home" class="btn btn-primary my-2">Panel de Inicio</a>
			          			</c:otherwise>
			          		</c:choose>      
			        	</p>
			        </div>
		    	</section>
		
		      	<div class="album py-5 bg-light">
		        	<div class="container">
		          		<div class="row">
		          			<c:forEach var = "i" begin = "1" end = "5">
			            		<div class="col-md-4">
			              			<div class="card mb-4 box-shadow">
			                			<img class="card-img-top" alt="Thumbnail [100%x225]" style="height: 225px; width: 100%; display: block;" 
			                			src="/SIA/resources/img/ups.JPG" data-holder-rendered="true">
			                			<div class="card-body">
			                  				<p class="card-text">
						                  		Noticia de la institución
					                  		</p>
							               	<div class="d-flex justify-content-between align-items-center">
							                	<div class="btn-group">
							                    	<button type="button" class="btn btn-sm btn-outline-secondary">Ver</button>
						                    	</div>
							                    <small class="text-muted">9 mins</small>
						                	</div>
		                				</div>
			              			</div>
		            			</div>
		     				</c:forEach>
	        			</div>
	        		</div>
      			</div>
			</div>
		</div>
	</jsp:body>
	
</t:genericpage>
