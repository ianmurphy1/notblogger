package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Blog;
import models.Post;
import models.User;
import play.mvc.Controller;

public class PublicBlog extends Controller {

	public static void show(Long id, Long blogid) {
		User user = User.findById(id);
		Blog blog = Blog.findById(blogid);

		List<Post> posts = new ArrayList<Post>(blog.posts);
		Collections.reverse(posts);
		
		render(user, posts);
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

		render(user, loggedInUser, reversePosts);
	}
}



