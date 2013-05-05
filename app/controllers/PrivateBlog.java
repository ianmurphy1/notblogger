package controllers;

import play.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import play.db.jpa.Model;

import models.*;

import play.Logger;
import play.mvc.Controller;

public class PrivateBlog extends Controller {
	
	public static void show(Long blogid) {
		User user = Start.getLoggedInUser();
		Blog blog = Blog.findById(blogid);

		List<Post> posts = new ArrayList<Post>(blog.posts);
		Collections.reverse(posts);
		
		if (!(user.equals(blog.author))) {
			visit(blogid);
		}
		
		render(blog, posts);
	}
	
	public static void newPost(String posttitle, String content, Long blogid) {
		User user = Start.getLoggedInUser();
				
		Blog blog = Blog.findById(blogid);	
		
		
		Post post = new Post(posttitle, content, user);
		blog.addPost(post);
		user.save();
		
		Logger.info("title: " + posttitle + " content: " + content);
		show(blogid);
	}
	
	public static void deletePost(Long postid) {
		User user = Start.getLoggedInUser();
		
		Post post = Post.findById(postid);
		
		Blog blog = post.blog;		
		blog.posts.remove(post);
		
		user.save();
		post.delete();
		
		show(blog.id);
	}
	
	public static void visit(Long blogid) {
		
		Blog blog = Blog.findById(blogid);
		
		User user = blog.author;

		List<Post> reversePosts = new ArrayList<Post>(blog.posts);
		Collections.reverse(reversePosts);

		User loggedInUser = Start.getLoggedInUser();
		
		if (user.equals(loggedInUser)) {
			show(blogid);
		}

		render(user, loggedInUser, reversePosts, blog);
	}
}
