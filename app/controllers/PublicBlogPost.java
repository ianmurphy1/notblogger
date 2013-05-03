package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Blog;
import models.Comment;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class PublicBlogPost extends Controller {
public static void show(Long postid) {
		
        Logger.info("Post ID = " + postid);
		Post post = Post.findById(postid);
		Blog blog = post.blog;
		User user = blog.author;
		
		//List<Comment> comments = Comment.find("byPostid", post.id).fetch();
		//Collections.reverse(comments);
		
		render(post, blog, user);
	}
	
	public static void visit(Long postid, Long userid) {
		boolean loggedIn = false;

		if (session.get("logged_in_userid") != null) {
			loggedIn = true;
			User currentUser = Start.getLoggedInUser();

			User user = User.findById(userid);

			Logger.info("Post ID = " + postid);
			Post post = Post.findById(postid);
			render(user, post, loggedIn, currentUser);
		} else {
			User user = User.findById(userid);

			Logger.info("Post ID = " + postid);
			Post post = Post.findById(postid);
			render(user, post, loggedIn);			
		}
	}
	
	public static void guestVisit(Long postid) {		
				
		Post post = Post.findById(postid);
		Blog blog = post.blog;

		render(post, blog);
	}
	
	public static void newComment(Long postid, String content) {
		User fromUser = Start.getLoggedInUser();
		String author = fromUser.firstName;		
		Logger.info("Comment from user " + fromUser.firstName
				+ "" + fromUser.lastName + ". " + content);		
			
		Post post = Post.findById(postid);
		
		Comment commentObj = new Comment(content, author);
		post.addComment(commentObj);
		post.save();
		
		show(postid);		
	}
	
	public static void newVisitorComment(Long userid, Long postid, String content) {
		User fromUser = Start.getLoggedInUser();
		String author = fromUser.firstName;		
		Logger.info("Comment from user " + fromUser.firstName
				+ "" + fromUser.lastName + ". " + content);		
			
		Post post = Post.findById(postid);			
		User user = User.findById(userid);
		
		Comment commentObj = new Comment(content, author);
		post.addComment(commentObj);
		post.save();
		visit(postid, user.id);		
	}
	
	public static void deleteComment(Long commentid) {			
		
		Comment theComment = Comment.findById(commentid);
		
		Post post = theComment.post;
		
		Logger.info("Comment " + theComment.content + " is now deleted");
		
		post.removeComment(theComment);
		post.save();
		theComment.delete();
		
		show(post.id);
	}
}
