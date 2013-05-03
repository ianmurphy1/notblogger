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
		Blog blog = Blog.findById(blogid);

		List<Post> posts = new ArrayList<Post>(blog.posts);
		Collections.reverse(posts);
		
		render(blog, posts);
	}
	
	public static void newPost(String posttitle, String content, Long blogid) {
		User user = Start.getLoggedInUser();
		String author = user.firstName;
		
		Blog blog = Blog.findById(blogid);		
		
		Post post = new Post(posttitle, content, author);
		blog.addPost(post);
		blog.save();
		
		
		Logger.info("title: " + posttitle + " content: " + content);
		show(blog.id);
	}
	
	public static void deletePost(Long postid, Long blogid) {
		User user = Start.getLoggedInUser();
		
		Blog blog = Blog.findById(blogid);
		
		Post post = Post.findById(postid);
		blog.posts.remove(post);
		
		user.save();
		post.delete();
		
		Home.index();
	}
	
	public static void visit(Long userid, Long blogid) {
		
	    User user = User.findById(userid);		
		Blog blog = Blog.findById(blogid);

		List<Post> reversePosts = new ArrayList<Post>(blog.posts);
		Collections.reverse(reversePosts);

		User loggedInUser = null;
		if (session.contains("logged_in_userid")) {
			String userId = session.get("logged_in_userid");
			loggedInUser = User.findById(Long.parseLong(userId));
		}

		render(user, loggedInUser, blog, reversePosts);
	}
	
    public static void guestVisit(Long blogid) {		
	    		
		Blog blog = Blog.findById(blogid);

		List<Post> reversePosts = new ArrayList<Post>(blog.posts);
		Collections.reverse(reversePosts);		

		render(blog, reversePosts);
	}
}



