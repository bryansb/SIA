/**
 * 
 */

function createInscription(){
	var studentId = $("#studentId").val();
	var careerId = $( "#careerId" ).val();
	var url = "/SIA/InscriptionController?option=create" + "&studentId=" + 
		studentId + "&careerId=" + careerId;
	console.log(url);
	jQuery('#master').load(`${url} #slave`);
	
}