showBlogCreator = function(event) {	
  $("#createblog").show("slowly");
}

hideBlogCreator = function(event) {	
	$("#createblog").hide("slowly");	
}

showPostCreator = function(event) {
	$("#createpost").show("slowly");
} 

hidePostCreator = function(event) {
	$("#createpost").hide("slowly");
}


function loadHomeEventHandlers() {    
  $("#showBlogCreator").click(showBlogCreator);  
  $("#hideBlogCreator").click(hideBlogCreator);  
  
}

function loadBlogEventHandlers() {
	$("#showPostCreator").click(showPostCreator);
	$("#hidePostCreator").click(hidePostCreator);	
}