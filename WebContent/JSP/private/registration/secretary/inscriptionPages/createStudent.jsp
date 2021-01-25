<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-12">
	<div class="row">
		<div class="col-12 my-4">
			<h2>Crear Estudiante</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<form onsubmit="createStudent(); return false;" id="student-form">
				<input type="hidden" value="createStudent" name="option"/>
				<div class="form-row">
					<div class="form-group col-sm-6">
						<label for="dni">Cédula:</label>
						<input type="text" class="form-control" id="dni" name="dni" placeholder="Cédula" required/>
					</div>
					<div class="form-group col-sm-6">
						<label for="email">Correo:</label>
						<input type="email" class="form-control" id="email" name="email" placeholder="Correo" required/>
					</div>
					<div class="form-group col-sm-12">
						<label for="fullName">Nombre Completo:</label>
						<input type="text" class="form-control" id="fullName" name="fullName" placeholder="Nombre Completo" required/>
					</div>
					<div class="form-group col-sm-6">
						<label for="address">Dirección:</label>
						<input type="text" class="form-control" id="address" name="address"  placeholder="Dirección" required/>
					</div>
					<div class="form-group col-sm-6">
						<label for="birthdate">Fecha de Nacimiento:</label>
						<input type="date" class="form-control" id="birthdate" name="birthdate"  required/>
					</div>
					<div class="form-group col-sm-6">
						<label for="phone">Teléfono:</label>
						<input type="tel" class="form-control" id="phone" name="phone"  placeholder="Teléfono" required/>
					</div>
					<div class="form-group col-sm-6">
						<label for="gen">Género:</label><br/>
						<div class="form-check form-check-inline mt-1">
							<input class="form-check-input" type="radio" name="gender" id="genderH" value="M" checked>
						  	<label class="form-check-label" for="genderH">
						    	Hombre
						  	</label>
						</div>
						<div class="form-check form-check-inline">
						  	<input class="form-check-input" type="radio" name="gender" id="genderF" value="F">
						  	<label class="form-check-label" for="genderF">
						   		Mujer
						  	</label>
						</div>
						<div class="form-check form-check-inline">
						  	<input class="form-check-input" type="radio" name="gender" id="genderO" value="O">
						  	<label class="form-check-label" for="genderO">
						   		Otro
						  	</label>
						</div>
					</div>
					<div class="form-group col-sm-12">
						<div class="row justify-content-between">
							<button type="button" class="btn btn-outline-info" onclick="cancelCreateStudent();"> 
								<i class="fa fa-arrow-circle-left"></i>  Inscripciones
							</button>
							<button type="submit" class="btn btn-primary"> Crear Estudiante <i class="fas fa-check-square"></i> </button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>