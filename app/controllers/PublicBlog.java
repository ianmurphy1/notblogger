package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Blog;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class PublicBlog extends Controller {

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
		blog.save();
		
		
		Logger.info("title: " + posttitle + " content: " + content);
		show(blog.id);
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

		User loggedInUser = null;
		if (session.contains("logged_in_userid")) {
			String userId = session.get("logged_in_userid");
			loggedInUser = User.findById(Long.parseLong(userId));
			
			if ((loggedInUser.equals(user))) {
				show(blogid);
			}
		}

		render(user, loggedInUser, blog, reversePosts);
	}    
}



