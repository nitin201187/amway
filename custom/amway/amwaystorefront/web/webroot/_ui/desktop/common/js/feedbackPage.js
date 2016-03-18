/**
 * 
 */

$(document).ready(function() {
	
	if ($("#action").val() == "information") {
		$("#url").val("");
		$("#url").closest(".control-group").hide();
	}
	$("#action").on("change",function() {
		if($("#action").val() == "information") {
			$("#url").val("");
			$("#url").closest(".control-group").hide();
		} else if($("#action").val() == "action") {
			$("#url").closest(".control-group").show();
		}
	});
});