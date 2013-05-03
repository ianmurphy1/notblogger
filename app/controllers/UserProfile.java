package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import models.Blog;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class UserProfile extends Controller {
	
	public static void visit(Long id) {
		
		User currentUser = Start.getLoggedInUser();
		
		User user = User.findById(id);
		Logger.info("Visiting " + user.firstName + " " + user.lastName + "'s Profile Page.");
		
		boolean isSubscribing = false;
		
		Set<User> subscribing = currentUser.subscribing;
		
		if (subscribing.contains(user)) {
			isSubscribing = true;
		}
		
		List<Blog> allBlogs = new ArrayList<Blog>(user.blogs);
		List<Blog> privateBlogs = new ArrayList<Blog>();
		List<Blog> publicBlogs = new ArrayList<Blog>();
		
		for (Blog blog: allBlogs) {
			if (blog.isPublic) {
				publicBlogs.add(blog);
			} else if (!(blog.isPublic)) {
				privateBlogs.add(blog);
			}
		}		
		
		render(currentUser, user, privateBlogs, publicBlogs, isSubscribing);
	}	
}
