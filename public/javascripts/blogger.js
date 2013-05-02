showEditor = function(event) {	
  $("#createblog").show("slowly");
}

hideEditor = function(event) {
	
	$("#createblog").hide("slowly");	
}


function loadEventHandlers() {    
  $("#showEditor").click(showEditor);  
  $("#hideEditor").click(hideEditor);
}