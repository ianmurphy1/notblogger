package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Blog;
import models.User;
import play.Logger;
import play.mvc.Controller;

public class Home extends Controller {
	
	public static void index() {
		
		User user = Start.getLoggedInUser();
		
		List<Blog> reverseBlogs = new ArrayList<Blog>(user.blogs);
		Collections.reverse(reverseBlogs);
		
		List<User> users = User.findAll();
		
		render(user, reverseBlogs, users);
	}
	
	public static void createBlog(String name, boolean isPublic) {
		User user = Start.getLoggedInUser();
		
		Blog blog = new Blog(name, user, isPublic);
		user.addBlog(blog);
		user.save();
		
		Logger.info("Name: " + name + " " + "Is Visible: " + isPublic);
		index();
	}
	
	public static void deleteBlog(Long blogid) {
		User user = Start.getLoggedInUser();
		
		Blog blog = Blog.findById(blogid);
		user.blogs.remove(blog);
		
		user.save();
		blog.delete();
		
		index();
	}
}
