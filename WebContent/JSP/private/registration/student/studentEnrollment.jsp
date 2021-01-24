<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="js">
		<script src="/SIA/resources/js/registration/student_enrollment.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="title">
	Matriculación
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="left">
		<!-- Editar al menú que corresponda -->
		<jsp:include page="/WEB-INF/templates/student_menu.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	
	<jsp:body>
		<div id="master">
		<div id="slave">
			<c:if test="${noticeClass ne 'none'}">
				<div class="row p-4" id="notice">
					<div class="col-12 rounded">
						<div class="row justify-content-end ${noticeClass}">
							<button type="button" onclick="hideNotice();" class="btn text-white">
								<i class="fa fa-window-close"></i> 
							</button>
						</div>
						<div class="row p-2 bg-light">
							<p class="col-12  text-center text-secondary">
								${output}
							</p>
						</div>
					</div>
				</div>
			</c:if>
			<c:choose>
				<c:when test="${level == 0}">
					<div class="row justify-content-center">
						<jsp:include page="enrollmentPages/subjectSelection.jsp"/>
					</div>
					<div class="row justify-content-between">
						<button type="button" class="btn btn-primary disabled"> 
							<i class="fa fa-arrow-circle-left"></i> Atrás
						</button>
						<button type="button" class="btn btn-primary" onclick="subjectSelected();"> 
							Siguiente <i class="fa fa-arrow-circle-right"></i>
						</button>
					</div>
				</c:when>
				<c:when test="${level == 1}">
					<div class="row justify-content-center">
						<jsp:include page="enrollmentPages/groupSelection.jsp"/>
					</div>
					<div class="row justify-content-between">
						<button type="button" class="btn btn-primary" onclick="previousLevel(${level});"> 
							<i class="fa fa-arrow-circle-left"></i> Atrás
						</button>
						<button type="button" class="btn btn-primary" onclick="groupSelected();"> 
							Siguiente <i class="fa fa-arrow-circle-right"></i>
						</button>
					</div>
				</c:when>
				<c:when test="${level == 2}">
					<div class="row justify-content-center">
						<jsp:include page="enrollmentPages/enrollmentSummary.jsp"/>
					</div>
					<div class="row justify-content-between">
						<button type="button" class="btn btn-primary" onclick="previousLevel(${level});"> 
							<i class="fa fa-arrow-circle-left"></i> Atrás
						</button>
						<button type="button" class="btn btn-primary" onclick="confirmSummary();"> 
							Siguiente <i class="fa fa-arrow-circle-right"></i>
						</button>
					</div>
				</c:when>
				<c:when test="${level == 3}">
					<div class="row justify-content-center">
						<jsp:include page="enrollmentPages/enrollmentPrefecture.jsp"/>
					</div>
					<div class="row justify-content-between">
						<button type="button" class="btn btn-primary" onclick="previousLevel(${level});"> 
							<i class="fa fa-arrow-circle-left"></i> Atrás
						</button>
						<button type="button" class="btn btn-primary" onclick="confirmEnrollment();"> 
							Siguiente <i class="fa fa-arrow-circle-right"></i>
						</button>
					</div>
				</c:when>
				<c:when test="${level == 4}">
					<div class="row justify-content-center">
						<jsp:include page="enrollmentPages/enrollmentState.jsp"/>
					</div>
					<div class="row justify-content-between">
						<a href="#" class="btn btn-link"> Ver Facturas </a>
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
		</div>
	</jsp:body>
</t:genericpage>
