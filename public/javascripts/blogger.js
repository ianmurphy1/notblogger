showBlogCreator = function(event) {	
  $("#createblog").show("slowly");
}

hideBlogCreator = function(event) {	
	$("#createblog").hide("slowly");	
}

showPostCreator = function(event) {
	$("#content").hide("slowly");
	$("#createpost").show("slowly");
} 

hidePostCreator = function(event) {
	$("#content").show("slowly");
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