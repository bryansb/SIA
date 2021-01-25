/**
 * 
 */

function createInscription() {
	var studentId = $("#studentId").val();
	var careerId = $( "#careerId" ).val();
	var url = "/SIA/InscriptionController?option=create" + "&studentId=" + 
		studentId + "&careerId=" + careerId;
	console.log(url);
	jQuery('#master').load(`${url} #slave`);
	
}

function createStudent() {
	var url = "/SIA/InscriptionController?" + $('#student-form').serialize();
	jQuery('#master').load(`${url} #slave`);
	console.log(url)
	return false;
}

function createStudentPage() {
	document.location.href = "/SIA/InscriptionController?option=createStudentProcess";
}

function cancelCreateStudent() {
	document.location.href = "/SIA/InscriptionController?option=inscribe";
}