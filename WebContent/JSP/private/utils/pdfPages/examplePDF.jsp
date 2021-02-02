<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	<jsp:attribute name="js">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.3.0/jspdf.es.min.js"></script>
		<script src="/SIA/resources/js/utils/pdf_generator.js"></script>
	</jsp:attribute>
	<jsp:body>
		<div class="row justify-content-center" id="pdf-content">
			<div class="col-12">
				<p>Este es el contenido principal.</p>
			</div>
		</div>
	</jsp:body>
</t:genericpage>
