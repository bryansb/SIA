/**
 * 
 */

function previousLevel(level) {
	var url = "/SIA/EnrollStudent?option=enrollment&level=" + (level - 1);
	jQuery('#master').load(`${url} #slave`);
}

function subjectSelected() {
	var subjectId = $('input[name="subjectId"]:checked');
	var url = "/SIA/EnrollStudent?option=enrollment" + "&level=1&" + subjectId.serialize();
	jQuery('#master').load(`${url} #slave`);
}

function groupSelected(){
	var groupId = $('input:radio.groupId:checked');
	var url = "/SIA/EnrollStudent?option=enrollment" + "&level=2&" + groupId.serialize();
	jQuery('#master').load(`${url} #slave`);
}

function confirmSummary (){
	var url = "/SIA/EnrollStudent?option=enrollment" + "&level=3";
	jQuery('#master').load(`${url} #slave`);
}

function confirmEnrollment (){
	var url = "/SIA/EnrollStudent?option=enrollment" + "&level=4";
	jQuery('#master').load(`${url} #slave`);
}
