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
public static void show(Long blogid, Long postid) {
		
        Logger.info("Post ID = " + postid);
		Post post = Post.findById(postid);
		Blog blog = Blog.findById(blogid);
		User user = User.findByName(post.author);
		
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
	
	public static void guestVisit(Long blogid, Long postid) {		
		
		Blog blog = Blog.findById(blogid);
		Post post = Post.findById(postid);		

		render(post, blog);
	}
	
	public static void newComment(Long blogid, Long postid, String content) {
		User fromUser = Start.getLoggedInUser();
		String author = fromUser.firstName;		
		Logger.info("Comment from user " + fromUser.firstName
				+ "" + fromUser.lastName + ". " + content);		
			
		Post post = Post.findById(postid);
		
		Comment commentObj = new Comment(content, author);
		post.addComment(commentObj);
		post.save();
		
		show(blogid, postid);		
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
	
	public static void deleteComment(Long blogid, Long postid, Long commentid) {
		Post post = Post.findById(postid);		
		
		Comment theComment = Comment.findById(commentid);
		
		Logger.info("Comment " + theComment.content + " is now deleted");
		
		post.removeComment(theComment);
		post.save();
		theComment.delete();
		
		show(blogid, postid);
	}
}
