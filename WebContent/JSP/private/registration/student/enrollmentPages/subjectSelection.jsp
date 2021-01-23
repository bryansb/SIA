<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-12">
	<table>
	<c:forEach var="subject" items="${subjectList}">
		<div class="form-check">
		  <input class="form-check-input" type="checkbox" value="${subject.name}" id="${subject.id}">
		  <label class="form-check-label" for="${subject.id}">
		    ${subject.name}
		  </label>
		</div>
	</c:forEach>
	</table>
</div>
