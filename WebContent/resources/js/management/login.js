/**
 * 
 */
function loging(f_id){
    var form = $("#" + f_id);
	$.post("/SIA/Login", form.serialize(), function(res){
		var msg = res.split("&", 2);
		if(msg[1] == "e_notice_sucess"){
			location.href = "/SIA/home";
		}else {
			showNotice(msg[0], msg[1]);
		}
	});

}