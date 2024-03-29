package controllers;

import play.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import play.db.jpa.Model;
import models.*;

import play.Logger;
import play.mvc.Controller;

public class Home extends Controller {
	
	public static void index() {
		
		User user = Start.getLoggedInUser();		
		
		List<Blog> allBlogs = user.blogs;
		
		List<Blog> publicBlogs = new ArrayList<Blog>();
		List<Blog> privateBlogs = new ArrayList<Blog>();
			
		
		for (Blog blog: allBlogs) {
			if (blog.isPublic) {
				publicBlogs.add(blog);
			} else if (!(blog.isPublic)){
				privateBlogs.add(blog);
			}
		}
		
		Collections.reverse(publicBlogs);
		Collections.reverse(privateBlogs);		
		
		render(user, publicBlogs, privateBlogs);
	}
	
	public static void createBlog(String name, boolean isPublic) {
		User user = Start.getLoggedInUser();				
		
		Blog blog = new Blog(name, isPublic);
		user.addBlog(blog);
		blog.save();
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
