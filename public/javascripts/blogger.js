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

showMessageCreator = function(event) {	
	  $("#createmessage").show("slowly");
	  $("#showMessageCreator").hide("slowly");
	}

hideMessageCreator = function(event) {
	$("#createmessage").hide("slowly");
	$("#showMessageCreator").show("slowly");
}

showCommentCreator = function(event) {	
	  $("#createcomment").show("slowly");
	  $("#showCommentCreator").hide("slowly");
	}

hideCommentCreator = function(event) {
	$("#createcomment").hide("slowly");	
	$("#showCommentCreator").show("slowly");
}


function loadHomeEventHandlers() {    
  $("#showBlogCreator").click(showBlogCreator);  
  $("#hideBlogCreator").click(hideBlogCreator);  
  
}

function loadBlogEventHandlers() {
	$("#showPostCreator").click(showPostCreator);
	$("#hidePostCreator").click(hidePostCreator);	
}

function loadProfileEventHandlers() {
	$("#showMessageCreator").click(showMessageCreator);
	$("#hideMessageCreator").click(hideMessageCreator);	
}

function loadPostEventHandlers() {    
	  $("#showCommentCreator").click(showCommentCreator);  
	  $("#hideCommentCreator").click(hideCommentCreator);  
	  
	}