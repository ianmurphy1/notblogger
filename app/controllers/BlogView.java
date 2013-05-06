package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import models.Blog;
import models.Post;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class BlogView extends Controller {
	public static void show(Long blogid) {
		Blog blog = Blog.findById(blogid);
		User user = blog.author;
		
		
		User loggedInUser = null;
		if (session.contains("logged_in_userid")) {
			String userId = session.get("logged_in_userid");
			loggedInUser = User.findById(Long.parseLong(userId));			
		}
		
		
		
		//Checks to ensure a user is subscribed to another so they
		//can not read blogs that are private by users they don't
		//subscribe to.
		if (loggedInUser != null) {
			if (!(loggedInUser.equals(user))) {
				if (!(blog.isPublic)) {
				    if (!(loggedInUser.subscribing.contains(user))) {
					    UserProfile.visit(user.id);
			    	}
			    }
		    }		
		} 
		
		List<Post> posts = new ArrayList<Post>(blog.posts);
		Collections.reverse(posts);

		render(blog, posts, loggedInUser);
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
}
